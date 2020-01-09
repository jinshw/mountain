# SpringBoot后台权限管理系统搭建（四)—部署

## 集成Swagger2 

### 集成jar包

```xml
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
```



### 文件配置

* 启动类

```java
package com.site.mountain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2 {
    //swagger2的配置文件，这里可以配置swagger2的一些基本的内容，比如扫描的包等等
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())//调用apiInfo方法,创建一个ApiInfo实例,里面是展示在文档页面信息内容
                .select()
                //控制暴露出去的路径下的实例
                //如果某个接口不想暴露,可以使用以下注解
                //@ApiIgnore 这样,该接口就不会暴露在 swagger2 的页面下
                .apis(RequestHandlerSelectors.basePackage("com.site.mountain.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    //构建 api文档的详细信息函数,注意这里的注解引用的是哪个
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //页面标题
                .title("Spring Boot 测试使用 Swagger2 构建RESTful API")
                //创建人
                .contact(new Contact("MarryFeng", "http://www.baidu.com", ""))
                //版本号
                .version("1.0")
                //描述
                .description("API 描述")
                .build();
    }

}

```

* shiro配置文件过滤

```java
map.put("/swagger-ui.html", "anon");
map.put("/swagger-resources", "anon");
map.put("/swagger-resources/configuration/security", "anon");
map.put("/swagger-resources/configuration/ui", "anon");
map.put("/v2/api-docs", "anon");
map.put("/webjars/springfox-swagger-ui/**", "anon");
```



### 使用方式

* 在类上:@Api(value = "sysuser", description = "系统用户类")

  ```java
  @Api(value = "sysuser", description = "系统用户类")
  @Controller
  @RequestMapping("/sysuser")
  public class SysUserController {...}
  ```

  

* 在方法上:

  @ApiOperation(value = "adduser",httpMethod="POST",notes = "用户添加",response = JSONObject.class)

  ```java
  @ApiOperation(value = "adduser",httpMethod="POST",notes = "用户添加",response = JSONObject.class)
  @RequestMapping(value = "adduser", method = RequestMethod.POST)
  public void addUser(){....}
  ```

* 在参数上: @ApiParam(value = "前端传递过来的用户对象",required = true)

  ```java
  @ApiOperation(value="edit", notes="用户编辑", httpMethod = "POST",response = JSONObject.class)
  @RequestMapping(value = "edit",method = RequestMethod.POST)
  public void update(@RequestBody @ApiParam(value = "前端传递过来的用户对象",required = true) SysUser sysUser){...}
  ```

* 详细用法请参考swagger2的api

* 页面显示：访问地址为http://localhost:8080/mt/swagger-ui.html

  ![](E:\项目\学习项目\自己博客文章\myblogmarkdown\imgs\4-swagger.png)



## 集成quartz

### 集成jar包

```xml
<dependency>
    <groupId>org.quartz-scheduler</groupId>
    <artifactId>quartz</artifactId>
    <version>2.2.2</version>
</dependency>
<!-- 该依赖必加，里面有sping对schedule的支持 -->
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-context-support</artifactId>
</dependency>
```



### 文件配置

* 数据库表导入：因为需要quartz的数据库表，因为最新版本没有数据库表，所以我使用的是`2.2.2`版本，

  把`quartz-2.2.2-distribution.tar.gz\quartz-2.2.2\docs\dbTables`文件夹下的表导入数据库中

* 配置文件：

