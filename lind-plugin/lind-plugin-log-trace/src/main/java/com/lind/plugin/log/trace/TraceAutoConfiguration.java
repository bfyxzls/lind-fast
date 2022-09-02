package com.lind.plugin.log.trace;

import com.lind.plugin.log.trace.handlers.DefaultTraceMetaObjectHandler;
import com.lind.plugin.log.trace.handlers.TraceMetaObjectHandler;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * @author purgeyao
 * @since 1.0
 */
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(TraceLogProperties.class)
public class TraceAutoConfiguration {

	@Bean
	public TraceContentFactory traceContentFactory(Map<String, TraceMetaObjectHandler> traceMetaObjectHandlerMap) {
		return new TraceContentFactory(traceMetaObjectHandlerMap);
	}

	@Bean
	public DefaultTraceMetaObjectHandler defaultTraceMetaObjectHandler() {
		return new DefaultTraceMetaObjectHandler();
	}

}
