

# SpringBoot后台权限管理系统（三)—权限模块

[SpringBoot 后台权限框架搭建（一）](https://juejin.im/post/5d56678651882579675ed736)

[SpringBoot后台权限管理系统（二)—前端工程搭建](https://juejin.im/post/5d5d12096fb9a06afc25460f)

```text
权限模块主要包括用户管理、部门管理、角色管理、菜单管理模块。以下主要从数据库表创建、页面、接口主要实现点的讲述。
```

## 数据库设计

### 数据库关系图

![](.\imgs\mountain-db-designer.png)

### 数据库表说明

* 用户表：主要记录用户信息，登录账号、密码等
* 部门表：主要记录部门相关信息
* 角色表：主要记录角色关系信息
* 菜单表：主要记录菜单相关信息
* 用户角色关系表：用户和角色是多对多的关系
* 角色部门关系表：部门和角色是多对多的关系
* 角色菜单关系表：菜单和角色是多对多的关系

### 注意点

* 部门表和菜单表都是树形结构，在项目初始化时，在这两张表中初始化一个根节点，方便之后查询树形结构（我是这么解决的，也可以用其他方法解决），例如：

  ```sql
  insert into  sys_dept(dept_id,name) values(-1,'一级部门');
  insert into  sys_menu(menu_id,name) values(-1,'一级菜单');
  ```

* 关联表中是有外键关联的，以便于后期修改和删除主表时，自动更新。

## 用户管理

### 前端页面

* 列表页面

![(E:\项目\学习项目\自己博客文章\myblogmarkdown\imgs\user-add.png)

![](E:\项目\学习项目\自己博客文章\myblogmarkdown\imgs\user-list.png)



* 新增页面

![](E:\项目\学习项目\自己博客文章\myblogmarkdown\imgs\user-add.png)



* 编辑页面

![](E:\项目\学习项目\自己博客文章\myblogmarkdown\imgs\user-edit.png)

* 前端代码路径

  ```
  ─views
      ├─sys
      │  ├─dept
      │  ├─dict
      │  ├─job
      │  ├─menu
      │  ├─role
      │  ├─task
      │  ├─test
      │  └─user
      
  ```

  

### 接口设计

* 列表查询：后端接口访问地址`/sysuser/list`
* 新增用户：`/sysuser/adduser`
* 编辑用户：`/sysuser/edit`
* 删除用户：`/sysuser/delete`

### 注意点

* 接口访问方式：前端访问接口使用`axios`方式，后端controller中使用`@RequestBody`标签对象接收数据

  ```java
  @RequestMapping(value = "adduser", method = RequestMethod.POST)
  public void addUser(@RequestBody SysUser sysUser, HttpServletRequest request, HttpServletResponse response){...}
  ```

* 权限管理：在接口上添加`@RequiresPermissions`注解，使接口拥有指定权限的人才能使用，在改系统中用户权限配置在数据表中，权限注解代码如下：

  ```java
  @RequiresPermissions("userInfo:view")//拥有userInfo:view权限才能查询
  @RequestMapping(value = "list", method = RequestMethod.POST)
  @ResponseBody
  public JSONObject findList(@RequestBody SysUser sysUser, HttpServletRequest request, HttpServletResponse response) {
      List list = sysUserService.findList(sysUser);
      JSONObject obj = new JSONObject();
      obj.put("data", list);
      obj.put("code", 20000);
      obj.put("message", "success");
      return obj;
  }
  ```

  

![](.\imgs\3-menu-perms.png)





## 角色管理

### 前端页面

* 列表

![](.\imgs\3-roles-list.png)

* 新增

![](.\imgs\3-roles-add.png)

* 编辑

![](.\imgs\3-roles-edit.png)



### 接口设计

* 角色列表：`/sysrole/list`

* 角色新增：`/sysrole/add`
* 角色编辑：`/sysrole/edit`
* 部门查询：`/sysdept/getTree`
* 菜单查询:`/sysmenu/getTree`

### 注意点

* 菜单授权:在编辑页面中的菜单授权默认勾选项，是在前端JS中根据后端查询回来的数据处理的，代码如下：

  ```javascript
  getMenuTreeCheckedKeys: function(arr, node) {
      var that = this
      if (node.children.length === 0) {
          arr.push(node.menuId)
          return node.menuId
      }
      for (var index in node.children) {
      	that.getMenuTreeCheckedKeys(arr, node.children[index])
      }
      arr.push(node.menuId)
  }
  ```

* 数据授权：在前端JS中处理，代码如下：

  ```javascript
  getDeptTreeCheckedKeys: function(arr, node) {
      var that = this
      if (node.children.length === 0) {
      	arr.push(node.deptId)
      	return node.deptId
      }
      for (var index in node.children) {
      	that.getDeptTreeCheckedKeys(arr, node.children[index])
      }
      arr.push(node.deptId)
  }
  ```



## 部门管理

### 前端页面

* 列表

![](.\imgs\3-dept-list.png)

* 新增

![](.\imgs\3-dept-add.png)

* 编辑

![](.\imgs\3-dept-edit.png)



### 接口设计

* 部门列表：`/sysdept/getTree`
* 部门新增:`/sysdept/add`
* 部门编辑:`/sysdept/edit`
* 部门删除:`/sysdept/delete`

### 注意点

* 部门列表：因为列表是树形结构，在查询时传递树的根节点`deptId`,前端请求如下：

  ```javascript
  getTree({ deptId: '-1' }).then(response => {
  	that.depts = response.data.children
  })
  ```

  后端代码：

  ```java
  @RequestMapping("getTree")
  @ResponseBody
  public JSONObject getTree(@RequestBody SysDept sysDept, HttpServletRequest request, HttpServletResponse response) {
      JSONObject jsonObject = new JSONObject();
      SysDept tree = new SysDept();
      tree = sysDeptService.getMenuTree(sysDept.getDeptId());
      jsonObject.put("code", 20000);
      jsonObject.put("data", tree);
      return jsonObject;
  }
  ```

  

* 部门删除：由于部门数据是树形结构的，如果删除的节点有孩子节点，不删除，提示信息，主要代码如下：

  ```java
  @RequestMapping(value = "delete", method = RequestMethod.POST)
  public void delete(@RequestBody SysDept sysDeptParam, HttpServletRequest request, 	HttpServletResponse response) {
      int flag = 0;
      JSONObject jsonObject = new JSONObject();
      BigInteger deptId = sysDeptParam.getDeptId();
      SysDept tree = new SysDept();
      tree = sysDeptService.getMenuTree(deptId);
      if (tree.getChildren().size() == 0) {
          SysDept sysDept = new SysDept();
          sysDept.setDeptId(deptId);
          sysDept.setDelFlag(-1);
          flag = sysDeptService.delete(sysDept);
          if (flag > 0) {
              jsonObject.put("status", 200);
          } else {
              jsonObject.put("status", 500);
          }
      } else {
          jsonObject.put("status", 201);
      }
      jsonObject.put("code", 20000);
      try {
          response.getWriter().print(jsonObject.toJSONString());
      } catch (IOException e) {
          e.printStackTrace();
      }
  }
  ```

  

## 菜单管理

### 前端页面

* 列表：

![](.\imgs\3-menu-list.png)

* 新增

![](E:\项目\学习项目\自己博客文章\myblogmarkdown\imgs\3-menu-add.png)

* 编辑

![](E:\项目\学习项目\自己博客文章\myblogmarkdown\imgs\3-menu-edit.png)



### 接口设计

* 列表查询：`/sysmenu/getTree`
* 菜单新增：`/sysmenu/add`
* 菜单编辑：`/sysmenu/edit`
* 菜单删除：`/sysmenu/delete`

### 注意点

* 菜单新增：可以按照类型新增，目录、菜单、按钮
  * 目录：在页面左侧显示第一级别的目录栏
  * 菜单：在左侧目录下包含的菜单
  * 按钮：在页面上显示的按钮（暂时未实现该功能）
* 菜单授权：在添加菜单或修改菜单是，一定注意`菜单路由`和`授权标识` ,并且都可以配置多个，中间用英文`;`间隔。
  * 菜单路由：是前端点击该菜单浏览器跳转的路由
  * 授权标识：是后端`shiro`校验的标识，需要和controller方法中的注解`@RequiresPermissions`一致

![](.\imgs\3-menu-edit-2.png)



## 填坑

* MyBatis如何获取插入记录的自增长字段值

  在插入方法中添加`useGeneratedKeys`和`keyProperty`属性，`useGeneratedKeys`当设置为 true 时，表示如果插入的表以自增列为主键，则允许 JDBC 支持自动生成主键，并可将自动生成的主键返回。

  ```xml
  <insert id="insert" useGeneratedKeys="true" keyProperty="userId" parameterType="com.site.mountain.entity.SysUser">
          INSERT INTO sys_user
      <trim prefix="(" suffix=")" suffixOverrides=",">
          <if test="userId!=null">`user_id`,</if>
          <if test="username!=null">`username`,</if>
          <if test="password!=null">`password`,</if>
          <if test="email!=null">`email`,</if>
          <if test="mobile!=null">`mobile`,</if>
          <if test="status!=null">`status`,</if>
          <if test="deptId!=null">`dept_id`,</if>
          <if test="createTime!=null">`create_time`,</if>
  
      </trim>
      VALUES
      <trim prefix="(" suffix=")" suffixOverrides=",">
          <if test="userId!=null">#{userId},</if>
          <if test="username!=null">#{username},</if>
          <if test="password!=null">#{password},</if>
          <if test="email!=null">#{email},</if>
          <if test="mobile!=null">#{mobile},</if>
          <if test="status!=null">#{status},</if>
          <if test="deptId!=null">#{deptId},</if>
          <if test="createTime!=null">#{createTime},</if>
  
      </trim>
  </insert>
  ```

  

* MySQL根据外键级联删除：表存储引擎必须使用InnoDB引擎

* 事务失效问题：springboot和shiro框架集成时，先加载shiro，这时sysUserService还没有实例化，导致事务失效， 在sysUserService 上加载 `@Lazy` ，主要代码如下：

  ```java
  public class MyShiroRealm extends AuthorizingRealm {
      @Autowired
      @Lazy
      private SysUserService sysUserService;
      
      ......
  }
  ```

  

## 其他

该项目我先是设计数据库表的关系，然后用自己写的小工具把数据库表转成JavaBean、xml、mapper代码，小工具请看我的 [《golang 实现的mybatis代码生成器》](https://juejin.im/post/5d3954b4518825102c7e63c2)

生成小工具[下载地址](https://github.com/jinshw/mybatis-client/releases)



## 待续...

- 集成Swagger2
- 集成quartz框架
- 集成docker

