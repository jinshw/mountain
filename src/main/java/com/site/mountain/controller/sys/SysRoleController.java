package com.site.mountain.controller.sys;

import com.alibaba.fastjson.JSONObject;
import com.site.mountain.entity.SysRole;
import com.site.mountain.exception.MyException;
import com.site.mountain.service.SysRoleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/sysrole")
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;

    @RequestMapping(value = "list", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("role:list")
    public JSONObject findList(@RequestBody SysRole sysRole, HttpServletRequest request, HttpServletResponse response) {
        JSONObject jsonObject = new JSONObject();
        String searchText = request.getParameter("searchText");
        List list = sysRoleService.find(sysRole);
        jsonObject.put("code", 20000);
        jsonObject.put("data", list);
        return jsonObject;
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    @RequiresPermissions("role:add")
    public void insert(@RequestBody SysRole sysRole, HttpServletRequest request, HttpServletResponse response) {
        JSONObject jsonObject = new JSONObject();
//        String roleName = request.getParameter("roleName");
//        String remark = request.getParameter("remark");
//        SysRole sysRole = new SysRole();
//        sysRole.setRoleName(roleName);
//        sysRole.setAvailable(available);
//        sysRole.setRemark(remark);
        int flag = sysRoleService.insert(sysRole);
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
        }
    }


    @RequestMapping("delete")
    @RequiresPermissions("role:delete")
    public void delete(@RequestBody SysRole sysRole, HttpServletRequest request, HttpServletResponse response) {
        int flag = 0;
        JSONObject jsonObject = new JSONObject();
        flag = sysRoleService.delete(sysRole);

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
        }
    }

    @RequestMapping(value = "edit", method = RequestMethod.POST)
    @RequiresPermissions("role:edit")
    public void edit(@RequestBody SysRole sysRole, HttpServletRequest request, HttpServletResponse response) {
        JSONObject jsonObject = new JSONObject();
        int flag = sysRoleService.edit(sysRole);
        jsonObject.put("code", 20000);
        try {
            response.getWriter().print(jsonObject.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping(value = "id/{id}")
    public void getAge(@PathVariable("id") Integer id) {
        if (id < 10) {
            //返回"你还在上小学吧" code=100
            throw new MyException(100, "你还在上小学吧");
        } else if (id > 10 && id < 16) {
            //返回"你可能在上初中" code=101
            throw new MyException(101, "你可能在上初中");
        }
    }
}
