package com.site.mountain.exception;

import com.alibaba.fastjson.JSONObject;
import com.site.mountain.common.Result;
import com.site.mountain.utils.JSONResult;
import com.site.mountain.utils.ResultUtil;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalDefaultExceptionHandler {
    private final static Logger logger = LoggerFactory.getLogger(GlobalDefaultExceptionHandler.class);
    public static final String DEFAULT_ERROR_VIEW = "error/error";

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Object defaultExceptionHandler(HttpServletRequest request,
                                          HttpServletResponse response,Exception e) throws IOException {
        if (e instanceof MyException) {
            MyException myException = (MyException) e;
            return ResultUtil.error(myException.getCode(), myException.getMessage());
        } else {
            //将系统异常以打印出来
//            logger.info("[系统异常]{}", e);
//            return ResultUtil.error(-1, "未知错误");

            e.printStackTrace();
            if (isAjax(request)) {
                return ajaxException(e, request, response);
            } else {
                //不是异步请求
                return exceptionPD(e, request);
            }
        }
    }


    public boolean isAjax(HttpServletRequest request) {
        return (request.getHeader("X-Requested-With") != null &&
                "XMLHttpRequest".equals(request.getHeader("X-Requested-With").toString()));
    }

    /**
     * 不是ajax请求的异常方法
     *
     * @param ex
     * @param request
     * @return
     */
    public ModelAndView exceptionPD(Exception ex, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        if (ex instanceof UnauthenticatedException) {
            modelAndView.addObject("msg", "认证时间已过,请重新登录");
            modelAndView.addObject("login", 0);
        } else if (ex instanceof UnauthorizedException) {
            modelAndView.addObject("msg", "你还没有该访问权限！！！请联系管理员吧");
        } else if (ex instanceof Exception) {
            modelAndView.addObject("msg", "系统异常");

        }
        modelAndView.setViewName(DEFAULT_ERROR_VIEW);
        return modelAndView;
    }

    /**
     * ajax异常处理方法
     *
     * @param ex
     * @param request
     * @param response
     * @return
     */
    public JSONResult ajaxException(Exception ex, HttpServletRequest request, HttpServletResponse response) {
        JSONResult json = new JSONResult();
        if (ex instanceof UnauthenticatedException) {
            json.setCode(50014);
            json.setMessage("未认证或者session过期");
            Map<String, Object> map = new HashMap<>();
        } else if (ex instanceof UnauthorizedException) {
            json.setMessage("没有权限");
            json.setCode(50015);
            Map<String, Object> map = new HashMap<>();
        } else if (ex instanceof Exception) {
            json.setCode(50008);
            json.setMessage("系统异常");
        }
        return json;
    }
}
