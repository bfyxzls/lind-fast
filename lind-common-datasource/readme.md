# 动态数据源
1. 一个有意思的问题，如果希望将a库的数据备份到b库，两个库结构完全相同，这时可以使用动态数据源功能，在读方法走a数据源，写方法走b数据源。
2. 另外，对于代码生成器来说，也可以通过动态数据源，动态选择数据库和数据表
## 声明动态数据源类或者方法
> 声明了@DS注解的方法或者类，它将走动态数据源的拦截
```
@DS("#last")
Map<String, String> queryTable(@Param("tableName") String tableName, String dsName);
```
## 建立mapper.xml文件，如读取表信息列表
```
<select id="queryTable" resultType="map">
		select table_name tableName, engine, table_comment tableComment, create_time createTime from information_schema.tables
			where table_schema = (select database()) and table_name = #{tableName}
</select>
```
## 配置文件密码加密
```
spring:
  application:
    name: @artifactId@
  datasource:
    type: com.zaxxer.hikari.HikariDataSource
    driver-class-name: com.mysql.cj.jdbc.Driver
    username: root
    password: ENC(e6ytx260Dh8EfNszc7ktxg==)
    url: jdbc:mysql://localhost:3306/pig_codegen?characterEncoding=utf8&useSSL=false&serverTimezone=Asia/Shanghai&rewriteBatchedStatements=true

# 加解密根密码
jasypt:
  encryptor:
    password: pig #根密码
```
