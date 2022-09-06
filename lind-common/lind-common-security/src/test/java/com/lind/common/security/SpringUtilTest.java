package com.lind.common.security;

import com.lind.common.security.service.Run;
import com.lind.common.security.service.RunCat;
import com.lind.common.security.service.RunDog;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.AnnotationConfigUtils;
import org.springframework.context.support.GenericApplicationContext;

/**
 * hutool springUtils test.
 *
 * @author lind
 * @date 2022/9/6 10:52
 * @since 1.0.0
 */
public class SpringUtilTest {

	@Test
	public void getImpl() {
		GenericApplicationContext context = new GenericApplicationContext();
		RootBeanDefinition runDog = new RootBeanDefinition(RunDog.class);
		RootBeanDefinition runCat = new RootBeanDefinition(RunCat.class);
		context.registerBeanDefinition("runDog", runDog);
		context.registerBeanDefinition("runCat", runCat);
		AnnotationConfigUtils.registerAnnotationConfigProcessors(context);
		context.refresh();
		for (String s : context.getBeanNamesForType(Run.class)) {
			((Run) context.getBean(s)).doing();
		}
	}

}
