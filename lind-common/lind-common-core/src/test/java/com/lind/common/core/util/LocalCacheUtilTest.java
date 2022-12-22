package com.lind.common.core.util;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.util.Date;

/**
 * @author lind
 * @date 2022/12/22 14:11
 * @since 1.0.0
 */
public class LocalCacheUtilTest {

	@SneakyThrows
	@Test
	public void getAndExpire() {
		LocalCacheUtil.set("test", new Date(), 20000);
		while (LocalCacheUtil.get("test")!= null) {
			System.out.println(LocalCacheUtil.get("test"));
			Thread.sleep(1000);
		}
	}

}
