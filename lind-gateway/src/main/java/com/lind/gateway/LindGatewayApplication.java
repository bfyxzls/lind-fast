package com.lind.gateway;

import io.seata.spring.annotation.GlobalTransactional;
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
@GlobalTransactional // 分布式事务注解
public class LindGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(LindGatewayApplication.class, args);
    }
}
