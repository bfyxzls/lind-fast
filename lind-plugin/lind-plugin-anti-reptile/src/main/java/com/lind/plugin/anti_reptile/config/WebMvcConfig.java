package com.lind.plugin.anti_reptile.config;

import com.lind.plugin.anti_reptile.interceptor.AntiReptileInterceptor;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author chenjh
 * @since 2020/2/4 17:40
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

	private AntiReptileInterceptor antiReptileInterceptor;

	public WebMvcConfig(AntiReptileInterceptor antiReptileInterceptor) {
		this.antiReptileInterceptor = antiReptileInterceptor;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(this.antiReptileInterceptor).addPathPatterns("/**");
		super.addInterceptors(registry);
	}

}
