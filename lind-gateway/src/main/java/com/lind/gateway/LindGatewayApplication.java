package com.lind.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author lind
 * @date 2022/7/6 9:28
 * @description
 */
@EnableDiscoveryClient
@SpringBootApplication
public class LindGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(LindGatewayApplication.class, args);
    }
}
