package com.wxpay.paydemo.service.impl;

import com.wxpay.paydemo.entity.PaymentInfo;
import com.wxpay.paydemo.mapper.PaymentInfoMapper;
import com.wxpay.paydemo.service.PaymentInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class PaymentInfoServiceImpl extends ServiceImpl<PaymentInfoMapper, PaymentInfo> implements PaymentInfoService {

}
