package com.lind.common.swagger.config;

import com.lind.common.swagger.support.SwaggerBasicGatewayFilter;
import com.lind.common.swagger.support.SwaggerProperties;
import com.lind.common.swagger.support.SwaggerProvider;
import com.lind.common.swagger.support.SwaggerResourceHandler;
import com.lind.common.swagger.support.SwaggerSecurityHandler;
import com.lind.common.swagger.support.SwaggerUiHandler;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.cloud.gateway.config.GatewayProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Primary;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.cloud.gateway.config.GatewayProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SecurityConfigurationBuilder;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;


/**
 * @author lind
 * @date 2022/10/8 15:10
 * @since 1.0.0
 */
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.REACTIVE)
@ComponentScan("springfox.documentation.schema")
public class GatewaySwaggerAutoConfiguration {

    @Primary
    @Bean
    public SwaggerProvider swaggerProvider(SwaggerProperties swaggerProperties, GatewayProperties gatewayProperties) {
        return new SwaggerProvider(swaggerProperties, gatewayProperties);
    }

    @Bean
    public SwaggerResourceHandler swaggerResourceHandler(SwaggerProvider swaggerProvider) {
        return new SwaggerResourceHandler(swaggerProvider);
    }

    @Bean
    public WebFluxSwaggerConfiguration fluxSwaggerConfiguration() {
        return new WebFluxSwaggerConfiguration();
    }

    @Bean
    @ConditionalOnProperty(value = "swagger.basic.enabled", havingValue = "true")
    public SwaggerBasicGatewayFilter swaggerBasicGatewayFilter(SwaggerProperties swaggerProperties) {
        return new SwaggerBasicGatewayFilter(swaggerProperties);
    }

    @Bean
    public SwaggerSecurityHandler swaggerSecurityHandler(
            ObjectProvider<SecurityConfiguration> securityConfigurationObjectProvider) {
        SecurityConfiguration securityConfiguration = securityConfigurationObjectProvider
                .getIfAvailable(() -> SecurityConfigurationBuilder.builder().build());
        return new SwaggerSecurityHandler(securityConfiguration);
    }

    @Bean
    public SwaggerUiHandler swaggerUiHandler(ObjectProvider<UiConfiguration> uiConfigurationObjectProvider) {
        UiConfiguration uiConfiguration = uiConfigurationObjectProvider
                .getIfAvailable(() -> UiConfigurationBuilder.builder().build());
        return new SwaggerUiHandler(uiConfiguration);
    }

    @Bean
    public RouterFunction<ServerResponse> swaggerRouterFunction(SwaggerProperties swaggerProperties,
                                                                SwaggerUiHandler swaggerUiHandler, SwaggerSecurityHandler swaggerSecurityHandler,
                                                                SwaggerResourceHandler swaggerResourceHandler) {
        // 开启swagger 匹配路由
        if (swaggerProperties.getEnabled()) {
            return RouterFunctions
                    .route(RequestPredicates.GET("/swagger-resources").and(RequestPredicates.accept(MediaType.ALL)),
                            swaggerResourceHandler)
                    .andRoute(RequestPredicates.GET("/swagger-resources/configuration/ui")
                            .and(RequestPredicates.accept(MediaType.ALL)), swaggerUiHandler)
                    .andRoute(RequestPredicates.GET("/swagger-resources/configuration/security")
                            .and(RequestPredicates.accept(MediaType.ALL)), swaggerSecurityHandler);
        }
        else {
            // 关闭时，返回404
            return RouterFunctions
                    .route(RequestPredicates.GET("/swagger-ui/**").and(RequestPredicates.accept(MediaType.ALL)),
                            serverRequest -> ServerResponse.notFound().build())
                    .andRoute(RequestPredicates.GET("/*/v2/api-docs").and(RequestPredicates.accept(MediaType.ALL)),
                            serverRequest -> ServerResponse.notFound().build());

        }
    }

}