```properties
# 固定前缀org.quartz
# 主要分为scheduler、threadPool、jobStore、plugin等部分
#
#
#org.quartz.scheduler.instanceName = DefaultQuartzScheduler
org.quartz.scheduler.rmi.export = false
org.quartz.scheduler.rmi.proxy = false
org.quartz.scheduler.wrapJobExecutionInUserTransaction = false

# 实例化ThreadPool时，使用的线程类为SimpleThreadPool
org.quartz.threadPool.class = org.quartz.simpl.SimpleThreadPool

# threadCount和threadPriority将以setter的形式注入ThreadPool实例
# 并发个数
org.quartz.threadPool.threadCount = 5
# 优先级
org.quartz.threadPool.threadPriority = 5
org.quartz.threadPool.threadsInheritContextClassLoaderOfInitializingThread = true

org.quartz.jobStore.misfireThreshold = 5000

# 默认存储在内存中
#org.quartz.jobStore.class = org.quartz.simpl.RAMJobStore

#持久化
org.quartz.jobStore.class = org.quartz.impl.jdbcjobstore.JobStoreTX

org.quartz.jobStore.tablePrefix = QRTZ_

org.quartz.jobStore.dataSource = qzDS

org.quartz.dataSource.qzDS.driver = com.mysql.jdbc.Driver

org.quartz.dataSource.qzDS.URL = jdbc:mysql://192.168.75.1:3306/mountain?useUnicode=true&characterEncoding=UTF-8

org.quartz.dataSource.qzDS.user = root

org.quartz.dataSource.qzDS.password = root

org.quartz.dataSource.qzDS.maxConnections = 10
```



* 前端页面展示

  ![](E:\项目\学习项目\自己博客文章\myblogmarkdown\imgs\4-quartz-list.png)

![](E:\项目\学习项目\自己博客文章\myblogmarkdown\imgs\4-quartz-add.png)



