package com.lind.fast.demo.anno;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ResponseExcel {

    /**
     * 文件名称
     *
     * @return string
     */
    String name() default "";

    /**
     * 内存操作
     *
     * @return
     */
    boolean inMemory() default false;

    /**
     * excel 模板
     *
     * @return String
     */
    String template() default "";

    /**
     * + 包含字段
     *
     * @return String[]
     */
    String[] include() default {};

    /**
     * 排除字段
     *
     * @return String[]
     */
    String[] exclude() default {};

    /**
     * excel 头信息国际化
     *
     * @return boolean
     */
    boolean i18nHeader() default false;

    /**
     * 填充模式
     *
     * @return
     */
    boolean fill() default false;

}
