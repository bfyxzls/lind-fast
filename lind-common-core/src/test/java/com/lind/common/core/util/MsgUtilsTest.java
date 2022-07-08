package com.lind.common.core.util;

import cn.hutool.extra.spring.SpringUtil;
import com.lind.common.core.config.AutoLindWebMvcConfigurer;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = {AutoLindWebMvcConfigurer.class, SpringUtil.class})
public class MsgUtilsTest {
    @Test
    public void china() {
        System.out.println(
                MsgUtils.getMessage("sys.user.update.passwordError"));
    }
}
