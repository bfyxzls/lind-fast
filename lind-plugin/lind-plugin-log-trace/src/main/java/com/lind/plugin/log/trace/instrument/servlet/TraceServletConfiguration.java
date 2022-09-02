package com.lind.plugin.log.trace.instrument.servlet;

import com.lind.plugin.log.trace.TraceLogProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author purgeyao
 * @since 1.0
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
public class TraceServletConfiguration {

	@Bean
	public TraceServletFilter traceServletFilter(TraceLogProperties traceLogProperties) {
		return new TraceServletFilter(traceLogProperties);
	}

}
