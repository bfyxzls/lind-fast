package com.lind.fast.demo.aop;

import com.lind.fast.demo.anno.RequestExcel;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.List;

/**
 * @author lind
 * @date 2022/7/27 9:49
 * @since 1.0.0
 */
public class RequestExcelArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(RequestExcel.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        Class<?> parameterType = parameter.getParameterType();
        if (!parameterType.isAssignableFrom(List.class)) {
            throw new IllegalArgumentException(
                    "Excel upload request resolver error, @RequestExcel parameter is not List " + parameterType);
        }
        return null;
    }
}
