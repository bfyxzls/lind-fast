package com.lind.fast.demo;

import com.lind.common.datasource.annotation.EnableDynamicDataSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author lind
 * @date 2022/7/6 9:28
 * @description
 */
@SpringBootApplication
@EnableDynamicDataSource
public class LindDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(LindDemoApplication.class, args);
    }
}
