package com.site.mountain.filter;

import com.alibaba.fastjson.JSONObject;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截器(现在没有用到)
 */
//@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    JSONObject jsonObject = new JSONObject();
    //不需要登录就可以访问的路径(比如:注册登录等)
    String[] includeUrls = new String[]{ "/mt/loginCross"};


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new HandlerInterceptor() {
            @Override
            public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
                response.addHeader("Access-Control-Allow-Origin", "*");
                response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
                response.addHeader("Access-Control-Allow-Headers",
                        "Content-Type,X-Requested-With,accept,Origin,Access-Control-Request-Method,Access-Control-Request-Headers,token");

                return true;
            }

            @Override
            public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
                String uri = request.getRequestURI();

                System.out.println("webmvcconfig url:" + uri);
                //是否需要过滤
                boolean needFilter = isNeedFilter(uri);
                if(uri.contains("loginCross") || uri.contains("/mt/error")){
//                    response.sendRedirect("http://localhost:8081/mt/loginCross");
                    jsonObject.put("code", 200);
                    jsonObject.put("msg", "您还未登录");
                    response.getWriter().write(jsonObject.toString());
                }

                try {
                    if (!needFilter) { //不需要过滤直接传给下一个过滤器

                    } else {
                        if (request.getSession().getAttribute("user") == null) {
                            String requestType = request.getHeader("X-Requested-With");
                            jsonObject.put("code", 405);
                            jsonObject.put("msg", "您还未登录");
                            response.getWriter().write(jsonObject.toString());
                        }
                    }
                } catch (Exception e) {
                    jsonObject.put("code", 405);
                    jsonObject.put("msg", "您还未登录");
                    response.getWriter().write(jsonObject.toString());
                    e.printStackTrace();
                }


            }

            @Override
            public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

            }
        });
    }

    /**
     * @param uri
     * @Author: xxxxx
     * @Description: 是否需要过滤
     * @Date: 2018-03-12 13:20:54
     */
    public boolean isNeedFilter(String uri) {

        for (String includeUrl : includeUrls) {
            if (uri.contains(includeUrl)) {
                return false;
            }
        }

        return true;
    }
}
