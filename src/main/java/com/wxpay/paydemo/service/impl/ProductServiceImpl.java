package com.wxpay.paydemo.service.impl;

import com.wxpay.paydemo.entity.Product;
import com.wxpay.paydemo.mapper.ProductMapper;
import com.wxpay.paydemo.service.ProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

}
