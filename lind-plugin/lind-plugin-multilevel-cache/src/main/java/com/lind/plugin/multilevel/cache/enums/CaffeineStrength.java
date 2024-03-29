package com.lind.plugin.multilevel.cache.enums;

import com.github.benmanes.caffeine.cache.Caffeine;

/**
 * {@link Caffeine.Strength}
 */
@SuppressWarnings("JavadocReference")
public enum CaffeineStrength {

	/** 弱引用 */
	WEAK,
	/** 软引用 */
	SOFT

}
