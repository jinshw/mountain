package com.site.mountain.controller.sys;

import com.alibaba.fastjson.JSONObject;
import com.site.mountain.entity.SysDept;
import com.site.mountain.service.SysDeptService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
@RequestMapping("/sysdept")
public class SysDeptController {

    @Autowired
    SysDeptService sysDeptService;


    public List<SysDept> findList(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }

    @RequestMapping("getTree")
    @ResponseBody
    public JSONObject getTree(@RequestBody SysDept sysDept, HttpServletRequest request, HttpServletResponse response) {
        JSONObject jsonObject = new JSONObject();
        SysDept tree = new SysDept();
        tree = sysDeptService.getMenuTree(sysDept.getDeptId());
        jsonObject.put("code", 20000);
        jsonObject.put("data", tree);
        return jsonObject;
    }

    @ApiOperation(value="创建用户", notes="根据User对象创建用户")
    @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public void insert(@RequestBody SysDept sysDept, HttpServletRequest request, HttpServletResponse response) {
        JSONObject jsonObject = new JSONObject();
        int flag = sysDeptService.insert(sysDept);
        System.out.println(flag);
        jsonObject.put("code", 20000);
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

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public void delete(@RequestBody SysDept sysDeptParam, HttpServletRequest request, HttpServletResponse response) {
        int flag = 0;
        JSONObject jsonObject = new JSONObject();
        BigInteger deptId = sysDeptParam.getDeptId();
        SysDept tree = new SysDept();
        tree = sysDeptService.getMenuTree(deptId);
        if (tree.getChildren().size() == 0) {
            SysDept sysDept = new SysDept();
            sysDept.setDeptId(deptId);
            sysDept.setDelFlag(-1);
            flag = sysDeptService.delete(sysDept);
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
        }
    }

    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public void edit(@RequestBody SysDept sysDept, HttpServletRequest request, HttpServletResponse response) {
        JSONObject jsonObject = new JSONObject();
        int flag = sysDeptService.update(sysDept);
        System.out.println(flag);
        jsonObject.put("code", 20000);
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

}
