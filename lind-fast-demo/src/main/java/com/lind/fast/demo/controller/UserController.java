package com.lind.fast.demo.controller;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lind.common.core.util.R;
import com.lind.common.core.validator.ValidatorUtils;
import com.lind.common.core.validator.group.AddGroup;
import com.lind.common.log.annotation.SysLog;
import com.lind.fast.demo.LindDemoApplication;
import com.lind.fast.demo.dto.UserDTO;
import com.lind.fast.demo.entity.TestUser;
import com.lind.fast.demo.mapper.TestUserMapper;
import com.lind.fast.demo.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

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

	@Autowired
	HelloService helloService;

	@PostMapping
	public R ds(@RequestBody UserDTO userDTO) {
		// 只验证声明了AddGroup组的验证注释，本例中是phone和email
		ValidatorUtils.validateEntity(userDTO, AddGroup.class);
		return R.ok();
	}

	@GetMapping
	public R ds() {

		return R.ok(testUserMapper.selectList(new QueryWrapper<>()));
	}

	@GetMapping("list")
	public R dsList(@RequestParam(required = false) LocalDate fromDate) {
		LindDemoApplication.set("hello world!");
		System.out.println("time=" + fromDate);
		helloService.get();
		return R.ok(testUserMapper.selectTestList());
	}

	@SysLog(value = "添加用户",expression = "#userDTO.username")
	@PostMapping("add")
	public R add(@RequestBody  UserDTO userDTO) {
		helloService.get();
		return R.ok(testUserMapper.insert(TestUser.builder().name(userDTO.getUsername()).deptId(1L).build()));
	}

}
