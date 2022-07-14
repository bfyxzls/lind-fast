package com.lind.fast.demo;

import com.lind.common.datasource.annotation.EnableDynamicDataSource;
import lombok.extern.slf4j.Slf4j;
import org.jasypt.encryption.StringEncryptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author lind
 * @date 2022/7/6 9:28
 * @description
 */
@SpringBootApplication
@EnableDynamicDataSource
@MapperScan(basePackages = {"com.lind.fast.demo.mapper"})
@Slf4j
public class LindDemoApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext configurableApplicationContext = SpringApplication.run(LindDemoApplication.class, args);
        StringEncryptor stringEncryptor = configurableApplicationContext.getBean(StringEncryptor.class);
        log.info(stringEncryptor.encrypt("123456"));
    }
}
