package com.lind.common.datasource;

import com.lind.common.datasource.annotation.EnableDynamicDataSource;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author lind
 * @date 2022/12/23 10:05
 * @since 1.0.0
 */
@SpringBootTest
@EnableDynamicDataSource
@MapperScan("com.lind.common.datasource.mapper")
public class DsTest {

	@Autowired
	UserServiceImpl userService;

	@Test
	public void read() {
		// ConfigurableApplicationContext configurableApplicationContext =
		// SpringApplication.run(DsTest.class, args);
		// UserServiceImpl userService1 =
		// configurableApplicationContext.getBean(UserServiceImpl.class);
		System.out.println("打印数据用户");
		userService.get().forEach(o -> {
			System.out.println(o.getUsername());
		});
	}

}
