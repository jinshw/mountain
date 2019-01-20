package com.site.mountain.controller.sys;

import com.alibaba.fastjson.JSONObject;
import com.site.mountain.entity.SysMenu;
import com.site.mountain.entity.SysPermission;
import com.site.mountain.service.SysMenuService;
import com.site.mountain.service.SysPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
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
    public List findList(HttpServletRequest request, HttpServletResponse response) {
        String searchText = request.getParameter("searchText");
        List list = sysMenuService.findList(new SysMenu());
        return list;
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public void insert(HttpServletRequest request, HttpServletResponse response) {
        JSONObject jsonObject = new JSONObject();
        String name = request.getParameter("name");
        String type = request.getParameter("type");
        String url = request.getParameter("url");
        String perms = request.getParameter("perms");
        String parentId = request.getParameter("parentId");
        String icon = request.getParameter("icon");
        String orderNum = request.getParameter("orderNum");

        SysMenu sysMenu = new SysMenu();
        sysMenu.setName(name);
        sysMenu.setType(Integer.valueOf(type));
        sysMenu.setUrl(url);
        sysMenu.setPerms(perms);

        if(!StringUtils.isEmpty(parentId)){
            sysMenu.setParentId(new BigInteger(parentId));
        }
        sysMenu.setIcon(icon);
        sysMenu.setOrderNum(Integer.valueOf(orderNum));

        int flag = sysMenuService.insert(sysMenu);
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
        SysMenu tree = new SysMenu();
        tree = sysMenuService.getMenuTree(new BigInteger(id));
        if(tree.getChildren().size() == 0){
            SysMenu sysMenu = new SysMenu();
            sysMenu.setMenuId(new BigInteger(id));
            int flag = sysMenuService.delete(sysMenu);
            if (flag > 0) {
                jsonObject.put("status", 200);
            } else {
                jsonObject.put("status", 500);
            }
        }else{
            jsonObject.put("status", 201);
        }
        try {
            response.getWriter().print(jsonObject.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @RequestMapping("getTree")
    @ResponseBody
    public SysMenu getTree(HttpServletRequest request,HttpServletResponse response){
        String menuId = request.getParameter("menuId");
        SysMenu tree = new SysMenu();
        tree = sysMenuService.getMenuTree(new BigInteger(menuId));
        return tree;
    }

}
