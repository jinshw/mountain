package com.site.mountain.controller;

import com.alibaba.fastjson.JSONObject;
import com.site.mountain.entity.SysPermission;
import com.site.mountain.entity.SysRole;
import com.site.mountain.entity.UserInfo;
import com.site.mountain.service.SysPermissionService;
import com.site.mountain.service.SysRoleService;
import com.site.mountain.service.UserInfoService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysPermissionService sysPermissionService;

//
//    @RequestMapping(value = "list", method = RequestMethod.POST)
//    @ResponseBody
//    @RequiresPermissions("userInfo:view")
//    public List findList(HttpServletRequest request, HttpServletResponse response) {
//        String searchText = request.getParameter("searchText");
//        SysRole sysRole = new SysRole();
//        sysRole.setRole(searchText);
//        List list = sysRoleService.find(sysRole);
//        return list;
//    }
//
//    @RequestMapping(value = "add",method = RequestMethod.POST)
//    public void insert(HttpServletRequest request,HttpServletResponse response){
//        JSONObject jsonObject = new JSONObject();
//        String role = request.getParameter("role");
//        String available = request.getParameter("available");
//        String description = request.getParameter("description");
//        SysRole sysRole = new SysRole();
//        sysRole.setRole(role);
////        sysRole.setAvailable(available);
//        sysRole.setDescription(description);
//        int flag = sysRoleService.insert(sysRole);
//        System.out.println(flag);
//        if(flag>0){
//            jsonObject.put("status",200);
//        }else {
//            jsonObject.put("status",500);
//        }
//
//        try {
//            response.getWriter().print(jsonObject.toJSONString());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @RequestMapping("delete")
//    public void delete(HttpServletRequest request,HttpServletResponse response){
//        JSONObject jsonObject = new JSONObject();
//        String id = request.getParameter("id");
//        SysRole sysRole = new SysRole();
//        sysRole.setId(Integer.valueOf(id));
//        int flag = sysRoleService.delete(sysRole);
//        if(flag > 0){
//            jsonObject.put("status",200);
//        }else {
//            jsonObject.put("status",500);
//        }
//        try {
//            response.getWriter().print(jsonObject.toJSONString());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }


}
