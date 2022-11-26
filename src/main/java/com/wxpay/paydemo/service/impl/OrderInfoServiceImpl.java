package com.wxpay.paydemo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wxpay.paydemo.entity.OrderInfo;
import com.wxpay.paydemo.entity.Product;
import com.wxpay.paydemo.enums.OrderStatus;
import com.wxpay.paydemo.mapper.OrderInfoMapper;
import com.wxpay.paydemo.mapper.ProductMapper;
import com.wxpay.paydemo.service.OrderInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wxpay.paydemo.util.OrderNoUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class OrderInfoServiceImpl extends ServiceImpl<OrderInfoMapper, OrderInfo> implements OrderInfoService {
    @Resource
    private ProductMapper productMapper;

    @Override
    public void saveCodeUrl(String orderNo, String codeUrl) {
        QueryWrapper<OrderInfo> queryWrapper = new QueryWrapper();
        queryWrapper.eq("order_no",orderNo);
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setCodeUrl(codeUrl);
        baseMapper.update(orderInfo,queryWrapper);
    }

    @Override
    public OrderInfo createOrderByProductId(Long productId) {
        OrderInfo orderInfo = this.getNoPayOrderByProduct(productId);
        if(orderInfo!=null){
            return orderInfo;
        }

        Product product =  productMapper.selectById(productId);
        //生成订单
        orderInfo = new OrderInfo();
        orderInfo.setTitle(product.getTitle());
        orderInfo.setOrderNo(OrderNoUtils.getOrderNo());//订单号
        orderInfo.setProductId(productId);
        orderInfo.setTotalFee(product.getPrice());
        orderInfo.setOrderStatus(OrderStatus.NOTPAY.getType());
        baseMapper.insert(orderInfo);
        return orderInfo;
    }

    private OrderInfo getNoPayOrderByProduct(Long productId) {
        QueryWrapper<OrderInfo> queryWrapper = new QueryWrapper();
        queryWrapper.eq("product_id",productId);
        queryWrapper.eq("order_status",OrderStatus.NOTPAY.getType());
        OrderInfo orderInfo = baseMapper.selectOne(queryWrapper);
        return  orderInfo;

    }
}
