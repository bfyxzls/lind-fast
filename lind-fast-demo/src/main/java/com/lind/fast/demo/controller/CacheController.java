package com.lind.fast.demo.controller;

import cn.hutool.core.date.DateUtil;
import com.lind.common.core.util.R;
import com.lind.fast.demo.anno.WhiteList;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lind
 * @date 2022/9/8 9:09
 * @since 1.0.0
 */
@RestController
@RequestMapping("cache")
@RequiredArgsConstructor
public class CacheController {

	private final CacheManager cacheManager;

	/**
	 * 加载缓存，格式：get:key
	 * @param key
	 * @return
	 */
	@GetMapping("hello")
	@Cacheable(value = "get", key = "#key")
	@WhiteList
	public R hello(@RequestParam String key) {
		return R.ok(DateUtil.now());
	}

	/**
	 * 手动删除缓存:get:key
	 * @param key
	 * @return
	 */
	@GetMapping("hello-get")
	public R helloGet(@RequestParam String key) {
		Cache cache = cacheManager.getCache("get");
		if (cache != null && cache.get(key) != null) {
			return R.ok(cache.get(key).get());
		}
		return null;
	}

	/**
	 * 手动加入缓存
	 * @param key
	 * @return
	 */
	@GetMapping("hello-add")
	public R helloAdd(@RequestParam String key) {
		Cache cache = cacheManager.getCache("get");
		if (cache != null) {
			cache.put(key, "什么值");
			return R.ok();
		}
		return null;
	}

	/**
	 * 手动加入缓存
	 * @param key
	 * @return
	 */
	@GetMapping("hello-del")
	public R helloDel(@RequestParam String key) {
		Cache cache = cacheManager.getCache("get");
		if (cache != null) {
			cache.evict(key);
			return R.ok();
		}
		return null;
	}

}
