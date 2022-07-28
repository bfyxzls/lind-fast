package com.lind.fast.demo.anno;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestExcel {

    /**
     * 前端上传字段名称 file
     */
    String fileName() default "file";

    /**
     * 是否跳过空行
     *
     * @return 默认跳过
     */
    boolean ignoreEmptyRow() default false;

}
