package com.site.mountain.controller.sys;

import com.alibaba.fastjson.JSONObject;
import com.site.mountain.constant.ConstantProperties;
import com.site.mountain.entity.SysMenu;
import com.site.mountain.entity.SysUser;
import com.site.mountain.service.SysMenuService;
import com.site.mountain.service.SysUserService;
import com.site.mountain.utils.MD5Util;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(value = "sysuser", description = "系统用户类")
@Controller
@RequestMapping("/sysuser")
public class SysUserController {
    private final static Logger logger = LoggerFactory.getLogger(SysUserController.class);

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private ConstantProperties constantProperties;

    @ApiOperation(value = "login", httpMethod = "POST", notes = "用户登录")
    @RequestMapping(value = "login", method = {RequestMethod.POST})
    public void login(@RequestBody @ApiParam(value = "前端传递过来的用户对象", required = true) SysUser sysUser, HttpServletRequest request, HttpServletResponse response) {
        JSONObject obj = new JSONObject();
        ModelAndView mv = new ModelAndView();
        String sessionId = null;
        System.out.println("IndexController---login---");
        String username = sysUser.getUsername();
        String password = sysUser.getPassword();
        password = MD5Util.getMD5String(password);
        sysUser.setPassword(password);
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        //获取当前的Subject
        Subject currentUser = SecurityUtils.getSubject();
        try {
            //在调用了login方法后,SecurityManager会收到AuthenticationToken,并将其发送给已配置的Realm执行必须的认证检查
            //每个Realm都能在必要时对提交的AuthenticationTokens作出反应
            //所以这一步在调用login(token)方法时,它会走到MyRealm.doGetAuthenticationInfo()方法中,具体验证方式详见此方法
            logger.info("对用户[" + username + "]进行登录验证..验证开始");
            currentUser.login(token);
            sessionId = request.getSession().getId();
            request.getSession().setAttribute("user", sessionId);
            logger.info("对用户[" + username + "]进行登录验证..验证通过");

//            List<SysUser> sysUserList =  sysUserService.selectAllUserAndRoles(sysUser);
            SysMenu sysMenus = sysUserService.getMenuTree(sysUser);
//            SysMenu sysMenuTree = sysMenuService.getMenuTree(new BigInteger("-1"));
            obj.put("sysMenus", sysMenus);
//            obj.put("user",sysUserList.get(0));
//            obj.put("menuTree",sysMenuTree);
            obj.put("status", 30000);
            obj.put("message", "登录成功");
        } catch (UnknownAccountException uae) {
            logger.info("对用户[" + username + "]进行登录验证..验证未通过,未知账户");
            mv.addObject("message", "未知账户");
            obj.put("status", 30001);
            obj.put("message", "未知账户");
        } catch (IncorrectCredentialsException ice) {
            logger.info("对用户[" + username + "]进行登录验证..验证未通过,错误的凭证");
            mv.addObject("message", "密码不正确");
            obj.put("status", 30001);
            obj.put("message", "密码不正确");
        } catch (LockedAccountException lae) {
            logger.info("对用户[" + username + "]进行登录验证..验证未通过,账户已锁定");
            mv.addObject("message", "账户已锁定");
            obj.put("status", 30001);
            obj.put("message", "账户已锁定");
        } catch (ExcessiveAttemptsException eae) {
            logger.info("对用户[" + username + "]进行登录验证..验证未通过,错误次数过多");
            mv.addObject("message", "用户名或密码错误次数过多");
            obj.put("status", 30001);
            obj.put("message", "用户名或密码错误次数过多");
        } catch (AuthenticationException ae) {
            //通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景
            logger.info("对用户[" + username + "]进行登录验证..验证未通过,堆栈轨迹如下");
            mv.addObject("message", "用户名或密码不正确");
            obj.put("status", 30001);
            obj.put("message", "用户名或密码不正确");
            ae.printStackTrace();
        }
        //验证是否登录成功(废弃，在前端实现跳转)
        /*if (currentUser.isAuthenticated()) {
            logger.info("用户[" + username + "]登录认证通过(这里可以进行一些认证通过后的一些系统参数初始化操作)");
            mv.setViewName("/index");
        } else {
            token.clear();
            mv.setViewName("/login");
        }*/
        Map map = new HashMap();
        map.put("token", sessionId);
        map.put("name", "admin");
        map.put("sessionId", sessionId);

        obj.put("data", map);
        obj.put("code", 20000);
        try {
            response.setCharacterEncoding("UTF-8");
            response.getWriter().print(obj.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "info", method = RequestMethod.GET)
    public void getInfo(HttpServletRequest request, HttpServletResponse response) {
        JSONObject obj = new JSONObject();
        Map map = new HashMap();
        map.put("token", "admin-token");
        map.put("introduction", "I am a super administrator");
        map.put("avatar", "https://jinshw.github.io/images/avatar.gif");
        map.put("name", "Super Admin Java");

        obj.put("code", 20000);
        obj.put("data", map);
        try {
            response.getWriter().print(obj.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 用户退出
     *
     * @return 跳转到登录页面
     */
    @RequestMapping(value = "logout", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject logout(RedirectAttributes redirectAttributes) {
        //使用权限管理工具进行用户的退出，跳出登录，给出提示信息
        SecurityUtils.getSubject().logout();
        redirectAttributes.addFlashAttribute("message", "您已安全退出");
        JSONObject obj = new JSONObject();
        obj.put("code", 20000);
        obj.put("message", "success");
        return obj;
    }


    @ApiOperation(value = "list", httpMethod = "POST", notes = "用户列表", response = JSONObject.class)
    @RequiresPermissions("userInfo:view")
    @RequestMapping(value = "list", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject findList(@RequestBody @ApiParam(value = "前端传递过来的用户对象", required = true) SysUser sysUser, HttpServletRequest request, HttpServletResponse response) {
        List list = sysUserService.findList(sysUser);
        JSONObject obj = new JSONObject();
        obj.put("data", list);
        obj.put("code", 20000);
        obj.put("message", "success");
        return obj;
    }

    @ApiOperation(value = "adduser", httpMethod = "POST", notes = "用户添加", response = JSONObject.class)
    @RequestMapping(value = "adduser", method = RequestMethod.POST)
    public void addUser(@RequestBody @ApiParam(value = "前端传递过来的用户对象", required = true) SysUser sysUser, HttpServletRequest request, HttpServletResponse response) {
        JSONObject jsonObject = new JSONObject();
        Timestamp createtime = new Timestamp(System.currentTimeMillis());
        sysUser.setCreateTime(createtime);
        int flag = 0;
        try {
            sysUser.setPassword(MD5Util.getMD5String(sysUser.getPassword()));
            flag = sysUserService.insert(sysUser);
        } catch (Exception e) {
            logger.error("失败", e);
        }

        System.out.println(flag);
        jsonObject.put("code", 20000);
        if (flag > 0) {
            jsonObject.put("message", "执行成功");
        } else {
            jsonObject.put("message", "执行失败");
        }

        try {
            response.getWriter().print(jsonObject.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @ApiOperation(value = "delete", httpMethod = "POST", notes = "用户删除", response = JSONObject.class)
    @RequestMapping("delete")
    public void delete(@RequestBody @ApiParam(value = "前端传递过来的用户对象", required = true) SysUser sysUser, HttpServletRequest request, HttpServletResponse response) {
        int flag = 0;
        JSONObject jsonObject = new JSONObject();
        flag = sysUserService.delete(sysUser);
        if (flag > 0) {
            jsonObject.put("code", 20000);
        } else {
            jsonObject.put("code", 20000);
        }
        try {
            response.getWriter().print(jsonObject.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @ApiOperation(value = "edit", notes = "用户编辑", httpMethod = "POST", response = JSONObject.class)
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public void update(@RequestBody @ApiParam(value = "前端传递过来的用户对象", required = true) SysUser sysUser, HttpServletRequest request, HttpServletResponse response, @ModelAttribute SysUser user) {
        int flag = 0;
        JSONObject jsonObject = new JSONObject();
        flag = sysUserService.update(sysUser);
        if (flag > 0) {
            jsonObject.put("status", 200);
        } else {
            jsonObject.put("status", 500);
        }
        jsonObject.put("code", 20000);
        try {
            response.getWriter().print(jsonObject.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "resetPassword", method = RequestMethod.POST)
    public void resetPassword(@RequestBody @ApiParam(value = "前端传递过来的用户对象", required = true) SysUser sysUser, HttpServletRequest request, HttpServletResponse response, @ModelAttribute SysUser user) {
        int flag = 0;
        JSONObject jsonObject = new JSONObject();
        String resetPassword = constantProperties.getResetPassword();
        sysUser.setPassword(MD5Util.getMD5String(resetPassword));
        flag = sysUserService.updatePassword(sysUser);
        if (flag > 0) {
            jsonObject.put("status", 200);
        } else {
            jsonObject.put("status", 500);
        }
        jsonObject.put("code", 20000);
        try {
            response.getWriter().print(jsonObject.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "modifyPassword", method = RequestMethod.POST)
    public void modifyPassword(@RequestBody JSONObject paramJson, HttpServletResponse response) {
        int flag = 0;
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 20000);
        jsonObject.put("status", 200);
        jsonObject.put("message", "执行成功");
        String password = paramJson.getString("password");
        String oldPassword = paramJson.getString("oldPassword");
        Subject currentUser = SecurityUtils.getSubject();
        SysUser sysUser = (SysUser) currentUser.getPrincipal();
        // 判断用户是否登录
        if (sysUser != null) {
            String currentUserPasswordMD5 = sysUser.getPassword();
            String oldPasswordMD5 = MD5Util.getMD5String(oldPassword);
            // 判断旧密码是否正确
            if (currentUserPasswordMD5.equals(oldPasswordMD5)) {
                sysUser.setPassword(MD5Util.getMD5String(password));
                flag = sysUserService.updatePassword(sysUser);
                if (flag > 0) {
                    jsonObject.put("status", 200);
                    currentUser.logout();
                } else {
                    jsonObject.put("status", 500);// 更新失败
                    jsonObject.put("message", "更新失败");
                }
            } else {
                jsonObject.put("status", 501);// 旧密码不正确
                jsonObject.put("message", "旧密码不正确");// 旧密码不正确
            }

        } else {
            jsonObject.put("code", 50000);// 用户没登录
            jsonObject.put("message", "用户没登录");
        }
        try {
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().print(jsonObject.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
