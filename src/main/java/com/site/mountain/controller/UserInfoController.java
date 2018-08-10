package com.site.mountain.controller;

import com.site.mountain.entity.SysPermission;
import com.site.mountain.entity.SysRole;
import com.site.mountain.entity.UserInfo;
import com.site.mountain.service.SysPermissionService;
import com.site.mountain.service.SysRoleService;
import com.site.mountain.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/user")
@CrossOrigin(origins = "http://127.0.0.1:8081")
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysPermissionService sysPermissionService;

    @RequestMapping("list")
    @ResponseBody
    public List findList(HttpServletRequest request, HttpServletResponse response){
        UserInfo userInfo = new UserInfo();
        List list = userInfoService.selectAllUserAndRoles(userInfo);
        return list;
    }

    @RequestMapping("role/list")
    @ResponseBody
    public List findRoleList(){
        return sysRoleService.findRoleList(new SysRole());
    }
    @RequestMapping("permission/list")
    @ResponseBody
    public List findPermissionList(){
        return sysPermissionService.findList(new SysPermission());
    }
}
