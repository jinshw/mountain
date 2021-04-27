package com.site.mountain.filter;

import com.alibaba.fastjson.JSONObject;
import com.site.mountain.common.Code;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.SignatureException;

public class ApiInterceptor implements HandlerInterceptor {
    //可以在这里设置各种规则，取到token后解析，来验证token有效性，有效期等等。这里仅仅验证了是不是token为空。
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String token = httpServletRequest.getHeader("jwt-token");//这个就是从http头中取约定好的token的key。
        try{
            if(token==null||token.trim().equals("")){
                throw new SignatureException("jwt-token is null");
            }
        }catch (SignatureException e){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("message","请求参数中找不到Token");
            jsonObject.put("code", Code.NO_TOKEN);
            createSuccessResponse(jsonObject,httpServletResponse);
            return false;
        }

        return true;
    }

    public void createSuccessResponse(JSONObject jsonObject,HttpServletResponse response){
        try {
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().print(jsonObject.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
