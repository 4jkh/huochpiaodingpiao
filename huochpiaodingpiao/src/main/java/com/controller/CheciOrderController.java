
package com.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.text.SimpleDateFormat;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSONObject;
import java.util.*;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.config.AlipayTemplate;
import com.google.errorprone.annotations.Var;
import org.springframework.beans.BeanUtils;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.web.context.ContextLoader;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

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
 * 购票订单
 * 后端接口
 * @author
 * @email
*/
@RestController
@RequestMapping("/checiOrder")
public class CheciOrderController {
    private static final Logger logger = LoggerFactory.getLogger(CheciOrderController.class);
    public static HashMap map=new HashMap<String,CheciOrderEntity>();
    public static HashMap map2=new HashMap<String,YonghuEntity>();

    @Autowired
    private CheciOrderService checiOrderService;
    @Autowired
    private ZhekouService zhekouService;

    @Autowired
    private DictionaryService dictionaryService;
    @Autowired
    private AlipayTemplate alipayTemplate;

    //级联表service
    @Autowired
    private CheciService checiService;
    @Autowired
    private YonghuService yonghuService;

    @GetMapping("/echart")
    public R echart(){
        List<Echart> echart = checiOrderService.echart();
        BarChartData barChartData = new BarChartData();
        ArrayList<String> names = new ArrayList<>();
        ArrayList<Integer> count = new ArrayList<>();
        ArrayList<Float> moneys = new ArrayList<>();

        for (Echart echart1 : echart) {
            names.add(echart1.getName());
            count.add(echart1.getCount());
            moneys.add(echart1.getMoney());
        }
        barChartData.setCategories(names);
        barChartData.setValues1(count);
        barChartData.setValues2(moneys);
        return R.ok().put("data",barChartData);
    }

