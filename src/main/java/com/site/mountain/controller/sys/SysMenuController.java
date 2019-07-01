package com.site.mountain.controller.sys;

import com.alibaba.fastjson.JSONObject;
import com.site.mountain.entity.SysMenu;
import com.site.mountain.entity.SysPermission;
import com.site.mountain.service.SysMenuService;
import com.site.mountain.service.SysPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

/**
 * 菜单管理
 */
@Controller
@RequestMapping("/sysmenu")
public class SysMenuController {

    @Autowired
    private SysMenuService sysMenuService;


    @RequestMapping(value = "list", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject findList(@RequestBody SysMenu sysMenu, HttpServletRequest request, HttpServletResponse response) {
        JSONObject jsonObject = new JSONObject();
//        String searchText = request.getParameter("searchText");
        List list = sysMenuService.findList(sysMenu);
        jsonObject.put("code", 20000);
        jsonObject.put("data", list);
        return jsonObject;
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public void insert(@RequestBody SysMenu sysMenu, HttpServletRequest request, HttpServletResponse response) {
        JSONObject jsonObject = new JSONObject();
        int flag = sysMenuService.insert(sysMenu);
        System.out.println(flag);
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
            jsonObject.put("code", 20001);
        }
    }

    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public void edit(@RequestBody SysMenu sysMenu, HttpServletRequest request, HttpServletResponse response) {
        JSONObject jsonObject = new JSONObject();
        int flag = sysMenuService.update(sysMenu);
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
            jsonObject.put("code", 20001);
        }
    }

    @RequestMapping("delete")
    public void delete(@RequestBody SysMenu sysMenuParam, HttpServletRequest request, HttpServletResponse response) {
        JSONObject jsonObject = new JSONObject();
//        String id = request.getParameter("id");
        BigInteger menuId = sysMenuParam.getMenuId();
        SysMenu tree = new SysMenu();
        tree = sysMenuService.getMenuTree(menuId);
        if (tree.getChildren().size() == 0) {
            SysMenu sysMenu = new SysMenu();
            sysMenu.setMenuId(menuId);
            int flag = sysMenuService.delete(sysMenu);
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
            jsonObject.put("code", 20001);
        }
    }


    @RequestMapping("getTree")
    @ResponseBody
    public JSONObject getTree(@RequestBody SysMenu sysMenu, HttpServletRequest request, HttpServletResponse response) {
//        String menuId = request.getParameter("menuId");
        JSONObject jsonObject = new JSONObject();
        SysMenu tree = new SysMenu();
        tree = sysMenuService.getMenuTree(sysMenu.getMenuId());
        jsonObject.put("code", 20000);
        jsonObject.put("data", tree);
        return jsonObject;
    }

}
