package com.lind.plugin.captcha;

import com.lind.plugin.captcha.config.CaptchaEndpoint;
import com.lind.plugin.captcha.config.CaptchaProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author lengleng
 * @date 2020/7/31
 * <p>
 * 验证码配置类
 */
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(CaptchaProperties.class)
public class CaptchaAutoConfiguration {

    @Bean
    @ConditionalOnWebApplication
    public CaptchaEndpoint captchaEndpoint(CaptchaProperties properties) {
        return new CaptchaEndpoint(properties);
    }

}
