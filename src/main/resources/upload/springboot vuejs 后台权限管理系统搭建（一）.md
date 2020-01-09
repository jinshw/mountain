# Springboot 后台框架搭建
## 项目简介
```text
	项目名称定义为mountain（山）, 主要实现后端权限管理系统，包括用户管理、角色管理、部门管理、菜单管理等。项目采用前后端分离模式开发，后端使用springboot+shiro+mybatis+MySQL等；前端选用Element UI框架，直接基于vue-element-admin的基础上扩展开发。
```

[查看项目地址](https://github.com/jinshw/mountain)

## 工程目录说明

![](E:\项目\学习项目\自己博客文章\博客文章\imgs\package.jpg)

## 集成jar包

```text
集成 Springboot、Shiro、Mybatis、Druid等jar包
```



* 在pom.xml 文件中引用相对应的jar包

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.site</groupId>
    <artifactId>mountain</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <packaging>jar</packaging>

    <name>mountain</name>
    <description>Demo project for Spring Boot</description>

    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.1.3.RELEASE</version>
        <relativePath/> <!-- lookup parent from repository -->
    </parent>

    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
        <java.version>1.8</java.version>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
            <version>2.1.3.RELEASE</version>
        </dependency>
        <dependency>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-starter</artifactId>
            <version>1.3.2</version>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>druid-spring-boot-starter</artifactId>
            <version>1.1.10</version>
        </dependency>
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>5.1.38</version>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <optional>true</optional>
        </dependency>
        <!-- swagger2 -->
        <dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-swagger2</artifactId>
            <version>2.9.2</version>
        </dependency>
        <dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-swagger-ui</artifactId>
            <version>2.9.2</version>
        </dependency>
        <!--// swagger2 -->
        <!-- 添加oracle jdbc driver -->
        <dependency>
            <groupId>com.oracle</groupId>
            <artifactId>ojdbc14</artifactId>
            <version>10.2.0.2</version>
        </dependency>

        <dependency>
            <groupId>org.apache.shiro</groupId>
            <artifactId>shiro-spring</artifactId>
            <version>1.4.0</version>
        </dependency>
        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-api</artifactId>
            <version>1.7.25</version>
        </dependency>
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>fastjson</artifactId>
            <version>1.1.43</version>
        </dependency>
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
        <dependency>
            <groupId>com.github.pagehelper</groupId>
            <artifactId>pagehelper</artifactId>
            <version>5.0.0</version>
        </dependency>

    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>

            <!-- Docker maven plugin -->
            <plugin>
                <groupId>com.spotify</groupId>
                <artifactId>docker-maven-plugin</artifactId>
                <version>1.2.0</version>
                <configuration>
                    <!-- docker私服的地址 -->
                    <dockerHost>http://192.168.75.129:2375</dockerHost>
                    <!--镜像名称以及版本号-->
                    <imageName>mountain:1.0.0</imageName>
                    <!--依赖的基础镜像-->
                    <baseImage>java</baseImage>
                    <!--Dockerfile的位置 -->
                    <dockerDirectory>${project.basedir}/src/main/docker</dockerDirectory>
                    <!-- 这里是复制 jar 包到 docker 容器指定目录配置 -->
                    <resources>
                        <resource>
                            <targetPath>/</targetPath>
                            <directory>${project.build.directory}</directory>
                            <include>${project.build.finalName}.jar</include>
                        </resource>
                    </resources>
                </configuration>
            </plugin>
            <!-- Docker maven plugin -->

        </plugins>
    </build>

    <repositories>
        <repository>
            <!-- Maven 自带的中央仓库使用的Id为central 如果其他的仓库声明也是用该Id
            就会覆盖中央仓库的配置 -->
            <id>mvnrepository</id>
            <name>mvnrepository</name>
            <url>http://www.mvnrepository.com/</url>
            <layout>default</layout>
            <releases>
                <enabled>true</enabled>
            </releases>
            <snapshots>
                <enabled>false</enabled>
            </snapshots>
        </repository>
    </repositories>

    <pluginRepositories>
        <pluginRepository>
            <id>aliyun</id>
            <name>aliyun</name>
            <url>http://maven.aliyun.com/nexus/content/groups/public/</url>
            <snapshots>
                <enabled>true</enabled>
            </snapshots>
        </pluginRepository>
        <pluginRepository>
            <id>spring-snapshots</id>
            <name>Spring Snapshots</name>
            <url>https://repo.spring.io/snapshot</url>
            <snapshots>
                <enabled>true</enabled>
            </snapshots>
        </pluginRepository>
        <pluginRepository>
            <id>spring-milestones</id>
            <name>Spring Milestones</name>
            <url>https://repo.spring.io/milestone</url>
            <snapshots>
                <enabled>false</enabled>
            </snapshots>
        </pluginRepository>
    </pluginRepositories>


</project>

```



## springboot相关配置

### application 配置文件

```properties
server.port=8080 # 访问端口号
server.servlet.context-path=/mt # 访问项目名称
server.servlet.session.timeout=10 # session失效实现
#MVC
spring.mvc.view.prefix=/
spring.mvc.view.suffix=.html # 设置后缀名称
spring.resources.static-locations=classpath:/static/dist/ #静态文件可访问路径


#当遇到同样名字的时候，是否允许覆盖注册
#spring.main.allow-bean-definition-overriding=true

#配置监控统计拦截的filters，去掉后监控界面SQL无法进行统计，'wall'用于防火墙
spring.datasource.mysql.filters=stat,wall
spring.datasource.mysql.driverClassName=com.mysql.jdbc.Driver
spring.datasource.mysql.type = com.alibaba.druid.pool.DruidDataSource
spring.datasource.mysql.url=jdbc:mysql://127.0.0.1:3306/mountain
spring.datasource.mysql.username=root
spring.datasource.mysql.password=root


#spring.datasource.mysql.driverClassName=com.mysql.jdbc.Driver
## 这里配置springboot2 默认Hikari连接池，不识别url，需要用jdbc-url,不然报错jdbcUrl is required with driverClassName.
#spring.datasource.mysql.jdbc-url=jdbc:mysql://127.0.0.1:3306/mountain
#spring.datasource.mysql.username=root
#spring.datasource.mysql.password=root

mybatis.type-aliases-package=com.site.mountain.entity
```

###  注意点

* springboot2 默认Hikari连接池，可以直接使用不需要引用其他额外jar包
* 本项目中使用的连接池是Druid，请关注与Hikari连接池配置时的不同点

## Shiro相关配置

**路径**

![](E:\项目\学习项目\自己博客文章\博客文章\imgs\shiro-01.png)

### ShiroConfig类

```java
package com.site.mountain.config;

import com.site.mountain.filter.ShiroUserFilter;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {
    //将自己的验证方式加入容器
    @Bean
    public MyShiroRealm myShiroRealm() {
        MyShiroRealm myShiroRealm = new MyShiroRealm();
        return myShiroRealm;
    }

    @Bean
    public SessionManager sessionManager() {
        MySessionManager mySessionManager = new MySessionManager();
        return mySessionManager;
    }

    //权限管理，配置主要是Realm的管理认证
    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(myShiroRealm());
        // 自定义session管理
        securityManager.setSessionManager(sessionManager());
        return securityManager;
    }

    //Filter工厂，设置对应的过滤条件和跳转条件
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        /**
         * 覆盖默认的user拦截器(默认拦截器解决不了ajax请求 session超时的问题,若有更好的办法请及时反馈作者)
         */
        Map<String, Filter> filters = new LinkedHashMap<>();
        ShiroUserFilter shiroUserFilter = new ShiroUserFilter();
        // 名字不能自己随意写，要用shiro提供的anon、authc、user
        filters.put("authc", shiroUserFilter);
        shiroFilterFactoryBean.setFilters(filters);


        /**
         * 配置shiro拦截器链
         *
         * anon  不需要认证
         * authc 需要认证
         * user  验证通过或RememberMe登录的都可以
         *
         * 当应用开启了rememberMe时,用户下次访问时可以是一个user,但不会是authc,因为authc是需要重新认证的
         *
         * 顺序从上到下,优先级依次降低
         *
         */
        Map<String, String> map = new HashMap<String, String>();
        //登出
        map.put("/logout", "logout");
        //对所有用户认证
        map.put("/**", "authc");
        //start 访问设置
        map.put("/swagger-ui.html", "anon");
        map.put("/swagger-resources", "anon");
        map.put("/v2/api-docs", "anon");
        map.put("/webjars/springfox-swagger-ui/**", "anon");
        map.put("/sysuser/login","anon");
        map.put("/sysuser/info","anon");
        map.put("/sysuser/logout","anon");
        map.put("/index.html","anon");
        map.put("/*.ico","anon");
        map.put("/static/**","anon");
//        map.put("/**","user");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);

        //登录(默认的登陆访问url)
        shiroFilterFactoryBean.setLoginUrl("/sysuser/login");
        //首页(登陆成功后跳转的url)
        shiroFilterFactoryBean.setSuccessUrl("/index");
        //没有权限跳转的url(错误页面，认证不通过跳转)
        shiroFilterFactoryBean.setUnauthorizedUrl("/invalid");

        return shiroFilterFactoryBean;
    }

    /**
     * 开启Shiro的注解(如@RequiresRoles,@RequiresPermissions),
     * 需借助SpringAOP扫描使用Shiro注解的类,并在必要时进行安全逻辑验证
     * 配置以下两个bean(DefaultAdvisorAutoProxyCreator和AuthorizationAttributeSourceAdvisor)即可实现此功能
     *
     * @return
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

    //加入注解的使用，不加入这个注解不生效
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
}

```

### SchedulerConfig类

```java
package com.site.mountain.config;

import org.quartz.Scheduler;
import org.quartz.ee.servlet.QuartzInitializerListener;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.io.IOException;
import java.util.Properties;

@Configuration
public class SchedulerConfig {

    @Bean(name = "SchedulerFactory")
    public SchedulerFactoryBean schedulerFactoryBean() throws IOException {
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        factory.setQuartzProperties(quartzProperties());
        return factory;
    }

    @Bean
    public Properties quartzProperties() throws IOException {
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        //propertiesFactoryBean.setLocation(new ClassPathResource("/quartz.properties"));
        //在quartz.properties中的属性被读取并注入后再初始化对象
        propertiesFactoryBean.afterPropertiesSet();
        return propertiesFactoryBean.getObject();
    }

    /*
     * quartz初始化监听器
     */
    @Bean
    public QuartzInitializerListener executorListener() {
        return new QuartzInitializerListener();
    }

    /*
     * 通过SchedulerFactoryBean获取Scheduler的实例
     */
    @Bean
    public Scheduler scheduler() throws IOException {
        return schedulerFactoryBean().getScheduler();
    }

}
```

### MyShiroRealm类

```java
package com.site.mountain.config;

