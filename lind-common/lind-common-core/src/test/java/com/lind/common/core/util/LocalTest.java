package com.lind.common.core.util;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.context.i18n.LocaleContextHolder;

/**
 * @author lind
 * @date 2022/12/22 16:22
 * @since 1.0.0
 */
@Slf4j
public class LocalTest {

	@Test
	public void currnetLocal() {
		// 当前区域
		log.info("local={}", LocaleContextHolder.getLocale());
		log.info(I18nUtil.getString("sys.user.update.passwordError"));
	}

}