    @GetMapping("/excel/{id}")
    public ResponseEntity<byte[]> excel(@PathVariable("id")Integer id){
        List<Excel> excel = checiOrderService.excel(id);
        if(ObjectUtils.isEmpty(excel)){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        //type：1支付，2退款，3以使用

        //销售数量
        Integer xiaoshouliang = excel.size();
        //销售金额
        Float xiaoshouedu= (float) 0;
        //退票数量
        Integer tuipiaoshuliang=0;
        for (Excel excel1 : excel) {
            if(excel1.getShifoutuipiao()!=2){
                xiaoshouedu+=excel1.getXiaoshoue();
            }else {
                tuipiaoshuliang+=1;
            }
        }

        //退票率
        double tuipiaolv = (double)tuipiaoshuliang / (double)xiaoshouliang;
        //座位使用率就是1-退票率
        double shiyonglv= 1.0 -tuipiaolv;
        ExcelShow excelShow = new ExcelShow();
        Excel excel1 = excel.get(0);
        excelShow.setCheci(excel1.getCheci());
        excelShow.setChufa(excel1.getChufa());
        excelShow.setDaozhan(excel1.getDaozhan());
        excelShow.setRiqi(excel1.getRiqi());
        excelShow.setXiaoshoue(xiaoshouedu);
        excelShow.setTuipiaolv(tuipiaolv);
        excelShow.setTuipiaoliang(tuipiaoshuliang);
        excelShow.setZuoweishiyonglv(shiyonglv);
        excelShow.setXiaoshouliang(xiaoshouliang);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ArrayList<ExcelShow> excelShows = new ArrayList<>();
        excelShows.add(excelShow);
        try {
            // 使用 EasyExcel 写入数据到输出流
            EasyExcel.write(outputStream, ExcelShow.class).sheet("信息").doWrite(excelShows);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        // 设置响应头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", excel.get(0).getCheci() + ".xlsx");
//        String fileName;
//        fileName = URLEncoder.encode(rawFileName, "UTF-8").replaceAll("\\+", "%20");




        // 返回响应实体
        return new ResponseEntity<>(outputStream.toByteArray(), headers, HttpStatus.OK);
    }

    @ResponseBody
    @PostMapping("notify")
    @IgnoreAuth
    public String orders(HttpServletRequest request) throws AlipayApiException {
        if (request.getParameter("trade_status").equals("TRADE_SUCCESS")) {
            System.out.println("=========支付宝异步回调========");

            Map<String, String> params = new HashMap<>();
            Map<String, String[]> requestParams = request.getParameterMap();

            for (String name : requestParams.keySet()) {
                params.put(name, request.getParameter(name));
            }

            String sign = params.get("sign");
            String content = AlipaySignature.getSignCheckContentV1(params);
            boolean checkSignature = AlipaySignature.rsa256CheckContent(content, sign, alipayTemplate.getAlipayPublicKey(), "UTF-8"); // 验证签名
            // 支付宝验签
            if (checkSignature) {
                // 验签通过
                System.out.println("交易名称: " + params.get("subject"));
                System.out.println("交易状态: " + params.get("trade_status"));
                System.out.println("支付宝交易凭证号: " + params.get("trade_no"));
                System.out.println("商户订单号: " + params.get("out_trade_no"));
                System.out.println("交易金额: " + params.get("total_amount"));
                System.out.println("买家在支付宝唯一id: " + params.get("buyer_id"));
                System.out.println("买家付款时间: " + params.get("gmt_payment"));
                System.out.println("买家付款金额: " + params.get("buyer_pay_amount"));
                CheciOrderEntity o = (CheciOrderEntity) map.get("1");
                YonghuEntity o1 = (YonghuEntity) map2.get("1");
                String total = params.get("total_amount");
                System.out.println(o1.getNewMoney());
                o1.setNewMoney(o1.getNewMoney()- new Double(total));
                System.out.println(o1.getNewMoney());

                o.setZhekou(o1.getZhekou());
                o.setYonghuId(o1.getId()); //设置订单支付人id
                o.setCheciOrderTruePrice(new Double(total));
                o.setCheciOrderTypes(1);
                o.setInsertTime(new Date());
                o.setCreateTime(new Date());
                checiOrderService.insert(o);//新增订单
                yonghuService.updateById(o1);
            }
        }
        return "success";

    }


    /**
     * 前端保存
     */
    @RequestMapping("/add")
    @IgnoreAuth
    @Transactional(rollbackFor = Exception.class)
    public R add(@RequestBody CheciOrderEntity checiOrder, HttpServletRequest request, HttpServletResponse httpResponse) throws IOException, AlipayApiException {
        Float dengji = checiOrder.getDengji();
        if(ObjectUtils.isEmpty(dengji)){
            return R.error(511,"该座位无票");
        }
        if(dengji==0){
            return R.error(511,"该座位无票");
        }
        map.put("1",checiOrder);
        YonghuEntity yonghuEntity = yonghuService.selectById(checiOrder.getYonghuId());
        if(yonghuEntity == null)
            return R.error(511,"用户不能为空");
        if(yonghuEntity.getNewMoney() == null)
            return R.error(511,"用户金额不能为空");
        map2.put("1",yonghuEntity);

//        Integer zhekou = yonghuEntity.getZhekou();
//
//        if(!ObjectUtils.isEmpty(zhekou)) {
//            Zhekou zhekou1 = zhekouService.selectById(zhekou);
//            Float zhekou2 = zhekou1.getZhekou();
//            double balance = yonghuEntity.getNewMoney() - checiOrder.getDengji() * (checiOrder.getBuyZuoweiNumber().split(",").length) * zhekou2;//余额
//            if(balance<0)
//                return R.error(511,"余额不够支付");
//            yonghuEntity.setNewMoney(balance);//设置金额
//        }else{
//            double balance = yonghuEntity.getNewMoney() - checiOrder.getDengji() * (checiOrder.getBuyZuoweiNumber().split(",").length);//余额
//            if(balance<0)
//                return R.error(511,"余额不够支付");
//            yonghuEntity.setNewMoney(balance);//设置金额
//        }
//        checiOrder.setCheciOrderTypes(1); //设置订单状态为已支付
//        if(!ObjectUtils.isEmpty(zhekou)) {
//            Zhekou zhekou1 = zhekouService.selectById(zhekou);
//            Float zhekou2 = zhekou1.getZhekou();
//            float v = checiOrder.getDengji() * (checiOrder.getBuyZuoweiNumber().split(",").length) * zhekou2;
//            checiOrder.setCheciOrderTruePrice((double) v); //设置实付价格
//        }else{
//            checiOrder.setCheciOrderTruePrice((double) (checiOrder.getDengji() * (checiOrder.getBuyZuoweiNumber().split(",").length))); //设置实付价格
//        }
//        checiOrder.setZhekou(zhekou);
//        checiOrder.setYonghuId(userId); //设置订单支付人id
//        checiOrder.setCheciOrderUuidNumber(String.valueOf(new Date().getTime()));
//        checiOrder.setInsertTime(new Date());
//        checiOrder.setCreateTime(new Date());
//        checiOrderService.insert(checiOrder);//新增订单
//
////        alipayTemplate.pay(checiOrder,httpResponse);
//            yonghuService.updateById(yonghuEntity);
        return R.ok().put("data",checiOrder);
    }

    /**
    * 后端列表
    */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("page方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永不会进入");
        else if("会员".equals(role))
            params.put("yonghuId",request.getSession().getAttribute("userId"));
        if(params.get("orderBy")==null || params.get("orderBy")==""){
            params.put("orderBy","id");
        }
        PageUtils page = checiOrderService.queryPage(params);

        //字典表数据转换
        List<CheciOrderView> list =(List<CheciOrderView>)page.getList();
        for(CheciOrderView c:list){
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
        CheciOrderEntity checiOrder = checiOrderService.selectById(id);
        if(checiOrder !=null){
            //entity转view
            CheciOrderView view = new CheciOrderView();
            BeanUtils.copyProperties( checiOrder , view );//把实体数据重构到view中

                //级联表
                CheciEntity checi = checiService.selectById(checiOrder.getCheciId());
                if(checi != null){
                    BeanUtils.copyProperties( checi , view ,new String[]{ "id", "createTime", "insertTime", "updateTime"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setCheciId(checi.getId());
                }
                //级联表
                YonghuEntity yonghu = yonghuService.selectById(checiOrder.getYonghuId());
                if(yonghu != null){
                    BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createTime", "insertTime", "updateTime"});//把级联的数据添加到view中,并排除id和创建时间字段
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
    public R save(@RequestBody CheciOrderEntity checiOrder, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,checiOrder:{}",this.getClass().getName(),checiOrder.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("会员".equals(role))
            checiOrder.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        checiOrder.setInsertTime(new Date());
        checiOrder.setCreateTime(new Date());
        checiOrderService.insert(checiOrder);
        return R.ok();
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody CheciOrderEntity checiOrder, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,checiOrder:{}",this.getClass().getName(),checiOrder.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("会员".equals(role))
//            checiOrder.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        //根据字段查询是否有相同数据
        Wrapper<CheciOrderEntity> queryWrapper = new EntityWrapper<CheciOrderEntity>()
            .eq("id",0)
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        CheciOrderEntity checiOrderEntity = checiOrderService.selectOne(queryWrapper);
        if(checiOrderEntity==null){
            checiOrderService.updateById(checiOrder);//根据id更新
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        checiOrderService.deleteBatchIds(Arrays.asList(ids));
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
        try {
            List<CheciOrderEntity> checiOrderList = new ArrayList<>();//上传的东西
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
                            CheciOrderEntity checiOrderEntity = new CheciOrderEntity();
//                            checiOrderEntity.setCheciOrderUuidNumber(data.get(0));                    //订单号 要改的
//                            checiOrderEntity.setCheciId(Integer.valueOf(data.get(0)));   //车次 要改的
//                            checiOrderEntity.setYonghuId(Integer.valueOf(data.get(0)));   //会员 要改的
//                            checiOrderEntity.setCheciOrderTruePrice(data.get(0));                    //实付价格 要改的
//                            checiOrderEntity.setCheciOrderTypes(Integer.valueOf(data.get(0)));   //订单类型 要改的
//                            checiOrderEntity.setBuySectionNumber(Integer.valueOf(data.get(0)));   //车厢 要改的
//                            checiOrderEntity.setBuyZuoweiNumber(data.get(0));                    //购买的座位 要改的
//                            checiOrderEntity.setBuyZuoweiTime(sdf.parse(data.get(0)));          //订购日期 要改的
//                            checiOrderEntity.setInsertTime(date);//时间
//                            checiOrderEntity.setCreateTime(date);//时间
                            checiOrderList.add(checiOrderEntity);


                            //把要查询是否重复的字段放入map中
                                //订单号
                                if(seachFields.containsKey("checiOrderUuidNumber")){
                                    List<String> checiOrderUuidNumber = seachFields.get("checiOrderUuidNumber");
                                    checiOrderUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> checiOrderUuidNumber = new ArrayList<>();
                                    checiOrderUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("checiOrderUuidNumber",checiOrderUuidNumber);
                                }
                        }

                        //查询是否重复
                         //订单号
                        List<CheciOrderEntity> checiOrderEntities_checiOrderUuidNumber = checiOrderService.selectList(new EntityWrapper<CheciOrderEntity>().in("checi_order_uuid_number", seachFields.get("checiOrderUuidNumber")));
                        if(checiOrderEntities_checiOrderUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(CheciOrderEntity s:checiOrderEntities_checiOrderUuidNumber){
                                repeatFields.add(s.getCheciOrderUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [订单号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        checiOrderService.insertBatch(checiOrderList);
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

        // 没有指定排序字段就默认id倒序
        if(StringUtil.isEmpty(String.valueOf(params.get("orderBy")))){
            params.put("orderBy","id");
        }
        PageUtils page = checiOrderService.queryPage(params);

        //字典表数据转换
        List<CheciOrderView> list =(List<CheciOrderView>)page.getList();
        for(CheciOrderView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段
        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        CheciOrderEntity checiOrder = checiOrderService.selectById(id);
            if(checiOrder !=null){


                //entity转view
                CheciOrderView view = new CheciOrderView();
                BeanUtils.copyProperties( checiOrder , view );//把实体数据重构到view中

                //级联表
                    CheciEntity checi = checiService.selectById(checiOrder.getCheciId());
                if(checi != null){
                    BeanUtils.copyProperties( checi , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setCheciId(checi.getId());
                }
                //级联表
                    YonghuEntity yonghu = yonghuService.selectById(checiOrder.getYonghuId());
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
     * 退款
     */
    @RequestMapping("/shenqingrefund")
    public R shenqingrefund(Integer id){
        CheciOrderEntity checiOrderEntity = checiOrderService.selectById(id);
        checiOrderEntity.setStatus("退款中");
        checiOrderService.updateById(checiOrderEntity);//根据id更新
        return R.ok();
    }

    /**
    * 退款
    */
    @RequestMapping("/refund")
    public R refund(Integer id, HttpServletRequest request){
        logger.debug("refund方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        String role = String.valueOf(request.getSession().getAttribute("role"));

            CheciOrderEntity checiOrder = checiOrderService.selectById(id);
            Integer buyNumber = checiOrder.getBuyZuoweiNumber().split(",").length;
            Integer checiId = checiOrder.getCheciId();
            if(checiId == null)
                return R.error(511,"查不到该车次信息");
            CheciEntity checiEntity = checiService.selectById(checiId);
            if(checiEntity == null)
                return R.error(511,"查不到该车次信息");
            Double checiNewMoney = checiEntity.getCheciNewMoney();
            if(checiNewMoney == null)
                return R.error(511,"车次信息价格不能为空");

            Integer userId = (Integer) request.getSession().getAttribute("userId");
            YonghuEntity yonghuEntity = yonghuService.selectById(userId);
            if(yonghuEntity == null)
                return R.error(511,"用户不能为空");
            if(yonghuEntity.getNewMoney() == null)
                return R.error(511,"用户金额不能为空");


        Integer zhekou = yonghuEntity.getZhekou();
        if(!ObjectUtils.isEmpty(zhekou)) {
            if(zhekou==0){
                Double money = checiOrder.getCheciOrderTruePrice() * buyNumber ;
                yonghuEntity.setNewMoney(yonghuEntity.getNewMoney() + money); //设置金额
            }else {
                Zhekou zhekou1 = zhekouService.selectById(zhekou);
                Float zhekou2 = zhekou1.getZhekou();
                //余额支付
                //计算金额
                Double money = checiOrder.getCheciOrderTruePrice() * buyNumber * zhekou2;
                yonghuEntity.setNewMoney(yonghuEntity.getNewMoney() + money); //设置金额
            }
        }else{
            //余额支付
            //计算金额
            Double money = checiOrder.getCheciOrderTruePrice() * buyNumber ;
            yonghuEntity.setNewMoney(yonghuEntity.getNewMoney() + money); //设置金额
        }

        checiOrder.setStatus("已退款");
            checiOrder.setCheciOrderTypes(2);//设置订单状态为退款
            checiOrderService.updateById(checiOrder);//根据id更新
            yonghuService.updateById(yonghuEntity);//更新用户信息
            checiService.updateById(checiEntity);//更新订单中车次信息的信息
            return R.ok();
    }


    /**
     * 发货
     */
    @RequestMapping("/deliver")
    public R deliver(Integer id ){
        logger.debug("refund:,,Controller:{},,ids:{}",this.getClass().getName(),id.toString());
        CheciOrderEntity  checiOrderEntity = new  CheciOrderEntity();;
        checiOrderEntity.setId(id);
        checiOrderEntity.setCheciOrderTypes(3);
        boolean b =  checiOrderService.updateById( checiOrderEntity);
        if(!b){
            return R.error("发货出错");
        }
        return R.ok();
    }

















}
