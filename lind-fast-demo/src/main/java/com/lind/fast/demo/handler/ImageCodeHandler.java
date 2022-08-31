package com.lind.fast.demo.handler;

import cn.hutool.core.date.DateUtil;
import com.lind.common.core.captcha.ArithmeticCaptcha;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.FastByteArrayOutputStream;
import org.springframework.web.servlet.function.HandlerFunction;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

/**
 * 带有验证功能的验证码.
 *
 * @author lind
 * @date 2022/7/27 15:43
 * @since 1.0.0
 */
@RequiredArgsConstructor
public class ImageCodeHandler implements HandlerFunction<ServerResponse> {

	@Override
	public ServerResponse handle(ServerRequest request) throws Exception {
		ArithmeticCaptcha captcha = new ArithmeticCaptcha(64, 32);

		// 转换流信息写出
		FastByteArrayOutputStream os = new FastByteArrayOutputStream();
		captcha.out(os);

		return ServerResponse.status(HttpStatus.OK).contentType(MediaType.IMAGE_JPEG)
				.body(new ByteArrayResource(os.toByteArray()));
	}

}
