package com.weixin.wxpay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableScheduling
public class WxpayApplication {

	public static void main(String[] args) {
		SpringApplication.run(WxpayApplication.class, args);
	}




}
