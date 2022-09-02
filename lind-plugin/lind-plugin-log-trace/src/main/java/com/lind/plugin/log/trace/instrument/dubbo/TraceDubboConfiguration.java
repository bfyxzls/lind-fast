package com.lind.plugin.log.trace.instrument.dubbo;

import com.lind.plugin.log.trace.TraceContentFactory;
import org.apache.dubbo.spring.boot.autoconfigure.DubboAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;

/**
 * @author purgeyao
 * @since 1.0
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnClass({ DubboAutoConfiguration.class, TraceContentFactory.class })
public class TraceDubboConfiguration {

}
