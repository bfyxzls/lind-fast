package com.lind.fast.demo;

import com.lind.fast.demo.handler.ImageCodeHandler;
import com.lind.fast.demo.service.HelloService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.RouterFunctions;
import org.springframework.web.servlet.function.ServerResponse;

/**
 * @author lind
 * @date 2022/7/27 15:42
 * @since 1.0.0
 */
@Configuration(proxyBeanMethods = false)
@RequiredArgsConstructor
public class RouterFunctionConfiguration {

	@Bean
	public RouterFunction<ServerResponse> routerFunction() {
		return RouterFunctions.route(
				RequestPredicates.path("/code").and(RequestPredicates.accept(MediaType.TEXT_PLAIN)),
				imageCodeHandler());
	}

	@Bean
	public ImageCodeHandler imageCodeHandler() {
		return new ImageCodeHandler();
	}

	@Bean("helloService")
	public HelloService helloService(){
		return new HelloService();
	}
}
