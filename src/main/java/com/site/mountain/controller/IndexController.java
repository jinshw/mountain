package com.site.mountain.controller;

import com.site.mountain.service.MQISerivce;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class IndexController {


    @RequestMapping("/")
    public ModelAndView index() {
        return new ModelAndView("index");
    }
}
