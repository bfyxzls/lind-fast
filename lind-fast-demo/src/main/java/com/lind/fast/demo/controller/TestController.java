package com.lind.fast.demo.controller;

import cn.hutool.extra.spring.EnableSpringUtil;
import com.lind.common.core.util.MsgUtils;
import com.lind.common.core.util.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lind
 * @date 2022/7/7 10:50
 * @since 1.0.0
 */
@RestController
@EnableSpringUtil
public class TestController {
    @GetMapping("hello")
    public R hello() {
        return R.ok(MsgUtils.getMessage("sys.user.query.error"));
    }
}
