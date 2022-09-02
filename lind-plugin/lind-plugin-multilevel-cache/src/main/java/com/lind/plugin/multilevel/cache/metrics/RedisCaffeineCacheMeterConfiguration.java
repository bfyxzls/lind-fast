package com.lind.plugin.multilevel.cache.metrics;

import io.micrometer.core.instrument.binder.MeterBinder;
import org.springframework.boot.actuate.metrics.cache.CacheMeterBinderProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configure
 * {@link CacheMeterBinderProvider} beans.
 *
 * @author L.cm
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnClass({ MeterBinder.class, CacheMeterBinderProvider.class })
public class RedisCaffeineCacheMeterConfiguration {

	@Bean
	public RedisCaffeineCacheMeterBinderProvider redisCaffeineCacheMeterBinderProvider() {
		return new RedisCaffeineCacheMeterBinderProvider();
	}

}
