
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
 * 健康码打卡
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/daka")
public class DakaController {
    private static final Logger logger = LoggerFactory.getLogger(DakaController.class);

    private static final String TABLE_NAME = "daka";

    @Autowired
    private DakaService dakaService;


    @Autowired
    private TokenService tokenService;

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
    private YishiService yishiService;//疑似人员
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
        params.put("dakaDeleteStart",1);params.put("dakaDeleteEnd",1);
        CommonUtil.checkMap(params);
        PageUtils page = dakaService.queryPage(params);

        //字典表数据转换
        List<DakaView> list =(List<DakaView>)page.getList();
        for(DakaView c:list){
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
        DakaEntity daka = dakaService.selectById(id);
        if(daka !=null){
            //entity转view
            DakaView view = new DakaView();
            BeanUtils.copyProperties( daka , view );//把实体数据重构到view中
            //级联表 用户
            //级联表
            YonghuEntity yonghu = yonghuService.selectById(daka.getYonghuId());
            if(yonghu != null){
            BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "yonghuId"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
            view.setYonghuId(yonghu.getId());
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
    public R save(@RequestBody DakaEntity daka, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,daka:{}",this.getClass().getName(),daka.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("用户".equals(role))
            daka.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        Wrapper<DakaEntity> queryWrapper = new EntityWrapper<DakaEntity>()
            .eq("yonghu_id", daka.getYonghuId())
            .eq("daka_name", daka.getDakaName())
            .eq("daka_delete", 1)
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        DakaEntity dakaEntity = dakaService.selectOne(queryWrapper);
        if(dakaEntity==null){
            daka.setDakaDelete(1);
            daka.setInsertTime(new Date());
            daka.setCreateTime(new Date());
            dakaService.insert(daka);
            return R.ok();
        }else {
            return R.error(511,"今日已经打过卡");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody DakaEntity daka, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,daka:{}",this.getClass().getName(),daka.toString());
        DakaEntity oldDakaEntity = dakaService.selectById(daka.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("用户".equals(role))
//            daka.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        if("".equals(daka.getDakaFile()) || "null".equals(daka.getDakaFile())){
                daka.setDakaFile(null);
        }

            dakaService.updateById(daka);//根据id更新
            return R.ok();
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<DakaEntity> oldDakaList =dakaService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        ArrayList<DakaEntity> list = new ArrayList<>();
        for(Integer id:ids){
            DakaEntity dakaEntity = new DakaEntity();
            dakaEntity.setId(id);
            dakaEntity.setDakaDelete(2);
            list.add(dakaEntity);
        }
        if(list != null && list.size() >0){
            dakaService.updateBatchById(list);
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
            List<DakaEntity> dakaList = new ArrayList<>();//上传的东西
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
                            DakaEntity dakaEntity = new DakaEntity();
//                            dakaEntity.setYonghuId(Integer.valueOf(data.get(0)));   //用户 要改的
//                            dakaEntity.setDakaName(data.get(0));                    //健康码打卡 要改的
//                            dakaEntity.setDakaFile(data.get(0));                    //健康码照片 要改的
//                            dakaEntity.setDakaWendu(data.get(0));                    //体温 要改的
//                            dakaEntity.setDakaText(data.get(0));                    //备注 要改的
//                            dakaEntity.setDakaDelete(1);//逻辑删除字段
//                            dakaEntity.setInsertTime(date);//时间
//                            dakaEntity.setCreateTime(date);//时间
                            dakaList.add(dakaEntity);


                            //把要查询是否重复的字段放入map中
                        }

                        //查询是否重复
                        dakaService.insertBatch(dakaList);
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
        PageUtils page = dakaService.queryPage(params);

        //字典表数据转换
        List<DakaView> list =(List<DakaView>)page.getList();
        for(DakaView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段

        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        DakaEntity daka = dakaService.selectById(id);
            if(daka !=null){


                //entity转view
                DakaView view = new DakaView();
                BeanUtils.copyProperties( daka , view );//把实体数据重构到view中

                //级联表
                    YonghuEntity yonghu = yonghuService.selectById(daka.getYonghuId());
                if(yonghu != null){
                    BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setYonghuId(yonghu.getId());
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
    public R add(@RequestBody DakaEntity daka, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,daka:{}",this.getClass().getName(),daka.toString());
        Wrapper<DakaEntity> queryWrapper = new EntityWrapper<DakaEntity>()
            .eq("yonghu_id", daka.getYonghuId())
            .eq("daka_name", daka.getDakaName())
            .eq("daka_text", daka.getDakaText())
            .eq("daka_delete", daka.getDakaDelete())
//            .notIn("daka_types", new Integer[]{102})
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        DakaEntity dakaEntity = dakaService.selectOne(queryWrapper);
        if(dakaEntity==null){
            daka.setDakaDelete(1);
            daka.setInsertTime(new Date());
            daka.setCreateTime(new Date());
        dakaService.insert(daka);

            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

}

