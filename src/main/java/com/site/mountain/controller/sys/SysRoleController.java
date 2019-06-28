package com.site.mountain.controller.sys;

import com.alibaba.fastjson.JSONObject;
import com.site.mountain.entity.SysRole;
import com.site.mountain.service.SysPermissionService;
import com.site.mountain.service.SysRoleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
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

@Controller
@RequestMapping("/sysrole")
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysPermissionService sysPermissionService;


    @RequestMapping(value = "list", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("userInfo:view")
    public JSONObject findList(@RequestBody SysRole sysRole, HttpServletRequest request, HttpServletResponse response) {
        JSONObject jsonObject = new JSONObject();
        String searchText = request.getParameter("searchText");
//        SysRole sysRole = new SysRole();
//        sysRole.setRoleName(searchText);
        List list = sysRoleService.find(sysRole);
        jsonObject.put("code",20000);
        jsonObject.put("data",list);
        return jsonObject;
    }

    @RequestMapping(value = "add",method = RequestMethod.POST)
    public void insert(@RequestBody SysRole sysRole, HttpServletRequest request,HttpServletResponse response){
        JSONObject jsonObject = new JSONObject();
//        String roleName = request.getParameter("roleName");
//        String remark = request.getParameter("remark");
//        SysRole sysRole = new SysRole();
//        sysRole.setRoleName(roleName);
//        sysRole.setAvailable(available);
//        sysRole.setRemark(remark);
        int flag = sysRoleService.insert(sysRole);
        System.out.println(flag);
        if(flag>0){
            jsonObject.put("status",200);
        }else {
            jsonObject.put("status",500);
        }
        jsonObject.put("code",20000);
        try {
            response.getWriter().print(jsonObject.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @RequestMapping("delete")
    public void delete(@RequestBody SysRole sysRole,HttpServletRequest request,HttpServletResponse response){
        int flag = 0;
        JSONObject jsonObject = new JSONObject();
//        String roleId = request.getParameter("roleId");
//        SysRole sysRole = new SysRole();
//        if(!StringUtils.isEmpty(roleId)){
//            sysRole.setRoleId(new BigInteger(roleId));
//            flag = sysRoleService.delete(sysRole);
//        }
        flag = sysRoleService.delete(sysRole);

        if(flag > 0){
            jsonObject.put("status",200);
        }else {
            jsonObject.put("status",500);
        }
        jsonObject.put("code",20000);
        try {
            response.getWriter().print(jsonObject.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
