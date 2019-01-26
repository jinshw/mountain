package com.site.mountain.exception;

import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
public class GlobalDefaultExceptionHandler {
    JSONObject jsonObject = new JSONObject();

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseBody
    public void defaultExceptionHandler(HttpServletRequest request, HttpServletResponse response) throws IOException {
        jsonObject.put("code",600);
        jsonObject.put("msg","对不起，你没有访问权限！");
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().print(jsonObject.toString());
    }
}
