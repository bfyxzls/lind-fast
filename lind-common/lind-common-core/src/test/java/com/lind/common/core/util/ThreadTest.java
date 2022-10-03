package com.lind.common.core.util;

import com.lind.common.core.config.TaskExecutorConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.Executor;

/**
 * @author lind
 * @date 2022/9/26 11:08
 * @since 1.0.0
 */
@SpringBootTest(classes = TaskExecutorConfiguration.class)
public class ThreadTest {

	@Autowired
	Executor executor;

	@Test
	public void test() {
		executor.execute(new Runnable() {
			@Override
			public void run() {
				System.out.println(Thread.currentThread().getName());
			}
		});
	}

}
