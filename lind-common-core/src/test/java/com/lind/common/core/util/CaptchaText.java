package com.lind.common.core.util;

import com.lind.common.core.captcha.Randoms;
import org.junit.jupiter.api.Test;

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
