package com.lind.common.mybatis.service;

import com.lind.common.mybatis.entity.User;
import com.lind.common.mybatis.entity.UserAttribute;
import com.lind.common.mybatis.mapper.UserAttributeMapper;
import com.lind.common.mybatis.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * @author lind
 * @date 2022/10/11 8:49
 * @since 1.0.0
 */
@Component
public class UserService {

	@Autowired
	UserMapper userMapper;

	@Autowired
	UserAttributeMapper userAttributeMapper;

	@Transactional(rollbackFor = { Exception.class })
	public void insertUser() {
		User user = new User();
		user.setId(UUID.randomUUID().toString());
		user.setUsername("zzl4");
		userMapper.insert(user);

		UserAttribute userAttribute = new UserAttribute();
		userAttribute.setId(UUID.randomUUID().toString());
		userAttribute.setUserId(user.getId());
		userAttribute.setName("realName");
		userAttribute.setValue("占占");
		userAttributeMapper.insert(userAttribute);

	}

}
