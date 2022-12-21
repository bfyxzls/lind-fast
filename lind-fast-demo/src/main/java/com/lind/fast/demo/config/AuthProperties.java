package com.lind.fast.demo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author lind
 * @date 2022/8/26 10:21
 * @since 1.0.0
 */
@Data
@ConfigurationProperties("auth")
public class AuthProperties {

	private String name;

	private String date;

}
