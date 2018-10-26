package com.site.mountain.controller;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.site.mountain.entity.SysPermission;
import com.site.mountain.entity.SysRole;
import com.site.mountain.service.SysPermissionService;
import com.site.mountain.service.SysRoleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.hibernate.validator.constraints.LuhnCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 菜单管理
 */
@Controller
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private SysPermissionService sysPermissionService;


    @RequestMapping(value = "list", method = RequestMethod.POST)
    @ResponseBody
    public List findList(HttpServletRequest request, HttpServletResponse response) {
        String searchText = request.getParameter("searchText");
        List list = sysPermissionService.findList(new SysPermission());
        return list;
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public void insert(HttpServletRequest request, HttpServletResponse response) {
        JSONObject jsonObject = new JSONObject();
        String name = request.getParameter("name");
        String resourceType = request.getParameter("resource_type");
        String url = request.getParameter("url");
        String permission = request.getParameter("permission");
        String parentId = request.getParameter("parent_id");
        SysPermission sysPermission = new SysPermission();
        sysPermission.setName(name);
        sysPermission.setResourceType(resourceType);
        sysPermission.setUrl(url);
        sysPermission.setPermission(permission);
        sysPermission.setParentId(Long.valueOf(parentId));
        sysPermission.setParentIds("");

        int flag = sysPermissionService.insert(sysPermission);
        System.out.println(flag);
        if (flag > 0) {
            jsonObject.put("status", 200);
        } else {
            jsonObject.put("status", 500);
        }
        try {
            response.getWriter().print(jsonObject.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("delete")
    public void delete(HttpServletRequest request, HttpServletResponse response) {
        JSONObject jsonObject = new JSONObject();
        String id = request.getParameter("id");
        SysPermission sysPermission = new SysPermission();
        sysPermission.setId(Integer.valueOf(id));
        int flag = sysPermissionService.delete(sysPermission);
        if (flag > 0) {
            jsonObject.put("status", 200);
        } else {
            jsonObject.put("status", 500);
        }
        try {
            response.getWriter().print(jsonObject.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @RequestMapping("getTree")
    @ResponseBody
    public SysPermission getTree(HttpServletRequest request,HttpServletResponse response){
        SysPermission tree = new SysPermission();
        tree = sysPermissionService.getMenuTree(-1);
        return tree;
    }

}
