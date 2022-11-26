package com.wxpay.paydemo.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author 田振兴
 * @create 2022-10-29 16:50
 */
@Configuration
@MapperScan("com.wxpay.paydemo.mapper")
@EnableTransactionManagement //启用事务管理
public class MyBatisPlusConfig {
}
