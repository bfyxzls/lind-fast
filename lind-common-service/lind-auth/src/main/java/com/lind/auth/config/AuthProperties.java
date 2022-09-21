package com.lind.auth.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * 配置-热更新
 * @author lind
 * @date 2022/9/21 13:42
 * @since 1.0.0
 */
@Data
@ConfigurationProperties("auth")
public class AuthProperties {
    private String title;
}
