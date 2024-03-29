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

package com.lind.common.mybatis;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.BlockAttackInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.lind.common.mybatis.audit.CurrentAuditor;
import com.lind.common.mybatis.audit.DefaultCurrentAuditor;
import com.lind.common.mybatis.config.MybatisPlusMetaObjectHandler;
import com.lind.common.mybatis.plugins.DeptInterceptor;
import com.lind.common.mybatis.plugins.PigPaginationInnerInterceptor;
import com.lind.common.mybatis.resolver.SqlFilterArgumentResolver;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @author lengleng
 * @date 2020-03-14
 * <p>
 * mybatis plus 统一配置
 */
@Configuration(proxyBeanMethods = false)
public class MybatisAutoConfiguration implements WebMvcConfigurer {

	/**
	 * SQL 过滤器避免SQL 注入
	 * @param argumentResolvers
	 */
	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		argumentResolvers.add(new SqlFilterArgumentResolver());
	}

	@Bean
	@ConditionalOnMissingBean(CurrentAuditor.class)
	public CurrentAuditor defaultCurrentAuditor() {
		return new DefaultCurrentAuditor();
	}

	/**
	 * 分页插件, 对于单一数据库类型来说,都建议配置该值,避免每次分页都去抓取数据库类型
	 */
	@Bean
	public MybatisPlusInterceptor mybatisPlusInterceptor(CurrentAuditor currentAuditor) {
		MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
		// 分页
		mybatisPlusInterceptor.addInnerInterceptor(new PigPaginationInnerInterceptor());

		// 数据权限
		mybatisPlusInterceptor.addInnerInterceptor(new DeptInterceptor(currentAuditor));

		// 乐观锁
		mybatisPlusInterceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());

		// 防止全表更新与删除
		mybatisPlusInterceptor.addInnerInterceptor(new BlockAttackInnerInterceptor());

		return mybatisPlusInterceptor;
	}

	/**
	 * 审计字段自动填充
	 * @return {@link MetaObjectHandler}
	 */
	@Bean
	public MybatisPlusMetaObjectHandler mybatisPlusMetaObjectHandler(CurrentAuditor currentAuditor) {
		return new MybatisPlusMetaObjectHandler(currentAuditor);
	}

}
