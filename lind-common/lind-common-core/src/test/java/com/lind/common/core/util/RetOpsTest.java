package com.lind.common.core.util;

import org.junit.jupiter.api.Test;

/**
 * @author lind
 * @date 2022/12/22 13:41
 * @since 1.0.0
 */
public class RetOpsTest {
    // 使用场景1: 链式操作: 断言然后消费
    @Test
    public void chain(){
        R<Integer> result = R.restResult(0,-1,"成功");
        RetOps.of(result)
                .assertCode(-1,r -> new RuntimeException("error "+r.getCode()))
                .assertDataNotEmpty(r -> new IllegalStateException("oops!"))
                .useData(System.out::println);
    }

    @Test
    public void chainTrue(){
        R<Integer> result = R.restResult(0,1,"成功");
        RetOps.of(result)
                .assertCode(1,r -> new RuntimeException("error "+r.getCode()))
                .useData(System.out::println);
    }
}