import com.site.mountain.entity.SysMenu;
import com.site.mountain.entity.SysRole;
import com.site.mountain.entity.SysUser;
import com.site.mountain.service.SysUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.util.List;

public class MyShiroRealm extends AuthorizingRealm {
    @Autowired
    @Lazy
    private SysUserService sysUserService;

    //角色权限和对应权限添加
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取登录用户名
        String name = (String) principalCollection.getPrimaryPrincipal();
        SysUser sysUser = new SysUser();
        sysUser.setUsername(name);
        //查询用户名称
        List<SysUser> list = sysUserService.selectAllUserAndRoles(sysUser);
        SysUser userInfo = null;
        if (list.size() != 0) {
            userInfo = list.get(0);
        }
        //添加角色和权限
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        for (SysRole role : userInfo.getRoleList()) {
            //添加角色
            simpleAuthorizationInfo.addRole(role.getRoleName());
            for (SysMenu sysMenu : userInfo.getMenuList()) {
                //添加权限
                simpleAuthorizationInfo.addStringPermission(sysMenu.getPerms());
            }
        }
        return simpleAuthorizationInfo;
    }

    //用户认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //加这一步的目的是在Post请求的时候会先进认证，然后在到请求
        if (authenticationToken.getPrincipal() == null) {
            return null;
        }
        //获取用户信息
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        String name = usernamePasswordToken.getUsername();
        SysUser sysUser = new SysUser();
        sysUser.setUsername(name);
        List<SysUser> list = sysUserService.selectAllUserAndRoles(sysUser);
        SysUser userInfo = null;
        if (list.size() != 0) {
            userInfo = list.get(0);
        }
        if (userInfo == null) {
            //这里返回后会报出对应异常
            return null;
        } else {
            //这里验证authenticationToken和simpleAuthenticationInfo的信息
            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(name, userInfo.getPassword().toString(), getName());
            return simpleAuthenticationInfo;
        }

    }
}

