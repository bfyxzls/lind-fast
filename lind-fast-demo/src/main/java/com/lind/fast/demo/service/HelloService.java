package com.lind.fast.demo.service;

import com.lind.fast.demo.LindDemoApplication;

/**
 * @author lind
 * @date 2022/7/29 16:46
 * @since 1.0.0
 */
public class HelloService {

	public String get() {
		System.out.println(LindDemoApplication.get());
		return LindDemoApplication.get();
	}

}
