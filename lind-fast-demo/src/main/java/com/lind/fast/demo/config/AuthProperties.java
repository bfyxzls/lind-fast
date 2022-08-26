package com.lind.fast.demo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

/**
 * @author lind
 * @date 2022/8/26 10:21
 * @since 1.0.0
 */
@Data
@RefreshScope
@ConfigurationProperties("auth")
public class AuthProperties {

	private String name;

	private String date;

}
