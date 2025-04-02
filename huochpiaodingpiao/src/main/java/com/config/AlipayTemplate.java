package com.config;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.controller.CheciOrderController;
import com.entity.CheciOrderEntity;
import com.entity.YonghuEntity;
import com.entity.Zhekou;
import com.service.ZhekouService;
import com.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@ConfigurationProperties(prefix = "alipay")
@Component
public class AlipayTemplate {
    @Autowired
    private ZhekouService zhekouService;
    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    @Value("{alipay.appId}")
    public String appId;

    // 应用私钥，就是工具生成的应用私钥
    @Value("{alipay.merchantPrivateKey}")
    public String merchantPrivateKey;

    // 支付宝公钥,对应APPID下的支付宝公钥。
    @Value("{alipay.alipayPublicKey}")
    public String alipayPublicKey;

    //同步通知，支付成功，一般跳转到成功页
    @Value("{alipay.returnUrl}")
    public String returnUrl;
    // 支付宝会悄悄的给我们发送一个请求，告诉我们支付成功的信息
    @Value("{alipay.notifyUrl}")
    public String notifyUrl;
    // 签名方式
    @Value("{alipay.signType}")
    private String signType;

    // 字符编码格式
    @Value("{alipay.charset}")
    private String charset;
    //订单超时时间
    private String timeout = "1h";
    // 支付宝网关；https://openapi-sandbox.dl.alipaydev.com/gateway.do
    @Value("{alipay.gatewayUrl}")
    public String gatewayUrl;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getMerchantPrivateKey() {
        return merchantPrivateKey;
    }

    public void setMerchantPrivateKey(String merchantPrivateKey) {
        this.merchantPrivateKey = merchantPrivateKey;
    }

    public String getAlipayPublicKey() {
        return alipayPublicKey;
    }

    public void setAlipayPublicKey(String alipayPublicKey) {
        this.alipayPublicKey = alipayPublicKey;
    }

    public String getReturnUrl() {
        return returnUrl;
    }

    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public String getTimeout() {
        return timeout;
    }

    public void setTimeout(String timeout) {
        this.timeout = timeout;
    }

    public String getGatewayUrl() {
        return gatewayUrl;
    }

    public void setGatewayUrl(String gatewayUrl) {
        this.gatewayUrl = gatewayUrl;
    }

    public void pay(HttpServletResponse httpResponse) throws
            AlipayApiException, IOException {
//        // 1、根据支付宝的配置生成一个支付客户端
        AlipayClient alipayClient = new DefaultAlipayClient(gatewayUrl, appId, merchantPrivateKey,
                "JSON", charset, alipayPublicKey, signType);
// 2、创建一个支付请求，并设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(returnUrl);
        alipayRequest.setNotifyUrl(notifyUrl);
        JSONObject bizContent = new JSONObject();
        CheciOrderEntity o = (CheciOrderEntity) CheciOrderController.map.get("1");
        YonghuEntity o2 = (YonghuEntity) CheciOrderController.map2.get("1");
        Integer zhekou = o2.getZhekou();
        double balance=0;
        int money=0;
        if (!ObjectUtils.isEmpty(o2.getZhekou())) {
            if(zhekou==0){
                money= (int) (o.getDengji() * (o.getBuyZuoweiNumber().split(",").length));
                balance = o2.getNewMoney() - o.getDengji() * (o.getBuyZuoweiNumber().split(",").length);//余额
                if (balance < 0)
                    return ;
            }else {
                Zhekou zhekou1 = zhekouService.selectById(zhekou);
                Float zhekou2 = zhekou1.getZhekou();
                balance = o2.getNewMoney() - o.getDengji() * (o.getBuyZuoweiNumber().split(",").length) * zhekou2;//余额
                money = (int) (o.getDengji() * (o.getBuyZuoweiNumber().split(",").length) * zhekou2);
                if (balance < 0)
                    return;
            }
        } else {
            money= (int) (o.getDengji() * (o.getBuyZuoweiNumber().split(",").length));
            balance = o2.getNewMoney() - o.getDengji() * (o.getBuyZuoweiNumber().split(",").length);//余额
            if (balance < 0)
                return ;
        }
        bizContent.put("out_trade_no", o.getCheciOrderUuidNumber());  // 我们自己生成的订单编号
        bizContent.put("total_amount", money); // 订单的总金额
        bizContent.put("subject", o.getCheciId());   // 支付的名称
        bizContent.put("timeout_express", timeout);   // 支付的名称
        bizContent.put("product_code", "FAST_INSTANT_TRADE_PAY");
        alipayRequest.setBizContent(bizContent.toJSONString());
        String result = alipayClient.pageExecute(alipayRequest).getBody();
        System.out.println("支付宝发起支付，结果：" + result);
        httpResponse.setContentType("text/html;charset=" + "UTF-8");
        httpResponse.getWriter().write(result);
        httpResponse.getWriter().flush();
        httpResponse.getWriter().close();


//        // 1、根据支付宝的配置生成一个支付客户端
//        AlipayClient alipayClient = new DefaultAlipayClient(gatewayUrl, appId, merchantPrivateKey,
//                "JSON", charset, alipayPublicKey, signType);
//// 2、创建一个支付请求，并设置请求参数
//        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
//        alipayRequest.setReturnUrl(returnUrl);
//        alipayRequest.setNotifyUrl(notifyUrl);
//        String id = UUID.randomUUID().toString().replace("-", "");
//        int money =1;
//        JSONObject bizContent = new JSONObject();
//        bizContent.put("out_trade_no", id);  // 我们自己生成的订单编号
//        bizContent.put("total_amount", money); // 订单的总金额
//        bizContent.put("subject", "测试商品名称");   // 支付的名称
//        bizContent.put("timeout_express", timeout);   // 支付的名称
//        bizContent.put("product_code", "FAST_INSTANT_TRADE_PAY");
//        alipayRequest.setBizContent(bizContent.toJSONString());
//        String result = alipayClient.pageExecute(alipayRequest).getBody();
//        System.out.println("支付宝发起支付，结果："+result);
//        httpResponse.setContentType("text/html;charset=" +"UTF-8");
//        httpResponse.getWriter().write(result);
//        httpResponse.getWriter().flush();
//        httpResponse.getWriter().close();
    }
}
