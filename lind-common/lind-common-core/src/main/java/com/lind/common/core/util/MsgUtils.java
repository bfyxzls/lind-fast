package com.lind.common.core.util;

import cn.hutool.extra.spring.SpringUtil;
import lombok.experimental.UtilityClass;
import org.springframework.context.MessageSource;

import java.util.Locale;

/**
 * i18n 工具类
 *
 * @UtilityClass所有的方法和属性都会被加上static关键字，并且该类会创建一个私有的空参构造器 它是支持sys.admin.name这种i18n资源的
 * @author lengleng
 * @date 2022/3/30
 */
@UtilityClass
public class MsgUtils {

	/**
	 * 通过code 获取中文错误信息
	 * @param code
	 * @return
	 */
	public String getMessage(String code) {
		MessageSource messageSource = SpringUtil.getBean("messageSource");
		return messageSource.getMessage(code, null, Locale.CHINA);
	}

	/**
	 * 通过code 和参数获取中文错误信息
	 * @param code
	 * @return
	 */
	public String getMessage(String code, Object... objects) {
		MessageSource messageSource = SpringUtil.getBean("messageSource");
		return messageSource.getMessage(code, objects, Locale.CHINA);
	}

}
