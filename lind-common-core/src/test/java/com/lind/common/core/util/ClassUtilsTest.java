package com.lind.common.core.util;

import org.junit.jupiter.api.Test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

/**
 * @author lind
 * @date 2022/7/7 15:02
 * @since 1.0.0
 */
public class ClassUtilsTest {
    @Target({ElementType.METHOD})
    public @interface TestAnno{
        String value() default "";

    }
    @TestAnno("lind")
    public void print(){
        System.out.println("hello");
    }

    @Test
    public void getAnnotation(){

    }
}
