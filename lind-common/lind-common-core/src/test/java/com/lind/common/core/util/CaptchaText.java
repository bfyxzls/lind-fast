package com.lind.common.core.util;

import com.lind.common.core.captcha.ArithmeticCaptcha;
import com.lind.common.core.captcha.Randoms;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.util.FastByteArrayOutputStream;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lind
 * @date 2022/7/27 16:53
 * @since 1.0.0
 */
public class CaptchaText {

	@Test
	public void random() {
		System.out.printf("random:" + Randoms.num(1, 10));
		System.out.printf("random:" + Randoms.alpha(1, 10));
	}

}
