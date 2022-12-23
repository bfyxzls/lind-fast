package com.lind.common.core.util;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

/**
 * @author lind
 * @date 2022/7/26 11:06
 * @since 1.0.0
 */
@SpringBootTest()
@TestPropertySource("classpath:application.properties")  //配置文件注入@Slf4j
public class I18NUtilTest {

    @Test
    public void I18nConfigUtil() {
        System.out.println(
                I18nUtil.getString("sys.user.update.passwordError"));
    }

}
