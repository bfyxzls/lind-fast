package com.lind.fast.demo;

import cn.hutool.extra.spring.SpringUtil;
import com.lind.fast.demo.service.Run;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

/**
 * hutool springUtils test.
 *
 * @author lind
 * @date 2022/9/6 10:52
 * @since 1.0.0
 */
@SpringBootTest
public class SpringUtilTest {

	@Test
	public void getImpl() {
		Map<String, Run> userDetailsServiceMap = SpringUtil.getBeansOfType(Run.class);
		userDetailsServiceMap.forEach((i, o) -> {
			o.doing();
		});
	}

}
