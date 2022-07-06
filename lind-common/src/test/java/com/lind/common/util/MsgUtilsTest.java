package com.lind.common.util;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Import;

public class MsgUtilsTest {
    @Test
    public void china() {
        System.out.println(
                MsgUtils.getMessage("sys.user.update.passwordError"));
    }
}