```



### 注意点

* ShiroConfig中配置shiro拦截器链，配置拦截和拦截的路径

* 在MyShiroRealm中添加角色权限和对应权限，并且添加用户认证。

* 如果添加了权限，例如下面MyShiroRealm中代码

  ```java
  //添加角色和权限
  SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
  for (SysRole role : userInfo.getRoleList()) {
      //添加角色
      simpleAuthorizationInfo.addRole(role.getRoleName());
      for (SysMenu sysMenu : userInfo.getMenuList()) {
          //添加权限
          simpleAuthorizationInfo.addStringPermission(sysMenu.getPerms());
      }
  }
  ```

  例如用户列表接口`sysuser/list` 你添加了`userInfo:view` 权限，那么你在controller这个接口上必须添加

  `@RequiresPermissions`,如下面代码：

  ```java
  @RequiresPermissions("userInfo:view")
  @RequestMapping(value = "list", method = RequestMethod.POST)
  @ResponseBody
  public JSONObject findList(@RequestBody SysUser sysUser, HttpServletRequest request, HttpServletResponse response){
          ......
      }
  ```

  

## Mybatis相关配置

### 配置文件

* 在application.properties 配置文件中添加mybatis包扫描

  ```properties
  mybatis.type-aliases-package=com.site.mountain.entity
  ```

* 在springboot启动类上开启事务注解

  ```java
  @SpringBootApplication
  @EnableTransactionManagement   //开启事物管理功能
  public class MountainApplication {
  
      public static void main(String[] args) {
          SpringApplication.run(MountainApplication.class, args);
      }
  }
  ```

### 注意点

* 事务失效：springboot和shiro框架集成时，先加载shiro，这时sysUserService还没有实例化，导致事务失效，    在sysUserService 上加载 `@Lazy` 

## 配置多数据源

### 配置文件 

```properties
server.port=8080
server.servlet.context-path=/mt
server.servlet.session.timeout=10
#MVC
spring.mvc.view.prefix=/
spring.mvc.view.suffix=.html
spring.resources.static-locations=classpath:/static/dist/

