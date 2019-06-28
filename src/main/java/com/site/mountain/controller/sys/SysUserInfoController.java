package com.site.mountain.controller.sys;

import com.alibaba.fastjson.JSONObject;
import com.site.mountain.entity.SysPermission;
import com.site.mountain.entity.SysUser;
import com.site.mountain.service.SysPermissionService;
import com.site.mountain.service.SysUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;
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

//    @RequiresPermissions("userInfo:view22")
//    @RequestMapping(value = "list", method = RequestMethod.POST)
//    @ResponseBody
//    public List findList(HttpServletRequest request, HttpServletResponse response) {
//        String searchText = request.getParameter("searchText");
//        SysUser sysUser = new SysUser();
//        sysUser.setUsername(searchText);
//        List list = sysUserService.findList(sysUser);
//        return list;
//    }

    @RequiresPermissions("userInfo:view")
    @RequestMapping(value = "list", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject findList(HttpServletRequest request, HttpServletResponse response) {
        String searchText = request.getParameter("searchText");
        SysUser sysUser = new SysUser();
        sysUser.setUsername(searchText);
        List list = sysUserService.findList(sysUser);
        JSONObject obj = new JSONObject();
        obj.put("data",list);
        obj.put("code",20000);
        obj.put("message","success");
        return obj;
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

    @RequestMapping(value = "add",method = RequestMethod.POST)
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

    @RequestMapping(value = "adduser",method = RequestMethod.POST)
    public void addUser(@RequestBody SysUser sysUser, HttpServletRequest request, HttpServletResponse response){
        JSONObject jsonObject = new JSONObject();
//        String userName = request.getParameter("username");
//        String password = request.getParameter("password");
//        String email = request.getParameter("email");
//        String mobile = request.getParameter("mobile");
//        String status = request.getParameter("status");
//        String deptId = request.getParameter("deptId");
//        SysUser sysUser = new SysUser();
//        sysUser.setUsername(userName);
//        sysUser.setPassword(password);
//        sysUser.setEmail(email);
//        sysUser.setMobile(mobile);
//        if(!StringUtils.isEmpty(status)){
//            sysUser.setStatus( Integer.valueOf(status));
//        }
//        if(!StringUtils.isEmpty(deptId)){
//            sysUser.setDeptId(new BigInteger(deptId));
//        }

        Timestamp createtime = new Timestamp(System.currentTimeMillis());
        sysUser.setCreateTime(createtime);
        int flag = sysUserService.insert(sysUser);
        System.out.println(flag);
        jsonObject.put("code",20000);
        if(flag>0){
            jsonObject.put("message","执行成功");
        }else {
            jsonObject.put("message","执行失败");
        }

        try {
            response.getWriter().print(jsonObject.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("delete")
    public void delete(@RequestBody SysUser sysUser,HttpServletRequest request,HttpServletResponse response){
        int flag = 0;
        JSONObject jsonObject = new JSONObject();
//        String id = request.getParameter("id");
//        SysUser sysUser = new SysUser();
//        if(!StringUtils.isEmpty(id)){
//            sysUser.setUserId(new BigInteger(id));
//            flag = sysUserService.delete(sysUser);
//        }
        flag = sysUserService.delete(sysUser);
        if(flag > 0){
            jsonObject.put("code",20000);
        }else {
            jsonObject.put("code",20000);
        }
        try {
            response.getWriter().print(jsonObject.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @RequestMapping("update")
    public void update(@RequestBody SysUser sysUser,HttpServletRequest request,HttpServletResponse response,@ModelAttribute SysUser user){
        int flag = 0;


        JSONObject jsonObject = new JSONObject();
//        String id = request.getParameter("id");
//
//        SysUser sysUser = new SysUser();
//        if(!StringUtils.isEmpty(id)){
//            sysUser.setUserId(new BigInteger(id));
//            flag = sysUserService.update(sysUser);
//        }
        flag = sysUserService.update(sysUser);
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
