package com.lind.common.mybatis.aspect;

import com.lind.common.mybatis.util.DPHelper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Order(1)
@Component
public class DataPermissionAspect {

	/**
	 * 方法的注解覆盖class注解
	 */
	@Around("@annotation(com.lind.common.mybatis.annotation.DataScope)")
	public Object dataPermission(ProceedingJoinPoint point) throws Throwable {
		DPHelper.setLocalDataPermissions(true);
		return point.proceed();
	}

}
