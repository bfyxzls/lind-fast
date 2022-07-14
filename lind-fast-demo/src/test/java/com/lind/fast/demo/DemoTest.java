package com.lind.fast.demo;

import com.ulisesbocchio.jasyptspringboot.encryptor.DefaultLazyEncryptor;
import lombok.extern.slf4j.Slf4j;
import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.core.env.StandardEnvironment;

/**
 * @author lind
 * @date 2022/7/13 15:57
 * @since 1.0.0
 */
@Slf4j
public class DemoTest {

    @Test
    public void des() {
        System.setProperty("jasypt.encryptor.password", "pig");
        StringEncryptor stringEncryptor = new DefaultLazyEncryptor(new StandardEnvironment());
        //加密方法
        System.out.println(stringEncryptor.encrypt("123456"));
        //解密方法
        System.out.println(stringEncryptor.decrypt("e6ytx260Dh8EfNszc7ktxg=="));
    }

}
