/*
 * Copyright (c) 2020 pig4cloud Authors. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.lind.common.swagger.annotation;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import com.lind.common.swagger.config.GatewaySwaggerAutoConfiguration;
import com.lind.common.swagger.config.SwaggerAutoConfiguration;
import com.lind.common.swagger.support.SwaggerProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 开启 pig spring doc
 *
 * @author lengleng
 * @date 2022-03-26
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@EnableKnife4j
@EnableConfigurationProperties(SwaggerProperties.class)
@Import({ SwaggerAutoConfiguration.class, GatewaySwaggerAutoConfiguration.class })
@ComponentScan("springfox.documentation.schema")
public @interface EnablePigDoc {

}
