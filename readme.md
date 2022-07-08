# 低代码框架
## 1 关于项目结构和依赖包
> maven中避免重复发明轮子的方法，一种是继承，一种是引用。
* maven中配置引用关系的方法是
```
 <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-dependencies</artifactId>
    <version>${spring-boot.version}</version>
    <type>pom</type>
    <scope>import</scope>
 </dependency>
```
很简单，这样就引入一个pom文件，这样<dependencies>里面的
```
<groupId>org.springframework.cloud</groupId>
<artifactId>spring-cloud-starter-eureka-server</artifactId>
```
由于没有版本信息，就可以参考引入的pom文件的<dependencyManagement>里面的版本信息,就像maven继承方法似的，在父pom的<dependencyManagement>里，放入版本信息，在若干子pom里都省去版本信息了

* 核心依赖包项目lind-dependency：pom.xml里只声明依赖，dependencyManagement管理的依赖，在被引用的子项目里，按着需要引入
* 根项目lind-fast
    * dependencyManagement不仅会引用springboot,spring-cloud,alibaba-cloud的依赖包（POM包），而且还引用lind-dependency的依赖，相当于把lind-dependency里的依赖包import到了根项目，这样方便其它子项目按需引用它
    * dependency引用了所有子项目都会用到了包，这样子项目就不需要再引用了
    * build>pluginManagement中添加了共用的插件声明，在子项目中，可以直接按着需要引用它，子项目中直接使用groupId和artifactId就可以了

