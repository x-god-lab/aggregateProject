package com.xin.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author xin
 */
@SpringBootApplication
@EnableDiscoveryClient
public class AggregateGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(AggregateGatewayApplication.class, args);
    }

}
