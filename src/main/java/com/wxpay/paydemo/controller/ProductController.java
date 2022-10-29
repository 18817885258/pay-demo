package com.wxpay.paydemo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 田振兴
 * @create 2022-10-28 19:58
 */
@Api(tags = "商品管理")
@RestController
@RequestMapping("/api/product")
public class ProductController {
    @ApiOperation("测试接口")
    @GetMapping("/test")
    public String test(){
        return "田振兴真的很厉害，特别特别的厉害";
    }
}
