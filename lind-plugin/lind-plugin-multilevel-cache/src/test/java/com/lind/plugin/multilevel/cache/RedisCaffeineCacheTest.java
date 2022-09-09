package com.lind.plugin.multilevel.cache;

import com.lind.plugin.multilevel.cache.properties.CacheConfigProperties;
import com.lind.plugin.multilevel.cache.properties.RedisConfigProp;
import com.lind.plugin.multilevel.cache.support.RedisCaffeineCacheManager;
import org.junit.jupiter.api.Test;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author lind
 * @date 2022/9/8 8:25
 * @since 1.0.0
 */
public class RedisCaffeineCacheTest {

	@Test
	public void put() {
		RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
		CacheConfigProperties cacheConfigProperties = new CacheConfigProperties();
		cacheConfigProperties.setRedis(new RedisConfigProp());
		RedisCaffeineCacheManager redisCaffeineCache = new RedisCaffeineCacheManager(cacheConfigProperties,
				redisTemplate);
		redisCaffeineCache.caffeineCache().put("ok", "test");
		redisCaffeineCache.caffeineCache().get("ok", (o) -> {
			System.out.println(o);
			return o;
		});
	}

}
