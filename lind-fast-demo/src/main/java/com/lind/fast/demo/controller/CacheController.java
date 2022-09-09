package com.lind.fast.demo.controller;

import cn.hutool.core.date.DateUtil;
import com.lind.common.core.util.R;
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
public class CacheController {

	@GetMapping("hello")
	@Cacheable(value = "get",key = "#key")
	public R hello(@RequestParam String key) {
		return R.ok(DateUtil.now());
	}

}
