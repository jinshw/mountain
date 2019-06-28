package com.site.mountain.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.web.filter.authc.UserFilter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * 重写shiro的UserFilter，实现通过OPTIONS请求
 * @author MDY
 */
public class ShiroUserFilter0621 extends UserFilter {

//    @Override
//    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
//        if (request instanceof HttpServletRequest) {
//            if (((HttpServletRequest) request).getMethod().toUpperCase().equals("OPTIONS")) {
//                return true;
//            }
//        }
//        return super.isAccessAllowed(request, response, mappedValue);
//    }
//
//    @Override
//    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
//        HttpServletResponse res = (HttpServletResponse)response;
//        res.setHeader("Access-Control-Allow-Origin", "*");
//        res.setStatus(HttpServletResponse.SC_OK);
//        res.setCharacterEncoding("UTF-8");
//        PrintWriter writer = res.getWriter();
//        Map<String, Object> map= new HashMap<>();
//        map.put("code", 702);
//        map.put("msg", "未登录");
//        writer.write(JSON.toJSONString(map));
//        writer.close();
//        return false;
//    }
//
//    /**
//     * 在访问过来的时候检测是否为OPTIONS请求，如果是就直接返回true
//     */
//    @Override
//    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
//        HttpServletResponse httpResponse = (HttpServletResponse) response;
//        HttpServletRequest httpRequest = (HttpServletRequest) request;
//
//        httpResponse.addHeader("Access-Control-Allow-Origin", "*");
//        httpResponse.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
//        httpResponse.addHeader("Access-Control-Allow-Headers",
//                "Content-Type,X-Requested-With,accept,Origin,Access-Control-Request-Method,Access-Control-Request-Headers,token");
//
//        if (httpRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
//            setHeader(httpRequest,httpResponse);
//            return true;
//        }
//
//        return super.preHandle(request,response);
//    }
//
//    /**
//     * 该方法会在验证失败后调用，这里由于是前后端分离，后台不控制页面跳转
//     * 因此重写改成传输JSON数据
//     */
//    @Override
//    protected void saveRequestAndRedirectToLogin(ServletRequest request, ServletResponse response) throws IOException {
//        JSONObject jsonObject = new JSONObject();
//        HttpServletResponse httpResponse = (HttpServletResponse) response;
//        HttpServletRequest httpRequest = (HttpServletRequest) request;
//        if (httpRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
////            jsonObject.put("code",20000);
////            jsonObject.put("msg","跳转页面");
//        }else{
////            JSONObject obj = new JSONObject();
////            obj.put("token","admin");
////            jsonObject.put("code",20000);
////            jsonObject.put("msg","跳转页面");
////            jsonObject.put("data",obj);
//        }
//        jsonObject.put("code",20000);
//        jsonObject.put("msg","跳转页面");
//        saveRequest(request);
//        setHeader((HttpServletRequest) request,(HttpServletResponse) response);
//        PrintWriter out = response.getWriter();
//        out.println(jsonObject.toJSONString());
//        out.flush();
//        out.close();
//    }
//
//    /**
//     * 为response设置header，实现跨域
//     */
//    private void setHeader(HttpServletRequest request,HttpServletResponse response){
//        //跨域的header设置
//        response.setHeader("Access-control-Allow-Origin", "*");
//        response.setHeader("Access-Control-Allow-Methods", request.getMethod());
//        response.setHeader("Access-Control-Allow-Credentials", "true");
//        response.setHeader("Access-Control-Allow-Headers", "Content-Type,X-Requested-With,accept,Origin,Access-Control-Request-Method,Access-Control-Request-Headers,token");
//        //防止乱码，适用于传输JSON数据
//        response.setHeader("Content-Type","application/json;charset=UTF-8");
//        response.setStatus(HttpStatus.OK.value());
//    }
}
