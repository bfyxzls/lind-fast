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

package com.lind.codegen;

import cn.smallbun.screw.core.Configuration;
import cn.smallbun.screw.core.engine.EngineConfig;
import cn.smallbun.screw.core.engine.EngineFileType;
import cn.smallbun.screw.core.engine.EngineTemplateType;
import cn.smallbun.screw.core.execute.DocumentationExecute;
import cn.smallbun.screw.core.process.ProcessConfig;
import com.lind.common.datasource.annotation.EnableDynamicDataSource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.sql.DataSource;
import java.util.ArrayList;

/**
 * @author lengleng
 * @date 2020/03/11 代码生成模块
 */
@EnableDynamicDataSource
@SpringBootApplication
public class PigCodeGenApplication {

	public static void main(String[] args) {
		SpringApplication.run(PigCodeGenApplication.class, args);
	}

	/**
	 * 生成数据库文档
	 * @param args
	 */
	public static void genDbDocument(String[] args) {
		// 数据源
		HikariConfig hikariConfig = new HikariConfig();
		hikariConfig.setDriverClassName("com.mysql.cj.jdbc.Driver");
		hikariConfig.setJdbcUrl("jdbc:mysql://192.168.60.138:3306/nezha");
		hikariConfig.setUsername("root");
		hikariConfig.setPassword("123456");
		// 设置可以获取tables remarks信息
		hikariConfig.addDataSourceProperty("useInformationSchema", "true");
		hikariConfig.setMinimumIdle(2);
		hikariConfig.setMaximumPoolSize(5);
		DataSource dataSource = new HikariDataSource(hikariConfig);
		// 生成配置
		EngineConfig engineConfig = EngineConfig.builder()
				// 生成文件路径
				.fileOutputDir("d:\\数据库说明文档screw")
				// 打开目录
				.openOutputDir(true)
				// 文件类型
				.fileType(EngineFileType.HTML)
				// 生成模板实现
				.produceType(EngineTemplateType.freemarker).build();

		// 忽略表
		ArrayList<String> ignoreTableName = new ArrayList<>();
		ignoreTableName.add("test_user");
		ignoreTableName.add("test_group");
		// 忽略表前缀
		ArrayList<String> ignorePrefix = new ArrayList<>();
		ignorePrefix.add("test_");
		// 忽略表后缀
		ArrayList<String> ignoreSuffix = new ArrayList<>();
		ignoreSuffix.add("_test");
		ProcessConfig processConfig = ProcessConfig.builder()
				// 指定生成逻辑、当存在指定表、指定表前缀、指定表后缀时，将生成指定表，其余表不生成、并跳过忽略表配置
				// 根据名称指定表生成
				.designatedTableName(new ArrayList<>())
				// 根据表前缀生成
				.designatedTablePrefix(new ArrayList<>())
				// 根据表后缀生成
				.designatedTableSuffix(new ArrayList<>())
				// 忽略表名
				.ignoreTableName(ignoreTableName)
				// 忽略表前缀
				.ignoreTablePrefix(ignorePrefix)
				// 忽略表后缀
				.ignoreTableSuffix(ignoreSuffix).build();
		// 配置
		Configuration config = Configuration.builder()
				// 版本
				.version("1.0.0")
				// 描述
				.description("数据库设计文档生成")
				// 数据源
				.dataSource(dataSource)
				// 生成配置
				.engineConfig(engineConfig)
				// 生成配置
				.produceConfig(processConfig).build();
		// 执行生成
		new DocumentationExecute(config).execute();
	}

}
