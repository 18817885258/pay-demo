package com.wxpay.paydemo.controller;

import com.wxpay.paydemo.entity.Product;
import com.wxpay.paydemo.service.ProductService;
import com.wxpay.paydemo.vo.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author 田振兴
 * @create 2022-10-28 19:58
 */
@CrossOrigin//开放前端跨域请求
@Api(tags = "商品管理")
@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Resource
    private ProductService productService;
    @ApiOperation("测试接口")
    @GetMapping("/test")
    public R test(){
        return R.ok().data("message","hello").data("time",new Date());
    }

    @ApiOperation("课程列表")
    @GetMapping("/list")
    public R list(){
        List<Product> list = productService.list();
        return R.ok().data("productList",list);
    }
}