spring.profiles.active=dev

#当遇到同样名字的时候，是否允许覆盖注册
#spring.main.allow-bean-definition-overriding=true

#配置监控统计拦截的filters，去掉后监控界面SQL无法进行统计，'wall'用于防火墙
spring.datasource.mysql.filters=stat,wall
spring.datasource.mysql.driverClassName=com.mysql.jdbc.Driver
spring.datasource.mysql.type = com.alibaba.druid.pool.DruidDataSource
spring.datasource.mysql.url=jdbc:mysql://127.0.0.1:3306/mountain
spring.datasource.mysql.username=root
spring.datasource.mysql.password=root


#spring.datasource.mysql.driverClassName=com.mysql.jdbc.Driver
## 这里配置springboot2 默认Hikari连接池，不识别url，需要用jdbc-url,不然报错jdbcUrl is required with driverClassName.
#spring.datasource.mysql.jdbc-url=jdbc:mysql://127.0.0.1:3306/mountain
#spring.datasource.mysql.username=root
#spring.datasource.mysql.password=root


spring.datasource.test1.driverClassName=com.mysql.jdbc.Driver
# 这里配置springboot2 默认Hikari连接池，不识别url，需要用jdbc-url,不然报错jdbcUrl is required with driverClassName.
spring.datasource.test1.jdbc-url=jdbc:mysql://127.0.0.1:3306/mountain
spring.datasource.test1.username=root
spring.datasource.test1.password=root
#spring.datasource.test1.type=com.alibaba.druid.pool.DruidDataSource
#spring.datasource.test1.initialSize=5
#spring.datasource.test1.minIdle=5
#spring.datasource.test1.maxActive=20
spring.datasource.test2.driverClassName=com.mysql.jdbc.Driver
# 这里配置springboot2 默认Hikari连接池，不识别url，需要用jdbc-url,不然报错jdbcUrl is required with driverClassName.
spring.datasource.test2.jdbc-url=jdbc:mysql://127.0.0.1:3306/xjone
spring.datasource.test2.username=root
spring.datasource.test2.password=root
#spring.datasource.test2.type=com.alibaba.druid.pool.DruidDataSource
#spring.datasource.test2.initialSize=5
#spring.datasource.test2.minIdle=5
#spring.datasource.test2.maxActive=20
#mybatis.mapper-locations=mapping/*.xml
oracle.datasource.url=jdbc:oracle:thin:@192.168.25.142:1521:helowin
oracle.datasource.username=SJZX_ODS
oracle.datasource.password=SJZX_ODS
oracle.datasource.driverClassName=oracle.jdbc.driver.OracleDriver

