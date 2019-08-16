package com.site.mountain.controller;

import com.site.mountain.exception.MyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
@ResponseBody
@RequestMapping(value = "error")
public class ExceptionController {

    @GetMapping(value = "id/{id}")
    public void getAge(@PathVariable("id") Integer id) {
        if (id < 10) {
            //返回"你还在上小学吧" code=100
            throw new MyException(20000, "你还在上小学吧");
        } else if (id > 10 && id < 16) {
            //返回"你可能在上初中" code=101
            throw new MyException(40000, "你可能在上初中");
        }
    }
}
