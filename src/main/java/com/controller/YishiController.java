
package com.controller;

import java.io.File;
import java.math.BigDecimal;
import java.net.URL;
import java.text.SimpleDateFormat;
import com.alibaba.fastjson.JSONObject;
import java.util.*;
import org.springframework.beans.BeanUtils;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.ContextLoader;
import javax.servlet.ServletContext;
import com.service.TokenService;
import com.utils.*;
import java.lang.reflect.InvocationTargetException;

import com.service.DictionaryService;
import org.apache.commons.lang3.StringUtils;
import com.annotation.IgnoreAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.entity.*;
import com.entity.view.*;
import com.service.*;
import com.utils.PageUtils;
import com.utils.R;
import com.alibaba.fastjson.*;

/**
 * 疑似人员
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/yishi")
public class YishiController {
    private static final Logger logger = LoggerFactory.getLogger(YishiController.class);

    private static final String TABLE_NAME = "yishi";

    @Autowired
    private YishiService yishiService;


    @Autowired
    private TokenService tokenService;

    @Autowired
    private DakaService dakaService;//健康码打卡
    @Autowired
    private DictionaryService dictionaryService;//字典
    @Autowired
    private FengkongService fengkongService;//隔离信息
    @Autowired
    private ForumService forumService;//论坛
    @Autowired
    private NewsService newsService;//新闻信息
    @Autowired
    private WuziService wuziService;//物资
    @Autowired
    private WuziOrderService wuziOrderService;//物资分配
    @Autowired
    private YonghuService yonghuService;//用户
    @Autowired
    private UsersService usersService;//管理员


    /**
    * 后端列表
    */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("page方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永不会进入");
        else if("用户".equals(role))
            params.put("yonghuId",request.getSession().getAttribute("userId"));
        params.put("yishiDeleteStart",1);params.put("yishiDeleteEnd",1);
        CommonUtil.checkMap(params);
        PageUtils page = yishiService.queryPage(params);

        //字典表数据转换
        List<YishiView> list =(List<YishiView>)page.getList();
        for(YishiView c:list){
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(c, request);
        }
        return R.ok().put("data", page);
    }

    /**
    * 后端详情
    */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("info方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        YishiEntity yishi = yishiService.selectById(id);
        if(yishi !=null){
            //entity转view
            YishiView view = new YishiView();
            BeanUtils.copyProperties( yishi , view );//把实体数据重构到view中
            //级联表 健康码打卡
            //级联表
            DakaEntity daka = dakaService.selectById(yishi.getDakaId());
            if(daka != null){
            BeanUtils.copyProperties( daka , view ,new String[]{ "id", "createTime", "insertTime", "updateTime"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
            view.setDakaId(daka.getId());
            }
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view, request);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }

    }

    /**
    * 后端保存
    */
    @RequestMapping("/save")
    public R save(@RequestBody YishiEntity yishi, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,yishi:{}",this.getClass().getName(),yishi.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");

        Wrapper<YishiEntity> queryWrapper = new EntityWrapper<YishiEntity>()
            .eq("daka_id", yishi.getDakaId())
            .eq("yishi_name", yishi.getYishiName())
            .eq("yishi_types", yishi.getYishiTypes())
            .eq("yishi_delete", 1)
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        YishiEntity yishiEntity = yishiService.selectOne(queryWrapper);
        if(yishiEntity==null){
            yishi.setYishiDelete(1);
            yishi.setInsertTime(new Date());
            yishi.setCreateTime(new Date());
            yishiService.insert(yishi);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody YishiEntity yishi, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,yishi:{}",this.getClass().getName(),yishi.toString());
        YishiEntity oldYishiEntity = yishiService.selectById(yishi.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
        if("".equals(yishi.getYishiPhoto()) || "null".equals(yishi.getYishiPhoto())){
                yishi.setYishiPhoto(null);
        }

            yishiService.updateById(yishi);//根据id更新
            return R.ok();
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<YishiEntity> oldYishiList =yishiService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        ArrayList<YishiEntity> list = new ArrayList<>();
        for(Integer id:ids){
            YishiEntity yishiEntity = new YishiEntity();
            yishiEntity.setId(id);
            yishiEntity.setYishiDelete(2);
            list.add(yishiEntity);
        }
        if(list != null && list.size() >0){
            yishiService.updateBatchById(list);
        }

        return R.ok();
    }


    /**
     * 批量上传
     */
    @RequestMapping("/batchInsert")
    public R save( String fileName, HttpServletRequest request){
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}",this.getClass().getName(),fileName);
        Integer yonghuId = Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId")));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //.eq("time", new SimpleDateFormat("yyyy-MM-dd").format(new Date()))
        try {
            List<YishiEntity> yishiList = new ArrayList<>();//上传的东西
            Map<String, List<String>> seachFields= new HashMap<>();//要查询的字段
            Date date = new Date();
            int lastIndexOf = fileName.lastIndexOf(".");
            if(lastIndexOf == -1){
                return R.error(511,"该文件没有后缀");
            }else{
                String suffix = fileName.substring(lastIndexOf);
                if(!".xls".equals(suffix)){
                    return R.error(511,"只支持后缀为xls的excel文件");
                }else{
                    URL resource = this.getClass().getClassLoader().getResource("static/upload/" + fileName);//获取文件路径
                    File file = new File(resource.getFile());
                    if(!file.exists()){
                        return R.error(511,"找不到上传文件，请联系管理员");
                    }else{
                        List<List<String>> dataList = PoiUtil.poiImport(file.getPath());//读取xls文件
                        dataList.remove(0);//删除第一行，因为第一行是提示
                        for(List<String> data:dataList){
                            //循环
                            YishiEntity yishiEntity = new YishiEntity();
//                            yishiEntity.setDakaId(Integer.valueOf(data.get(0)));   //打卡 要改的
//                            yishiEntity.setYishiName(data.get(0));                    //疑似名称 要改的
//                            yishiEntity.setYishiPhoto("");//详情和图片
//                            yishiEntity.setYishiTypes(Integer.valueOf(data.get(0)));   //疑似类型 要改的
//                            yishiEntity.setYishiContent("");//详情和图片
//                            yishiEntity.setYishiDelete(1);//逻辑删除字段
//                            yishiEntity.setInsertTime(date);//时间
//                            yishiEntity.setCreateTime(date);//时间
                            yishiList.add(yishiEntity);


                            //把要查询是否重复的字段放入map中
                        }

                        //查询是否重复
                        yishiService.insertBatch(yishiList);
                        return R.ok();
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return R.error(511,"批量插入数据异常，请联系管理员");
        }
    }




    /**
    * 前端列表
    */
    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("list方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));

        CommonUtil.checkMap(params);
        PageUtils page = yishiService.queryPage(params);

        //字典表数据转换
        List<YishiView> list =(List<YishiView>)page.getList();
        for(YishiView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段

        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        YishiEntity yishi = yishiService.selectById(id);
            if(yishi !=null){


                //entity转view
                YishiView view = new YishiView();
                BeanUtils.copyProperties( yishi , view );//把实体数据重构到view中

                //级联表
                    DakaEntity daka = dakaService.selectById(yishi.getDakaId());
                if(daka != null){
                    BeanUtils.copyProperties( daka , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setDakaId(daka.getId());
                }
                //修改对应字典表字段
                dictionaryService.dictionaryConvert(view, request);
                return R.ok().put("data", view);
            }else {
                return R.error(511,"查不到数据");
            }
    }


    /**
    * 前端保存
    */
    @RequestMapping("/add")
    public R add(@RequestBody YishiEntity yishi, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,yishi:{}",this.getClass().getName(),yishi.toString());
        Wrapper<YishiEntity> queryWrapper = new EntityWrapper<YishiEntity>()
            .eq("daka_id", yishi.getDakaId())
            .eq("yishi_name", yishi.getYishiName())
            .eq("yishi_types", yishi.getYishiTypes())
            .eq("yishi_delete", yishi.getYishiDelete())
//            .notIn("yishi_types", new Integer[]{102})
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        YishiEntity yishiEntity = yishiService.selectOne(queryWrapper);
        if(yishiEntity==null){
            yishi.setYishiDelete(1);
            yishi.setInsertTime(new Date());
            yishi.setCreateTime(new Date());
        yishiService.insert(yishi);

            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

}

