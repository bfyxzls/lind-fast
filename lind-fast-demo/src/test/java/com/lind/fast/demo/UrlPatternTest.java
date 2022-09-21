package com.lind.fast.demo;

import cn.hutool.core.util.ReUtil;
import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

/**
 * @author lind
 * @date 2022/9/13 14:00
 * @since 1.0.0
 */
public class UrlPatternTest {

	private static final Pattern PATTERN = Pattern.compile("\\{(.*?)\\}");

	@Test
	public void replace() {
		String url = "/hello/get";
		url = ReUtil.replaceAll(url, PATTERN, "*");
		System.out.println("url=" + url);
		System.out.println("url=" + ReUtil.replaceAll("/hello/{name}", PATTERN, "*"));

	}

}
