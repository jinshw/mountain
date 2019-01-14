package com.site.mountain.controller.sys;

import com.alibaba.fastjson.JSONObject;
import com.site.mountain.entity.SysPermission;
import com.site.mountain.entity.SysRole;
import com.site.mountain.entity.SysUser;
import com.site.mountain.entity.UserInfo;
import com.site.mountain.service.SysPermissionService;
import com.site.mountain.service.SysRoleService;
import com.site.mountain.service.SysUserService;
import com.site.mountain.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

@Controller
@RequestMapping("/sysuser")
public class SysUserInfoController {

    @Autowired
    private SysUserService sysUserService;
//    @Autowired
//    private SysRoleService sysRoleService;
    @Autowired
    private SysPermissionService sysPermissionService;

    @RequestMapping(value = "allRoles", method = RequestMethod.GET)
    @ResponseBody
    public List findAllUserAndRoles(){
        SysUser sysUser = new SysUser();
        List list = sysUserService.selectAllUserAndRoles(sysUser);
        return list;
    }

    @RequestMapping(value = "list", method = RequestMethod.POST)
    @ResponseBody
    public List findList(HttpServletRequest request, HttpServletResponse response) {
        String searchText = request.getParameter("searchText");
        SysUser sysUser = new SysUser();
        sysUser.setUsername(searchText);
        List list = sysUserService.findList(sysUser);
        return list;
    }

//    @RequestMapping("role/list")
//    @ResponseBody
//    public List findRoleList() {
//        return sysRoleService.findRoleList(new SysRole());
//    }

    @RequestMapping("permission/list")
    @ResponseBody
    public List findPermissionList() {
        return sysPermissionService.findList(new SysPermission());
    }

    @RequestMapping("add")
    public void insert(HttpServletRequest request,HttpServletResponse response){
        JSONObject jsonObject = new JSONObject();
        String userName = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String mobile = request.getParameter("mobile");
        String status = request.getParameter("status");
        String deptId = request.getParameter("deptId");
        SysUser sysUser = new SysUser();
        sysUser.setUsername(userName);
        sysUser.setPassword(password);
        sysUser.setEmail(email);
        sysUser.setMobile(mobile);
        if(!StringUtils.isEmpty(status)){
            sysUser.setStatus( Integer.valueOf(status));
        }
        if(!StringUtils.isEmpty(deptId)){
            sysUser.setDeptId(new BigInteger(deptId));
        }
        int flag = sysUserService.insert(sysUser);
        System.out.println(flag);
        if(flag>0){
            jsonObject.put("status",200);
        }else {
            jsonObject.put("status",500);
        }

        try {
            response.getWriter().print(jsonObject.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("delete")
    public void delete(HttpServletRequest request,HttpServletResponse response){
        int flag = 0;
        JSONObject jsonObject = new JSONObject();
        String id = request.getParameter("id");
        SysUser sysUser = new SysUser();
        if(!StringUtils.isEmpty(id)){
            sysUser.setUserId(new BigInteger(id));
            flag = sysUserService.delete(sysUser);
        }

        if(flag > 0){
            jsonObject.put("status",200);
        }else {
            jsonObject.put("status",500);
        }
        try {
            response.getWriter().print(jsonObject.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @RequestMapping("update")
    public void update(HttpServletRequest request,HttpServletResponse response,@ModelAttribute SysUser user){
        int flag = 0;


        JSONObject jsonObject = new JSONObject();
        String id = request.getParameter("id");

        SysUser sysUser = new SysUser();
        if(!StringUtils.isEmpty(id)){
            sysUser.setUserId(new BigInteger(id));
            flag = sysUserService.update(sysUser);
        }

        if(flag > 0){
            jsonObject.put("status",200);
        }else {
            jsonObject.put("status",500);
        }
        try {
            response.getWriter().print(jsonObject.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
