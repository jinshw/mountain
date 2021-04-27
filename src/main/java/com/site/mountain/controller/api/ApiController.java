package com.site.mountain.controller.api;

import com.site.mountain.common.Code;
import com.site.mountain.common.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("api")
public class ApiController {

    @RequestMapping(value = "test",method = RequestMethod.POST)
    @ResponseBody
    public Result test() {
        List<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(11);
        list.add(111);
        list.add(1111);
        Result result = new Result();
        result.setCode(Code.OK);
        result.setMessage("访问成功OK");
        result.setData(list);
        return result;
    }
}
