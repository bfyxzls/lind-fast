package com.lind.fast.demo;

import com.lind.common.datasource.annotation.EnableDynamicDataSource;
import com.lind.fast.demo.config.AuthProperties;
import com.lind.fast.demo.config.PermitAllUrlProperties;
import lombok.extern.slf4j.Slf4j;
import org.jasypt.encryption.StringEncryptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author lind
 * @date 2022/7/6 9:28
 * @description
 */
@SpringBootApplication
@EnableDynamicDataSource
@EnableConfigurationProperties({ AuthProperties.class, PermitAllUrlProperties.class }) // 开启AuthProperties配置bean
@MapperScan(basePackages = { "com.lind.fast.demo.mapper" })
@Slf4j
@EnableCaching
// @EnableXxlJob
public class LindDemoApplication {

	static final InheritableThreadLocal<String> dic = new InheritableThreadLocal<>();

	public static void main(String[] args) {
		SpringApplication.run(LindDemoApplication.class, args);
	}

	public static void set(String value) {
		dic.set(value);
	}

	public static String get() {
		return dic.get();
	}

}
