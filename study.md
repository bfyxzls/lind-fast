* @Configuration(proxyBeanMethods = false),Spring 5.2.0+的版本，建议你的配置类均采用Lite模式去做，即显示设置proxyBeanMethods = false。Spring Boot在2.2.0版本（依赖于Spring 5.2.0）起就把它的所有的自动配置类的此属性改为了false，即@Configuration(proxyBeanMethods = false)，提高Spring启动速度
* @AutoConfigureAfter(DataSourceAutoConfiguration.class) 在加载DataSourceAutoConfiguration之后，再加载当前类
* @ConfigurationProperties("spring.datasource")声明在配置类上，通过@EnableConfigurationProperties(DataSourceProperties.class)来开启配置类功能
* 
