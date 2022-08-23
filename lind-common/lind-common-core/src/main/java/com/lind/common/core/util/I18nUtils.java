package com.lind.common.core.util;

import cn.hutool.extra.spring.SpringUtil;
import lombok.experimental.UtilityClass;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Locale;

/**
 * i18n 工具类
 *
 * @author lengleng
 * @UtilityClass所有的方法和属性都会被加上static关键字，并且该类会创建一个私有的空参构造器
 * @date 2022/3/30
 */
@UtilityClass
public class I18nUtils {
    private final Locale locale = LocaleContextHolder.getLocale();

    /**
     * 通过code 获取中文错误信息
     *
     * @param code
     * @return
     */
    public String getMessage(String code) {
        MessageSource messageSource = SpringUtil.getBean("messageSource");
        return messageSource.getMessage(code, null, locale);
    }

    /**
     * 通过code 和参数获取中文错误信息
     *
     * @param code
     * @return
     */
    public String getMessage(String code, Object... objects) {
        MessageSource messageSource = SpringUtil.getBean("messageSource");
        return messageSource.getMessage(code, objects, locale);
    }

}
