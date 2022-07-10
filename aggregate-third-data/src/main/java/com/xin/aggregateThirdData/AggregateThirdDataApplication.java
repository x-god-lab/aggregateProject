package com.xin.aggregateThirdData;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author xin
 */
@EnableDiscoveryClient
@MapperScan("com.xin.aggregateThirdData.mapper")
@SpringBootApplication
public class AggregateThirdDataApplication {

	public static void main(String[] args) {
		SpringApplication.run(AggregateThirdDataApplication.class, args);
	}

}