* 后端controller

  ```java
  package com.site.mountain.controller.sys;
  
  import com.github.pagehelper.PageInfo;
  import com.site.mountain.entity.JobAndTrigger;
  import com.site.mountain.entity.JobBean;
  import com.site.mountain.job.BaseJob;
  import com.site.mountain.service.IJobAndTriggerService;
  import org.quartz.CronScheduleBuilder;
  import org.quartz.CronTrigger;
  import org.quartz.JobBuilder;
  import org.quartz.JobDetail;
  import org.quartz.JobKey;
  import org.quartz.Scheduler;
  import org.quartz.SchedulerException;
  import org.quartz.TriggerBuilder;
  import org.quartz.TriggerKey;
  import org.slf4j.Logger;
  import org.slf4j.LoggerFactory;
  import org.springframework.beans.factory.annotation.Autowired;
  import org.springframework.beans.factory.annotation.Qualifier;
  import org.springframework.web.bind.annotation.*;
  
  import javax.servlet.http.HttpServletRequest;
  import javax.servlet.http.HttpServletResponse;
  import java.util.HashMap;
  import java.util.Map;
  
  @RestController
  @RequestMapping(value = "/job")
  public class JobController {
      @Autowired
      private IJobAndTriggerService iJobAndTriggerService;
  
      //加入Qulifier注解，通过名称注入bean
      @Autowired
      private Scheduler scheduler;
  
      private static Logger log = LoggerFactory.getLogger(JobController.class);
  
  
      @PostMapping(value = "/addjob")
      public Map addjob(@RequestBody JobBean jobBean) throws Exception {
          Map map = new HashMap();
          map.put("code", 20000);
          map.put("state", 30000);
          try{
              addJob(jobBean.getJobClassName(), jobBean.getJobGroupName(), jobBean.getCronExpression());
          }catch (Exception e){
              e.printStackTrace();
              map.put("state", 30001);
          }
          return map;
      }
  
      public void addJob(String jobClassName, String jobGroupName, String cronExpression) throws Exception {
  
          // 启动调度器
          scheduler.start();
  
          //构建job信息
          JobDetail jobDetail = JobBuilder.newJob(getClass(jobClassName).getClass()).withIdentity(jobClassName, jobGroupName).build();
  
          //表达式调度构建器(即任务执行的时间)
          CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);
  
          //按新的cronExpression表达式构建一个新的trigger
          CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(jobClassName, jobGroupName)
                  .withSchedule(scheduleBuilder).build();
  
          try {
              scheduler.scheduleJob(jobDetail, trigger);
  
          } catch (SchedulerException e) {
              System.out.println("创建定时任务失败" + e);
              throw new Exception("创建定时任务失败");
          }
      }
  
  
      @PostMapping(value = "/pausejob")
      public Map pausejob(@RequestBody JobBean jobBean) throws Exception {
          Map map = new HashMap();
          map.put("code", 20000);
          map.put("state", 30000);
          try {
              jobPause(jobBean.getJobClassName(), jobBean.getJobGroupName());
          } catch (Exception e) {
              e.printStackTrace();
              map.put("state", 30001);
          }
          return map;
      }
  
      public void jobPause(String jobClassName, String jobGroupName) throws Exception {
          scheduler.pauseJob(JobKey.jobKey(jobClassName, jobGroupName));
      }
  
  
      @PostMapping(value = "/resumejob")
      public Map resumejob(@RequestBody JobBean jobBean) throws Exception {
          Map map = new HashMap();
          map.put("code", 20000);
          map.put("state", 30000);
          try {
              jobresume(jobBean.getJobClassName(), jobBean.getJobGroupName());
          } catch (Exception e) {
              e.printStackTrace();
              map.put("state", 30001);
          } finally {
  
          }
          return map;
      }
  
      public void jobresume(String jobClassName, String jobGroupName) throws Exception {
          scheduler.resumeJob(JobKey.jobKey(jobClassName, jobGroupName));
      }
  
  
      @PostMapping(value = "/reschedulejob")
      public Map rescheduleJob(@RequestBody JobBean jobBean) throws Exception {
          Map map = new HashMap();
          map.put("code", 20000);
          map.put("state", 30000);
          try {
              jobreschedule(jobBean.getJobClassName(), jobBean.getJobGroupName(), jobBean.getCronExpression());
          } catch (Exception e) {
              e.printStackTrace();
              map.put("state", 30001);
          }
          return map;
      }
  
      public void jobreschedule(String jobClassName, String jobGroupName, String cronExpression) throws Exception {
          try {
              TriggerKey triggerKey = TriggerKey.triggerKey(jobClassName, jobGroupName);
              // 表达式调度构建器
              CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);
  
              CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
  
              // 按新的cronExpression表达式重新构建trigger
              trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
  
              // 按新的trigger重新设置job执行
              scheduler.rescheduleJob(triggerKey, trigger);
          } catch (SchedulerException e) {
              System.out.println("更新定时任务失败" + e);
              throw new Exception("更新定时任务失败");
          }
      }
  
  
      @PostMapping(value = "/deletejob")
      public Map deletejob(@RequestBody JobBean jobBean) throws Exception {
          Map map = new HashMap();
          map.put("code", 20000);
          map.put("state", 30000);
          try {
              jobdelete(jobBean.getJobClassName(), jobBean.getJobGroupName());
          } catch (Exception e) {
              e.printStackTrace();
              map.put("state", 30001);
          }
          return map;
      }
  
      public void jobdelete(String jobClassName, String jobGroupName) throws Exception {
          scheduler.pauseTrigger(TriggerKey.triggerKey(jobClassName, jobGroupName));
          scheduler.unscheduleJob(TriggerKey.triggerKey(jobClassName, jobGroupName));
          scheduler.deleteJob(JobKey.jobKey(jobClassName, jobGroupName));
      }
  
  
      @RequestMapping(value = "/queryjob")
      public Map<String, Object> queryjob(@RequestBody JobBean jobBean) {
          PageInfo<JobAndTrigger> jobAndTrigger = iJobAndTriggerService.getJobAndTriggerDetails(jobBean.getPageNum(), jobBean.getPageSize());
          Map<String, Object> map = new HashMap<String, Object>();
          map.put("JobAndTrigger", jobAndTrigger);
          map.put("number", jobAndTrigger.getTotal());
          map.put("code", 20000);
          return map;
      }
  
      public static BaseJob getClass(String classname) throws Exception {
          Class<?> class1 = Class.forName(classname);
          return (BaseJob) class1.newInstance();
      }
  }
  
  ```

* 定时器接口

```java
package com.site.mountain.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public interface BaseJob extends Job {
    @Override
    void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException;
}

```



### 使用方式

* 实现定时器接口

