package com.lind.fast.demo;

import com.lind.fast.demo.aop.RequestExcelArgumentResolver;
import com.lind.fast.demo.aop.ResponseExcelReturnValueHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lind
 * @date 2022/7/27 9:24
 * @since 1.0.0
 */
@Configuration
@RequiredArgsConstructor
public class AutoConfig {

    private final RequestMappingHandlerAdapter requestMappingHandlerAdapter;
    /**
     * 追加 Excel返回值处理器 到 springmvc 中，@PostConstruct 注释的方法,会在构造方法之后,init方法之前进行调用
     */
    @PostConstruct
    public void setReturnValueHandlers() {
        List<HandlerMethodReturnValueHandler> returnValueHandlers = requestMappingHandlerAdapter
                .getReturnValueHandlers();

        List<HandlerMethodReturnValueHandler> newHandlers = new ArrayList<>();
        newHandlers.add(new ResponseExcelReturnValueHandler());
        assert returnValueHandlers != null;
        newHandlers.addAll(returnValueHandlers);
        requestMappingHandlerAdapter.setReturnValueHandlers(newHandlers);
    }

    /**
     * 追加 Excel 请求处理器 到 springmvc 中
     */
    @PostConstruct
    public void setRequestExcelArgumentResolver() {
        List<HandlerMethodArgumentResolver> argumentResolvers = requestMappingHandlerAdapter.getArgumentResolvers();
        List<HandlerMethodArgumentResolver> resolverList = new ArrayList<>();
        resolverList.add(new RequestExcelArgumentResolver());
        resolverList.addAll(argumentResolvers);
        requestMappingHandlerAdapter.setArgumentResolvers(resolverList);
    }
}
