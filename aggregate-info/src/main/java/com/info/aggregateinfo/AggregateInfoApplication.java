package com.info.aggregateinfo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableDiscoveryClient
@SpringBootApplication
@MapperScan("com.info.aggregateinfo.mapper")
@EnableSwagger2
public class AggregateInfoApplication {

    public static void main(String[] args) {
        SpringApplication.run(AggregateInfoApplication.class, args);
    }

}
