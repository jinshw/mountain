# mountain 工程
> 访问url：http://localhost:8080/mt/index.htm

## 更新日志-20190726
* 集成Druid 连接池
    * pom.xml中添加相应jar包
    ```$xslt
    <dependency>
        <groupId>com.alibaba</groupId>
        <artifactId>druid-spring-boot-starter</artifactId>
        <version>1.1.10</version>
    </dependency>
    ```
   * 修改配置文件
   ```text
    #配置监控统计拦截的filters，去掉后监控界面SQL无法进行统计，'wall'用于防火墙
    spring.datasource.mysql.filters=stat,wall
    spring.datasource.mysql.driverClassName=com.mysql.jdbc.Driver
    spring.datasource.mysql.type = com.alibaba.druid.pool.DruidDataSource
    spring.datasource.mysql.url=jdbc:mysql://127.0.0.1:3306/mountain
    spring.datasource.mysql.username=root
    spring.datasource.mysql.password=root
   ``` 
   * 修改数据源配置类:把`MysqlDataSourceConfig`类中数据源应用到的类都修改为Druid对应的类
   ```java
    @Configuration
    @MapperScan(basePackages = "com.site.mountain.dao.mysql", sqlSessionTemplateRef = "mysqlSqlSessionTemplate")
    public class MysqlDataSourceConfig {
        // @Primary 确定此数据源为master
        @Bean(name = "mysqlDataSource")
        @ConfigurationProperties(prefix = "spring.datasource.mysql")
        @Primary
        public DruidDataSource mysqlDataSource() {
            return DruidDataSourceBuilder.create().build();
        }
      //....    
    }
   ````
   * 设置Druid配置类`DruidConfig.java`
   
   * Druid监控页面URL：[http://172.22.112.130:8080/mt/druid ]
* 存在问题：
   * 访问Druid监控页面URL时，系统必须先登录，因为shiro拦截了访问，但是在shiro放开`druid/**` 访问拦截，会导致系统主页面等链接不能访问，待解决...

## 更新日志-20190711

* 新增定时任务模块

  * 集成quartz 2.2.1插件

    ```xml
    <dependencies>
        ...
        <dependency>
            <groupId>org.quartz-scheduler</groupId>
            <artifactId>quartz</artifactId>
            <version>2.2.1</version>
        </dependency>
        <!-- 该依赖必加，里面有sping对schedule的支持 -->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-context-support</artifactId>
        </dependency>
        ...
    </dependencies>

    ```

    

  * 在quartz 官网中下载相应的数据脚本，并且把数据库刷入到本地数据库中

  * 配置quartz.properties 配置文件

  * 配置SchedulerConfig.java 文件

* 新增分页插件

  ```xml
  <dependency>    
      <groupId>com.github.pagehelper</groupId>    
      <artifactId>pagehelper</artifactId>    
      <version>5.0.0</version>
  </dependency>
  ```



* 填坑：

  * quartz.properties文件中下边这句话必须注释掉，不然会报Bean对象重复注入
  ```
    #org.quartz.scheduler.instanceName = DefaultQuartzScheduler
  ```
  
  * qrtz_triggers.TRIGGER_STATE 这个字段为定时器的状态
  
    

## 更新日志-20190709

* 新增菜单路由查询
* 更新前端工程mountain-element-ui路由菜单渲染
* 更新菜单表字段默认值
* 更新菜单新增/编辑页面元素
* 新增菜单步骤：
  * 在后台系统添加菜单
  * 新增菜单给角色赋权
  * 在前端工程中添加相应的前端工程文件（.vue）



## 更新日志-20190703
* 解决事务失效问题：springboot和shiro框架集成时，先加载shiro，这时sysUserService还没有实例化，导致事务失效，
    在sysUserService 上加载 `@Lazy`
* 级联删除、更新、关联表插入
* 删除代码

## 更新日志-20190628
* 前端框架改为vue-admin-template
* 后端springboot工程代码重构

*****
## 集成
* Spring Boot
* Mybatis
* MySql
* Swagger2
* d2-admin-start-kit UI 框架  (element ui)
* 多数据源mysql、oracle
* logback 日志

## 问题
* 前后端分离跨域问题:  
    前端使用d2-admin-start-kit(vuejs2+elementui)框架,
    后端使用springboot+shiro+mybatis+mysql,导致前端页面调用接口时
    有跨域问题。 现有3种方案，如下（现使用第一种方案）：  
    - 第一种方案：前端用`http-proxy-middleware`插件解决跨域
        在vue.confg.js中配置：
        ~~~
        proxy: {
            '/login': {
                target:'http://127.0.0.1:8080/mt/loginCross', // 你请求的第三方接口
                changeOrigin:true, // 在本地会创建一个虚拟服务端，然后发送请求的数据，并同时接收请求的数据，这样服务端和服务端进行数据的交互就不会有跨域问题
                pathRewrite:{  // 路径重写，
                    '^/login': ''  // 替换target中的请求地址，也就是说以后你在请求http://api.jisuapi.com/XXXXX这个地址的时候直接写成/api即可。
                }
            }
         }
        ~~~
    - 第二种方案：在后台Controller中添加跨域标签注解   
         @CrossOrigin(origins = "http://127.0.0.1:8081")
         
    - 第三种方案：Nginx配置解决跨域（生产系统使用）
    
* 多数据源注意点   
  
    - dao层不能重复：例如在test1和test2数据源dao层中名称不能重复，否则提示注册JavaBean名称重复。
* shiro 问题：
    在protected AuthenticationInfo doGetAuthenticationInfo方法中，代码
    SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(name, userInfo.getPassword().toString(), getName());
    如果数据库中用户名和密码相等，不跳转到/loginCross，待解决……
                