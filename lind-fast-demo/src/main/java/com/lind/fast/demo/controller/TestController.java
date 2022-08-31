package com.lind.fast.demo.controller;

import cn.hutool.extra.spring.EnableSpringUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lind.common.core.captcha.ArithmeticCaptcha;
import com.lind.common.core.util.R;
import com.lind.fast.demo.config.AuthProperties;
import com.lind.fast.demo.mapper.GeneratorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.FastByteArrayOutputStream;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author lind
 * @date 2022/7/7 10:50
 * @since 1.0.0
 */
@RestController
@EnableSpringUtil
public class TestController {

	@Autowired
	GeneratorMapper generatorMapper;

	@Autowired
	AuthProperties authProperties;

	@GetMapping("hello")
	@PreAuthorize("@helloService.get()")
	public R hello() {
		return R.ok("ok" + authProperties.getName());
	}

	@GetMapping("/user/ok")
	public R users(HttpServletRequest request) {
		return R.ok("ok" + authProperties.getName());
	}

	@GetMapping("ds/{ds}")
	public R ds(@PathVariable String ds) {
		return R.ok(generatorMapper.queryAll(null, new Page(1, 10), ds));
	}

	/**
	 * 输出带有计算的验证码图片
	 * @return
	 */
	@GetMapping(path = "codes", produces = MediaType.IMAGE_PNG_VALUE)
	public byte[] code() {
		ArithmeticCaptcha captcha = new ArithmeticCaptcha(64, 64);
		// 转换流信息写出
		FastByteArrayOutputStream os = new FastByteArrayOutputStream();
		captcha.out(os);
		return os.toByteArray();
	}

}
