package com.lind.common.core.util;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author lind
 * @date 2022/7/26 10:58
 * @since 1.0.0
 */
@Component
public class I18nConfig implements InitializingBean, DisposableBean {
    private static I18nConfig adminConfig = null;

    public static I18nConfig getAdminConfig() {
        return adminConfig;
    }

    @Override
    public void destroy() throws Exception {

    }

    @Override
    public void afterPropertiesSet() throws Exception {
        adminConfig = this;

    }

    @Value("${i18n:zh_CN}")
    private String i18n;

    public String getI18n() {
        if (!Arrays.asList("zh_CN", "zh_TC", "en").contains(i18n)) {
            return "zh_CN";
        }
        return i18n;
    }

}
