package com.lind.common.datasource;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lind.common.datasource.entity.User;
import com.lind.common.datasource.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author lind
 * @date 2022/12/23 9:56
 * @since 1.0.0
 */
@Component
public class UserServiceImpl {

	@Autowired
	UserMapper userMapper;

	public List<User> get() {
		return userMapper.selectList(new QueryWrapper<>());
	}

	@DS("slave_1")
	public List<User> getSlave() {
		return userMapper.selectList(new QueryWrapper<>());
	}

}
