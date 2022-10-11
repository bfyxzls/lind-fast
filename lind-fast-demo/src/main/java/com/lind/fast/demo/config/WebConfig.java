package com.lind.fast.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author lind
 * @date 2022/10/8 17:07
 * @since 1.0.0
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

	// 资源URL与本地路径映射
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// css
		registry.addResourceHandler("/css").addResourceLocations("classpath:/resources/static/css");
	}

	// view的url重写
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("forward:/user-view/index");
	}

}
