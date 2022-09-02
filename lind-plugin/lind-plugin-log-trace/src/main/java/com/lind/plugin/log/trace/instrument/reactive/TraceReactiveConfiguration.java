package com.lind.plugin.log.trace.instrument.reactive;

import com.lind.plugin.log.trace.TraceLogProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author purgeyao
 * @since 1.0
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.REACTIVE)
public class TraceReactiveConfiguration {

	@Bean
	public TraceReactiveFilter traceReactiveFilter(TraceLogProperties traceLogProperties) {
		return new TraceReactiveFilter(traceLogProperties);
	}

}