mybatis.type-aliases-package=com.site.mountain.entity



```



### MySQL数据源配置类

```java
package com.site.mountain.datasource;


import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

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

    @Bean(name = "mysqlSqlSessionFactory")
    @Primary
    public SqlSessionFactory mysqlSqlSessionFactory(@Qualifier("mysqlDataSource") DruidDataSource druidDataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(druidDataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/mapper/mysql/*.xml"));
        return bean.getObject();
    }

    //配置事务管理器
    @Bean(name = "mysqlTransactionManager")
    @Primary
    public DataSourceTransactionManager mysqlTransactionManager(@Qualifier("mysqlDataSource") DruidDataSource druidDataSource) {
        return new DataSourceTransactionManager(druidDataSource);
    }

    @Bean(name = "mysqlSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate mysqlSqlSessionTemplate(@Qualifier("mysqlSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}

```

### Oracle 数据源配置类

```java
package com.site.mountain.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = OracleDataSourceConfig.PACKAGE, sqlSessionTemplateRef = "oracleSqlSessionTemplate")
public class OracleDataSourceConfig {

    // 精确到 oracle 目录，以便跟其他数据源隔离
    static final String PACKAGE = "com.site.mountain.dao.oracle";
    static final String MAPPER_LOCATION = "classpath:mybatis/mapper/oracle/*.xml";

    @Value("${oracle.datasource.url}")
    private String url;

    @Value("${oracle.datasource.username}")
    private String user;

    @Value("${oracle.datasource.password}")
    private String password;

    @Value("${oracle.datasource.driverClassName}")
    private String driverClass;

    @Bean(name = "oracleDataSource")
    public DataSource oracleDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClass);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean(name = "oracleTransactionManager")
    public DataSourceTransactionManager oracleTransactionManager() {
        return new DataSourceTransactionManager(oracleDataSource());
    }

    @Bean(name = "oracleSqlSessionFactory")
    public SqlSessionFactory oracleSqlSessionFactory(@Qualifier("oracleDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(OracleDataSourceConfig.MAPPER_LOCATION));
        return bean.getObject();
    }

    @Bean(name = "oracleSqlSessionTemplate")
    public SqlSessionTemplate testSqlSessionTemplate(@Qualifier("oracleSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
```



### 注意点

* 数据源类中配置了Mybatis对应的Mapper.xml文件路径

* MySQL 和Oracle对应配置文件中的配置的方式是不一样的，MySQL直接注解`@ConfigurationProperties(prefix = "spring.datasource.mysql")` ,而Oracle是使用的

  ` @Value("${oracle.datasource.url}")` 



## 配置日志

由于springboot2 默认引入logback相关的jar包，即下面的jar包中关联引用了

```xml
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>2.1.3.RELEASE</version>
</parent>
```

### logback 配置文件

```xml
<?xml version="1.0" encoding="UTF-8"?>
<configuration debug="true">
    <!-- 项目名称 -->
    <property name="PROJECT_NAME" value="mountain" />

    <!--定义日志文件的存储地址 勿在 LogBack 的配置中使用相对路径-->
    <property name="LOG_HOME" value="${catalina.base}/logs" />

    <!-- 控制台输出 -->
    <appender name="CONSOLE" class="ch.qos.logback.core.ConsoleAppender">
        <!--<withJansi>true</withJansi>-->
        <encoder class="ch.qos.logback.classic.encoder.PatternLayoutEncoder">
            <!--格式化输出：%d表示日期，%thread表示线程名，%-5level：级别从左显示5个字符宽度%msg：日志消息，%n是换行符-->
            <pattern>[%d{yyyy-MM-dd HH:mm:ss.SSS}] [%thread] %highlight([%-5level] %logger{50} - %msg%n)</pattern>
            <charset>UTF-8</charset>
        </encoder>
    </appender>

    <!-- 系统错误日志文件 -->
    <appender name="SYSTEM_FILE"  class="ch.qos.logback.core.rolling.RollingFileAppender">
        <!-- 过滤器，只打印ERROR级别的日志 -->
        <filter class="ch.qos.logback.classic.filter.LevelFilter">
            <level>ERROR</level>
            <onMatch>ACCEPT</onMatch>
            <onMismatch>DENY</onMismatch>
        </filter>
        <rollingPolicy class="ch.qos.logback.core.rolling.SizeAndTimeBasedRollingPolicy">
            <!--日志文件输出的文件名-->
            <FileNamePattern>${LOG_HOME}/${PROJECT_NAME}.system_error.%d{yyyy-MM-dd}.%i.log</FileNamePattern>
            <!--日志文件保留天数-->
            <MaxHistory>15</MaxHistory>
            <!--日志文件最大的大小-->
            <MaxFileSize>10MB</MaxFileSize>
        </rollingPolicy>

        <encoder class="ch.qos.logback.classic.encoder.PatternLayoutEncoder">
            <!--格式化输出：%d表示日期，%thread表示线程名，%-5level：级别从左显示5个字符宽度%msg：日志消息，%n是换行符-->
            <pattern>[%d{yyyy-MM-dd HH:mm:ss.SSS}] [%thread] [%-5level] %logger{50} - %msg%n</pattern>
            <charset>UTF-8</charset>
        </encoder>
    </appender>
    <logger name="system_error" additivity="true">
        <appender-ref ref="SYSTEM_FILE"/>
    </logger>

    <!-- 自己打印的日志文件，用于记录重要日志信息 -->
    <appender name="MY_INFO_FILE"  class="ch.qos.logback.core.rolling.RollingFileAppender">
        <!-- 过滤器，只打印ERROR级别的日志 -->
        <filter class="ch.qos.logback.classic.filter.LevelFilter">
            <level>INFO</level>
            <onMatch>ACCEPT</onMatch>
            <onMismatch>DENY</onMismatch>
        </filter>
        <rollingPolicy class="ch.qos.logback.core.rolling.SizeAndTimeBasedRollingPolicy">
            <!--日志文件输出的文件名-->
            <FileNamePattern>${LOG_HOME}/${PROJECT_NAME}.my_info.%d{yyyy-MM-dd}.%i.log</FileNamePattern>
            <!--日志文件保留天数-->
            <MaxHistory>15</MaxHistory>
            <!--日志文件最大的大小-->
            <MaxFileSize>10MB</MaxFileSize>
        </rollingPolicy>

        <encoder class="ch.qos.logback.classic.encoder.PatternLayoutEncoder">
            <!--格式化输出：%d表示日期，%thread表示线程名，%-5level：级别从左显示5个字符宽度%msg：日志消息，%n是换行符-->
            <pattern>[%d{yyyy-MM-dd HH:mm:ss.SSS}] [%thread] [%-5level] %logger{50} - %msg%n</pattern>
            <charset>UTF-8</charset>
        </encoder>
    </appender>
    <logger name="my_info" additivity="true">
        <appender-ref ref="MY_INFO_FILE"/>
    </logger>

    <!-- 开发环境下的日志配置 -->
    <springProfile name="dev">
        <root level="INFO">
            <appender-ref ref="CONSOLE" />
            <appender-ref ref="SYSTEM_FILE" />
        </root>
    </springProfile>

    <!-- 生产环境下的日志配置 -->
    <springProfile name="prod">
        <root level="INFO">
            <appender-ref ref="SYSTEM_FILE" />
        </root>
    </springProfile>
</configuration>
```

### 注意点 

* 各个环境（开发、测试、生产）的日志输出级别
* 输出日志大小、保留的时间、路径。



## 其他

### 更改springboot启动图案

* 首先获图案：去http://patorjk.com/software/taag/#p=testall&f=Zodi&t=mountain 网站，输入需要生成的文字，例如输入mountain
* 把生成的文字复制保存为banner.txt 文件，把这个文件放到`mountain\src\main\resources` 路径下，这样在启动程序，图案就变成自定义了



## 后端项目地址

mountain项目地址：https://github.com/jinshw/mountain



## 待续...

* 集成Swagger2
* 集成quartz框架
* 集成docker
* 前端工程搭建：基于vue-element-admin扩展
* 权限管理系统实现：用户管理、角色管理、部门管理、菜单管理等模块

