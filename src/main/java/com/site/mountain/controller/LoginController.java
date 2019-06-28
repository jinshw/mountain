package com.site.mountain.controller;

import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("user")
public class LoginController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping(value = "/login", method = {RequestMethod.POST, RequestMethod.OPTIONS})
    public void login(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        String sessionId = null;
        System.out.println("IndexController---login---");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        username = "admin";
        password = "123456";
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
//            sessionId = (String) currentUser.getSession().getId();
            logger.info("对用户[" + username + "]进行登录验证..验证通过");
        } catch (UnknownAccountException uae) {
            logger.info("对用户[" + username + "]进行登录验证..验证未通过,未知账户");
            mv.addObject("message", "未知账户");
        } catch (IncorrectCredentialsException ice) {
            logger.info("对用户[" + username + "]进行登录验证..验证未通过,错误的凭证");
            mv.addObject("message", "密码不正确");
        } catch (LockedAccountException lae) {
            logger.info("对用户[" + username + "]进行登录验证..验证未通过,账户已锁定");
            mv.addObject("message", "账户已锁定");
        } catch (ExcessiveAttemptsException eae) {
            logger.info("对用户[" + username + "]进行登录验证..验证未通过,错误次数过多");
            mv.addObject("message", "用户名或密码错误次数过多");
        } catch (AuthenticationException ae) {
            //通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景
            logger.info("对用户[" + username + "]进行登录验证..验证未通过,堆栈轨迹如下");
            mv.addObject("message", "用户名或密码不正确");
            ae.printStackTrace();
        }
        //验证是否登录成功
//        if (currentUser.isAuthenticated()) {
//            logger.info("用户[" + username + "]登录认证通过(这里可以进行一些认证通过后的一些系统参数初始化操作)");
//            mv.setViewName("/index");
//        } else {
//            token.clear();
//            mv.setViewName("/login");
//        }
        Map map = new HashMap();
        map.put("uuid", "1111");
        map.put("token", "admin-token");
        map.put("name", "admin");
        map.put("sessionId", sessionId);
        JSONObject obj = new JSONObject();

        obj.put("data", map);
        obj.put("code", 20000);
        try {
            response.getWriter().print(obj.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/info", method = RequestMethod.GET)
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
    @RequestMapping(value="/logout",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject logout(RedirectAttributes redirectAttributes) {
        //使用权限管理工具进行用户的退出，跳出登录，给出提示信息
        SecurityUtils.getSubject().logout();
        redirectAttributes.addFlashAttribute("message", "您已安全退出");
        JSONObject obj = new JSONObject();
        obj.put("code",20000);
        obj.put("message","success");
        return obj;
    }
}
