
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
 * 物资分配
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/wuziOrder")
public class WuziOrderController {
    private static final Logger logger = LoggerFactory.getLogger(WuziOrderController.class);

    private static final String TABLE_NAME = "wuziOrder";

    @Autowired
    private WuziOrderService wuziOrderService;


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
        CommonUtil.checkMap(params);
        PageUtils page = wuziOrderService.queryPage(params);

        //字典表数据转换
        List<WuziOrderView> list =(List<WuziOrderView>)page.getList();
        for(WuziOrderView c:list){
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
        WuziOrderEntity wuziOrder = wuziOrderService.selectById(id);
        if(wuziOrder !=null){
            //entity转view
            WuziOrderView view = new WuziOrderView();
            BeanUtils.copyProperties( wuziOrder , view );//把实体数据重构到view中
            //级联表 物资
            //级联表
            WuziEntity wuzi = wuziService.selectById(wuziOrder.getWuziId());
            if(wuzi != null){
            BeanUtils.copyProperties( wuzi , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "yonghuId"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
            view.setWuziId(wuzi.getId());
            }
            //级联表 用户
            //级联表
            YonghuEntity yonghu = yonghuService.selectById(wuziOrder.getYonghuId());
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
    public R save(@RequestBody WuziOrderEntity wuziOrder, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,wuziOrder:{}",this.getClass().getName(),wuziOrder.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("用户".equals(role))
            wuziOrder.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        wuziOrder.setCreateTime(new Date());
        wuziOrder.setInsertTime(new Date());
        wuziOrderService.insert(wuziOrder);

        return R.ok();
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody WuziOrderEntity wuziOrder, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,wuziOrder:{}",this.getClass().getName(),wuziOrder.toString());
        WuziOrderEntity oldWuziOrderEntity = wuziOrderService.selectById(wuziOrder.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("用户".equals(role))
//            wuziOrder.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

            wuziOrderService.updateById(wuziOrder);//根据id更新
            return R.ok();
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<WuziOrderEntity> oldWuziOrderList =wuziOrderService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        wuziOrderService.deleteBatchIds(Arrays.asList(ids));

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
            List<WuziOrderEntity> wuziOrderList = new ArrayList<>();//上传的东西
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
                            WuziOrderEntity wuziOrderEntity = new WuziOrderEntity();
//                            wuziOrderEntity.setWuziId(Integer.valueOf(data.get(0)));   //物资 要改的
//                            wuziOrderEntity.setYonghuId(Integer.valueOf(data.get(0)));   //用户 要改的
//                            wuziOrderEntity.setBuyNumber(Integer.valueOf(data.get(0)));   //分配数量 要改的
//                            wuziOrderEntity.setWuziOrderTypes(Integer.valueOf(data.get(0)));   //订单类型 要改的
//                            wuziOrderEntity.setInsertTime(date);//时间
//                            wuziOrderEntity.setCreateTime(date);//时间
                            wuziOrderList.add(wuziOrderEntity);


                            //把要查询是否重复的字段放入map中
                        }

                        //查询是否重复
                        wuziOrderService.insertBatch(wuziOrderList);
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
        PageUtils page = wuziOrderService.queryPage(params);

        //字典表数据转换
        List<WuziOrderView> list =(List<WuziOrderView>)page.getList();
        for(WuziOrderView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段

        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        WuziOrderEntity wuziOrder = wuziOrderService.selectById(id);
            if(wuziOrder !=null){


                //entity转view
                WuziOrderView view = new WuziOrderView();
                BeanUtils.copyProperties( wuziOrder , view );//把实体数据重构到view中

                //级联表
                    WuziEntity wuzi = wuziService.selectById(wuziOrder.getWuziId());
                if(wuzi != null){
                    BeanUtils.copyProperties( wuzi , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setWuziId(wuzi.getId());
                }
                //级联表
                    YonghuEntity yonghu = yonghuService.selectById(wuziOrder.getYonghuId());
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
    public R add(@RequestBody WuziOrderEntity wuziOrder, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,wuziOrder:{}",this.getClass().getName(),wuziOrder.toString());
            WuziEntity wuziEntity = wuziService.selectById(wuziOrder.getWuziId());
            if(wuziEntity == null){
                return R.error(511,"查不到该物资");
            }
            // Double wuziNewMoney = wuziEntity.getWuziNewMoney();

            if(false){
            }
            else if((wuziEntity.getWuziKucunNumber() -wuziOrder.getBuyNumber())<0){
                return R.error(511,"购买数量不能大于库存数量");
            }

            //计算所获得积分
            Double buyJifen =0.0;
            Integer userId = (Integer) request.getSession().getAttribute("userId");
            YonghuEntity yonghuEntity = yonghuService.selectById(userId);
            if(yonghuEntity == null)
                return R.error(511,"用户不能为空");
//            if(yonghuEntity.getNewMoney() == null)
//                return R.error(511,"用户金额不能为空");
//            double balance = yonghuEntity.getNewMoney() - wuziEntity.getWuziNewMoney()*wuziOrder.getBuyNumber();//余额
//            if(balance<0)
//                return R.error(511,"余额不够支付");
            wuziOrder.setWuziOrderTypes(101); //设置订单状态为已分配
//            wuziOrder.setWuziOrderTruePrice(0.0); //设置实付价格
            wuziOrder.setYonghuId(userId); //设置订单支付人id
            wuziOrder.setInsertTime(new Date());
            wuziOrder.setCreateTime(new Date());
                wuziEntity.setWuziKucunNumber( wuziEntity.getWuziKucunNumber() -wuziOrder.getBuyNumber());
                wuziService.updateById(wuziEntity);
                wuziOrderService.insert(wuziOrder);//新增订单


            return R.ok();
    }


    /**
    * 取消分配
    */
    @RequestMapping("/refund")
    public R refund(Integer id, HttpServletRequest request){
        logger.debug("refund方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        String role = String.valueOf(request.getSession().getAttribute("role"));

            WuziOrderEntity wuziOrder = wuziOrderService.selectById(id);//当前表service
            Integer buyNumber = wuziOrder.getBuyNumber();
            Integer wuziId = wuziOrder.getWuziId();
            if(wuziId == null)
                return R.error(511,"查不到该物资");
            WuziEntity wuziEntity = wuziService.selectById(wuziId);
            if(wuziEntity == null)
                return R.error(511,"查不到该物资");

            Integer userId = (Integer) request.getSession().getAttribute("userId");
            YonghuEntity yonghuEntity = yonghuService.selectById(userId);
            if(yonghuEntity == null)
                return R.error(511,"用户不能为空");
            Double zhekou = 1.0;

                //计算金额
//                Double money = wuziEntity.getWuziNewMoney() * buyNumber  * zhekou;
                //计算所获得积分
                Double buyJifen = 0.0;



            wuziEntity.setWuziKucunNumber(wuziEntity.getWuziKucunNumber() + buyNumber);

            wuziOrder.setWuziOrderTypes(102);//设置订单状态为已取消分配
            wuziOrderService.updateAllColumnById(wuziOrder);//根据id更新
            yonghuService.updateById(yonghuEntity);//更新用户信息
            wuziService.updateById(wuziEntity);//更新订单中物资的信息

            return R.ok();
    }

    /**
     * 分发
     */
    @RequestMapping("/deliver")
    public R deliver(Integer id  , HttpServletRequest request){
        logger.debug("refund:,,Controller:{},,ids:{}",this.getClass().getName(),id.toString());
        WuziOrderEntity  wuziOrderEntity = wuziOrderService.selectById(id);
        wuziOrderEntity.setWuziOrderTypes(103);//设置订单状态为已分发
        wuziOrderService.updateById( wuziOrderEntity);

        return R.ok();
    }


    /**
     * 领取物资
     */
    @RequestMapping("/receiving")
    public R receiving(Integer id , HttpServletRequest request){
        logger.debug("refund:,,Controller:{},,ids:{}",this.getClass().getName(),id.toString());
        WuziOrderEntity  wuziOrderEntity = wuziOrderService.selectById(id);
        wuziOrderEntity.setWuziOrderTypes(104);//设置订单状态为领取物资
        wuziOrderService.updateById( wuziOrderEntity);
        return R.ok();
    }

}

