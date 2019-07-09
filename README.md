# mountain 工程
> 访问url：http://localhost:8080/mt/index.htm



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
                