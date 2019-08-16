package com.site.mountain.exception;

import com.alibaba.fastjson.JSONObject;
import com.site.mountain.common.Result;
import com.site.mountain.utils.ResultUtil;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
public class GlobalDefaultExceptionHandler {
    private final static Logger logger = LoggerFactory.getLogger(GlobalDefaultExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result defaultExceptionHandler(Exception e) throws IOException {
        if (e instanceof MyException) {
            MyException myException = (MyException) e;
            return ResultUtil.error(myException.getCode(), myException.getMessage());
        } else {
            //将系统异常以打印出来
            logger.info("[系统异常]{}", e);
            return ResultUtil.error(-1, "未知错误");
        }

    }
}
