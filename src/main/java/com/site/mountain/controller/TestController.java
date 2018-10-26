package com.site.mountain.controller;

import com.site.mountain.entity.UserInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("test")
public class TestController{

    @RequestMapping("resp")
    @ResponseBody
    public String getStrs(HttpServletRequest request, HttpServletResponse response){
        return "name:jinshw";
    }

    @RequestMapping("user")
    @ResponseBody
    public UserInfo getUser(UserInfo userInfo){
        userInfo.setUid(userInfo.getUid()+1);
        userInfo.setName(userInfo.getName()+" jinshw");
        return userInfo;
    }

}
