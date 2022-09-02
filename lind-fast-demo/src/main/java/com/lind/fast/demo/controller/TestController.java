package com.lind.fast.demo.controller;

import cn.hutool.extra.spring.EnableSpringUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lind.common.core.util.R;
import com.lind.common.oss.service.OssTemplate;
import com.lind.fast.demo.config.AuthProperties;
import com.lind.fast.demo.mapper.GeneratorMapper;
import com.lind.plugin.captcha.core.ArithmeticCaptcha;
import com.lind.plugin.captcha.core.base.Captcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.FastByteArrayOutputStream;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;

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

	@RequestMapping("/captcha")
	public void captcha(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 设置请求头为输出图片类型
		response.setContentType("image/gif");
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);

		// 三个参数分别为宽、高、位数
		ArithmeticCaptcha specCaptcha = new ArithmeticCaptcha(130, 48, 2);
		// 设置字体
		specCaptcha.setFont(new Font("Verdana", Font.PLAIN, 32)); // 有默认字体，可以不用设置
		// 设置类型，纯数字、纯字母、字母数字混合
		specCaptcha.setCharType(Captcha.TYPE_ONLY_NUMBER);

		// 验证码存入session
		request.getSession().setAttribute("captcha", specCaptcha.text().toLowerCase());

		// 输出图片流
		specCaptcha.out(response.getOutputStream());
	}



}
