package com.lind.common.core.util;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.security.KeyPair;

/**
 * @author lind
 * @date 2022/12/22 14:01
 * @since 1.0.0
 */
public class RSAUtilsTest {

	static final KeyPair keyPair;

	static {
		try {
			keyPair = RSAUtils.getKeyPair();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	// 公钥加密，私钥解密
	@SneakyThrows
	@Test
	public void encAndDec() {
		String dec = RSAUtils.encrypt("hello", keyPair.getPublic());
		System.out.println("dec=" + dec);
		System.out.println(RSAUtils.decrypt(dec, keyPair.getPrivate()));
	}

	// 私钥生成签名，公钥验证签名
	@SneakyThrows
	@Test
	public void signAndVerify() {
		String sign = RSAUtils.sign("hello", keyPair.getPrivate());
		System.out.println("sign=" + sign);
		System.out.println("verify=" + RSAUtils.verify("hello", keyPair.getPublic(), sign));
	}

}
