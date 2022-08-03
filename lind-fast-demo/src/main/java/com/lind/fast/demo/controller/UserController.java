package com.lind.fast.demo.controller;

import com.github.mybatis.helper.core.MybatisThreadHelper;
import com.lind.common.core.util.R;
import com.lind.common.core.validator.ValidatorUtils;
import com.lind.common.core.validator.group.AddGroup;
import com.lind.fast.demo.dto.UserDTO;
import com.lind.fast.demo.mapper.TestUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lind
 * @date 2022/7/15 10:58
 * @since 1.0.0
 */
@RestController
@RequestMapping("user")
public class UserController {

	@Autowired
	TestUserMapper testUserMapper;

	@PostMapping
	public R ds(@RequestBody UserDTO userDTO) {
		// 只验证声明了AddGroup组的验证注释，本例中是phone和email
		ValidatorUtils.validateEntity(userDTO, AddGroup.class);
		return R.ok();
	}

	@GetMapping
	public R ds() {
		List<Map<String,Object[]>> arr=new ArrayList<>();
		Map<String,Object[]> map=new HashMap<>();
		map.put("dept_id", new Object[]{1});
		MybatisThreadHelper.putVariable("dataScope",Arrays.asList(map));

		return R.ok(testUserMapper.selectTestList());
	}

}
