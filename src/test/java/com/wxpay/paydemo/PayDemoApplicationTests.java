package com.wxpay.paydemo;

import com.wxpay.paydemo.config.WxPayConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.security.PrivateKey;

@SpringBootTest
class PayDemoApplicationTests {
	@Resource
	private WxPayConfig wxPayConfig;
	@Test
	void testGetPrivateKey() {
		String privateKeyPath = wxPayConfig.getPrivateKeyPath();
		PrivateKey privateKey = wxPayConfig.getPrivateKey(privateKeyPath);
		System.out.println(privateKey);
	}

}
