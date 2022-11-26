package com.wxpay.paydemo.service;

import com.wxpay.paydemo.entity.OrderInfo;
import com.baomidou.mybatisplus.extension.service.IService;

public interface OrderInfoService extends IService<OrderInfo> {
    void saveCodeUrl(String orderNo,String codeUrl);
    OrderInfo createOrderByProductId(Long productId);
}
