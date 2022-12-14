package com.wxpay.paydemo.service.impl;

import com.google.gson.Gson;
import com.mysql.cj.util.StringUtils;
import com.wxpay.paydemo.config.WxPayConfig;
import com.wxpay.paydemo.entity.OrderInfo;
import com.wxpay.paydemo.enums.OrderStatus;
import com.wxpay.paydemo.enums.wxpay.WxApiType;
import com.wxpay.paydemo.enums.wxpay.WxNotifyType;
import com.wxpay.paydemo.service.OrderInfoService;
import com.wxpay.paydemo.service.WxPayService;
import com.wxpay.paydemo.util.OrderNoUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 田振兴
 * @create 2022-11-19 16:20
 */
@Service
@Slf4j
public class WxPayServiceImpl implements WxPayService {
    @Resource
    public WxPayConfig wxPayConfig;
    @Resource
    private CloseableHttpClient wxPayClient;
    @Resource
    private OrderInfoService orderInfoService;
    @Override
    public Map<String, Object> nativePay(Long productId) throws IOException {
        log.info("生成订单");

        //存入数据库
        OrderInfo orderInfo = orderInfoService.createOrderByProductId(productId);
        String codeUrl = orderInfo.getCodeUrl();
        if(orderInfo!=null && StringUtils.isNullOrEmpty(codeUrl)){
            log.info("二维码订单已经存在");
            Map<String,Object> map = new HashMap<>();
            map.put("codeUrl",codeUrl);
            map.put("orderNo",orderInfo.getOrderNo());
            return  map;
        }
        //调用统一下单API
        log.info("调用统一下单API");
        HttpPost httpPost = new HttpPost(wxPayConfig.getDomain().concat(WxApiType.NATIVE_PAY.getType()));
        // 请求body参数
        Gson gson = new Gson();
        Map paramsMap = new HashMap();
        paramsMap.put("appid", wxPayConfig.getAppid());
        paramsMap.put("mchid", wxPayConfig.getMchId());
        paramsMap.put("description", orderInfo.getTitle());
        paramsMap.put("out_trade_no", orderInfo.getOrderNo());
        paramsMap.put("notify_url", wxPayConfig.getNotifyDomain().concat(WxNotifyType.NATIVE_NOTIFY.getType()));

        Map amountMap = new HashMap();
        amountMap.put("total", orderInfo.getTotalFee());
        amountMap.put("currency", "CNY");

        paramsMap.put("amount", amountMap);

        //将参数转换成json字符串
        String jsonParams = gson.toJson(paramsMap);
        log.info("请求参数 ===> {}" + jsonParams);
        StringEntity entity = new StringEntity(jsonParams,"utf-8");
        entity.setContentType("application/json");
        httpPost.setEntity(entity);
        httpPost.setHeader("Accept", "application/json");

        //完成签名并执行请求
        CloseableHttpResponse response = wxPayClient.execute(httpPost);

        try {
            String bodyAsString = EntityUtils.toString(response.getEntity());//响应体
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 200) { //处理成功
                log.info("成功,返回结果 = " + bodyAsString);
            } else if (statusCode == 204) { //处理成功，无返回Body
                log.info("成功");
            } else {
                log.info("Native下单失败,响应码 = " + statusCode+ ",返回结果 = " + bodyAsString);
                throw new IOException("request failed");
            }
            Map<String,String> resultMap =  gson.fromJson(bodyAsString, HashMap.class);
            //二维码
            codeUrl = resultMap.get("code_url");
            //保存二维码
            orderInfoService.saveCodeUrl(orderInfo.getOrderNo(),codeUrl);
            Map<String,Object> map = new HashMap<>();
            map.put("codeUrl",codeUrl);
            map.put("orderNo",orderInfo.getOrderNo());
            return  map;
        } finally {
            response.close();
        }
    }
}