```java
package com.site.mountain.job.impl;

import com.site.mountain.job.BaseJob;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class HelloJob implements BaseJob {
    private static Logger _log = LoggerFactory.getLogger(HelloJob.class);
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        _log.error("Hello Job执行时间: " + new Date());
    }
}
```

* 在前端配置定时类

![](E:\项目\学习项目\自己博客文章\myblogmarkdown\imgs\4-quartz-list.png)



## 集成Druid 连接池

### 集成jar包

```xml
<dependency>
    <groupId>com.alibaba</groupId>
    <artifactId>druid-spring-boot-starter</artifactId>
    <version>1.1.10</version>
</dependency>
```



### 文件配置

* 修改application.properties文件

```properties
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
```

* 设置Druid配置类`DruidConfig.java`

```java
package com.site.mountain.datasource;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DruidConfig {

    /**
     * 注册servlet信息，配置监控图
     */
    @Bean
    @ConditionalOnMissingBean
    public ServletRegistrationBean druidServlet() {
        ServletRegistrationBean servletRegistrationBean =
                new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        //白名单
        servletRegistrationBean.addInitParameter("allow", "172.22.112.130");
        //IP黑名单(存在共同时，deny优先于allow) : 如果满足deny的话提示:Sorry, you are not permitted to view this page.
        servletRegistrationBean.addInitParameter("deny", "127.0.0.1");
        //用于登陆的账号密码
        servletRegistrationBean.addInitParameter("loginUsername", "admin");
        servletRegistrationBean.addInitParameter("loginPassword", "admin");
        //是否能重置数据
        servletRegistrationBean.addInitParameter("resetEnable", "true");
        return servletRegistrationBean;
    }

    /**
     * 注册filter信息，用于拦截
     */
    @Bean
    @ConditionalOnMissingBean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }
}
```

* Druid监控页面URL：[http://172.22.112.130:8080/mt/druid ]   用户名密码是admin/admin。登录后页面如下：

![](E:\项目\学习项目\自己博客文章\myblogmarkdown\imgs\4-druid.png)



### 注意点

* 访问[http://172.22.112.130:8080/mt/druid ]   用户名密码是admin/admin。注意访问地址ip需要与DruidConfig类中的配置白名单一致，不然不能访问

* 访问Druid监控页面URL时，系统必须先登录，因为shiro拦截了访问，但是在shiro放开`druid/**` 访问拦截，会导致系统主页面等链接不能访问，待解决...



## 集成Docker

### 集成jar包

```xml
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
```



### 文件配置

* 在工程目录src/main/docker中创建Dockerfile文件，内容如下：

```dockerfile
# FROM:指明当前镜像继承的基镜像,编译当前镜像时候会自动下载基镜像
FROM java:8
# MAINTAINER:当前镜像的作者和邮箱,使用空格隔开
MAINTAINER jinshw jinshw@xxx.com
# VOLUME:挂载目录
VOLUME /ROOT
# ADD:从当前工作目录复制文件到镜像目录中并重新命名
ADD mountain-0.0.1-SNAPSHOT.jar mountain.jar
# RUN:在当前镜像上执行Linux命令,这里我执行了2个run指令,第二个run指令是为了解决容器和宿主机时间不一致的问题
RUN bash -c 'touch /mountain.jar'

RUN cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime \
&& echo 'Asia/Shanghai' >/etc/timezone

# EXPOSE:监听的端口号
EXPOSE 8080

# ENTRYPOINT:让容器像一个可执行程序一样运行
ENTRYPOINT ["java", "-jar", "mountain.jar"]
```



### 注意点

- pom文件中集成docker插件 `docker-maven-plugin`
- docker 服务需要开启 2375 端口
  - 编辑docker.service文件，manjaro系统在`/usr/lib/systemd/system`路径下 
  - 配置`ExecStart=/usr/bin/dockerd -H unix://var/run/docker.sock -H tcp://0.0.0.0:2375`
- 集成dockerfile文件 
- 执行命令：`clean compile install -DskipTests docker:removeImage docker:build`

 

