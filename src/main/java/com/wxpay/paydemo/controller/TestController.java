package com.wxpay.paydemo.controller;

import com.wxpay.paydemo.config.WxPayConfig;
import com.wxpay.paydemo.vo.R;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 田振兴
 * @create 2022-10-30 18:45
 */
@Api(tags = "测试控制器")
@RestController
@RequestMapping("/api/test")
public class TestController {
    @Resource
    private WxPayConfig wxPayConfig;
    @GetMapping
    public R getWxConfig(){
        String mchId = wxPayConfig.getMchId();
        return R.ok().data("mchId",mchId);
    }
}
