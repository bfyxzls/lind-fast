package com.lind.common.core.util;

import cn.hutool.extra.spring.SpringUtil;
import com.lind.common.core.config.AutoLindWebMvcConfigurer;
import lombok.extern.slf4j.Slf4j;
import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = {AutoLindWebMvcConfigurer.class, SpringUtil.class, StandardPBEStringEncryptor.class})
@Slf4j
public class MsgUtilsTest {
    @Autowired
    StringEncryptor stringEncryptor;

    @Test
    public void china() {
        System.out.println(
                MsgUtils.getMessage("sys.user.update.passwordError"));
    }

    @Test
    public void enc() {
        log.info(stringEncryptor.encrypt("123456"));
    }
}
