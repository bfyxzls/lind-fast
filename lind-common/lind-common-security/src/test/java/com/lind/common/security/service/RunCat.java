package com.lind.common.security.service;

import org.springframework.stereotype.Component;

/**
 * @author lind
 * @date 2022/9/6 10:53
 * @since 1.0.0
 */
@Component
public class RunCat implements Run {

	@Override
	public void doing() {
		System.out.println("cat running");
	}

}
