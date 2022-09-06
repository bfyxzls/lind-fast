package com.lind.common.security;

import cn.hutool.core.lang.Assert;
import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author lind
 * @date 2022/9/6 10:42
 * @since 1.0.0
 */
public class PigBearerTokenExtractorTest {

	private static final Pattern authorizationPattern = Pattern.compile("^Bearer (?<token>[a-zA-Z0-9-:._~+/]+=*)$",
			Pattern.CASE_INSENSITIVE);

	@Test
	public void matcher() {
		String authorization = "Bearer 12345abcde";
		Matcher matcher = authorizationPattern.matcher(authorization);
		Assert.isTrue(matcher.matches());
		Assert.equals(matcher.group("token"), "12345abcde");
	}

}
