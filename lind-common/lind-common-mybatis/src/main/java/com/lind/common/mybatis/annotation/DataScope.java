package com.lind.common.mybatis.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 数据权限标识
 * @author lind
 * @date 2022/8/4 10:03
 * @since 1.0.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataScope {

	/**
	 * 用户ID
	 */
	String userId() default "create_by";

	/**
	 * 部门ID
	 */
	String deptId() default "dept_id";

}
