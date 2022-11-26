package com.wxpay.paydemo.controller;

import com.wxpay.paydemo.service.WxPayService;
import com.wxpay.paydemo.vo.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Map;

/**
 * @author 田振兴
 * @create 2022-11-19 16:17
 */
@RestController
@RequestMapping("/api/wx-pay")
@CrossOrigin
@Slf4j
@Api(tags = "网站微信支付api")
public class WxPayController {
    @Resource
    public WxPayService wxPayService;
    @ApiOperation("统一调用单API接口,生成二维码")
    @PostMapping("native/{productId}")
    public R nativePay(@PathVariable Long productId) throws IOException {
       Map<String,Object> map =  wxPayService.nativePay(productId);
       return R.ok().setData(map);
    }
}
