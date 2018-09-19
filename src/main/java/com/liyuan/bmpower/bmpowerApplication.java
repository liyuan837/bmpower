package com.liyuan.bmpower;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class bmpowerApplication {

	public static void main(String[] args) {
		SpringApplication.run(bmpowerApplication.class, args);
	}
}