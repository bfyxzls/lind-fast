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

package com.lind.fast.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lind.common.mybatis.annotation.DataScope;
import com.lind.fast.demo.entity.TestUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 代码生成器
 *
 * @author lengleng
 * @date 2018-07-30
 */
@Mapper
public interface TestUserMapper extends BaseMapper<TestUser> {

	@Select("select u.* from test_user u"	)
	@DataScope
	List<TestUser> selectTestList();

}
