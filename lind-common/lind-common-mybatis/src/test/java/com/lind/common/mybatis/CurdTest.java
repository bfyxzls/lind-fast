package com.lind.common.mybatis;

import com.baomidou.mybatisplus.autoconfigure.MybatisPlusAutoConfiguration;
import com.lind.common.mybatis.entity.User;
import com.lind.common.mybatis.mapper.UserMapper;
import com.lind.common.mybatis.service.UserService;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.UUID;

/**
 * @author lind
 * @date 2022/10/10 16:25
 * @since 1.0.0
 */
@EnableTransactionManagement
@SpringBootTest(classes = { MybatisAutoConfiguration.class, DataSourceAutoConfiguration.class,
		MybatisPlusAutoConfiguration.class, DataSource.class, SqlSessionFactory.class,
		DataSourceTransactionManagerAutoConfiguration.class, UserService.class })
@MapperScan(basePackages = "com.lind.common.mybatis.mapper")
public class CurdTest {

	@Autowired
	UserMapper userMapper;

	@Autowired
	UserService userService;

	@Test
	public void insert() throws IOException {
		User user = new User();
		user.setId(UUID.randomUUID().toString());
		user.setUsername("zzl");
		userMapper.insert(user);
	}

	/**
	 * @DataSourceTransactionManagerAutoConfiguration 注入事务所需的bean
	 * @throws IOException
	 */
	@Test
	public void trans() throws IOException {
		// 开启事务：@EnableTransactionManagement，注入事务的bean:DataSourceTransactionManagerAutoConfiguration
		// 事务需要在新的bean中，不需要在单元测试中直接使用@Transactional
		// 使用@Transactional注解都是用在类的方法上。官网也不建议使用在接口类上面，注解肯定都是用到了aop的思想，即使用了动态代理。而如果使用cglib动态代理肯定没有办法代理接口类
		userService.insertUser();
	}

}
