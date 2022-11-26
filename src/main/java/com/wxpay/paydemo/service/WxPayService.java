package com.wxpay.paydemo.service;

import java.io.IOException;
import java.util.Map;

/**
 * @author 田振兴
 * @create 2022-11-19 16:19
 */
public interface WxPayService {
    Map<String, Object> nativePay(Long productId) throws IOException;
}
