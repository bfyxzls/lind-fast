package com.lind.common.datasource;

import com.lind.common.datasource.annotation.EnableDynamicDataSource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author lind
 * @date 2022/12/23 10:05
 * @since 1.0.0
 */
@SpringBootApplication
@EnableDynamicDataSource
@MapperScan("com.lind.common.datasource.mapper")
public class DsTest {

	public static void main(String[] args) {
		ConfigurableApplicationContext configurableApplicationContext = SpringApplication.run(DsTest.class, args);
		UserServiceImpl userService1 = configurableApplicationContext.getBean(UserServiceImpl.class);
		System.out.println("打印数据用户");
		userService1.get().forEach(o -> {
			System.out.println(o.getName());
		});
	}

}
