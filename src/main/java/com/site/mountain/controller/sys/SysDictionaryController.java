package com.site.mountain.controller.sys;

import com.site.mountain.entity.SysDictionary;
import com.site.mountain.service.SysDictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/sysdict")
public class SysDictionaryController {

    @Autowired
    public SysDictionaryService sysDictionaryService;

    @RequestMapping(value = "list", method = RequestMethod.POST)
    @ResponseBody
    public Map findList(@RequestBody SysDictionary sysDictionary) {
        Map map = new HashMap();
        List list = sysDictionaryService.findList(sysDictionary);
        map.put("code", 20000);
        map.put("data", list);
        return map;
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public Map insert(@RequestBody SysDictionary sysDictionary) {
        Map map = new HashMap();
        int flag = sysDictionaryService.insert(sysDictionary);
        if (flag > 0) {
            map.put("status", 30000);
        } else {
            map.put("status", 30001);
        }
        map.put("code", 20000);
        return map;
    }

    @RequestMapping(value = "edit", method = RequestMethod.POST)
    @ResponseBody
    public Map edit(@RequestBody SysDictionary sysDictionary) {
        Map map = new HashMap();
        int flag = sysDictionaryService.update(sysDictionary);
        if (flag > 0) {
            map.put("status", 30000);
        } else {
            map.put("status", 30001);
        }
        map.put("code", 20000);
        return map;
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public Map delete(@RequestBody SysDictionary sysDictionary) {
        Map map = new HashMap();
        int flag = sysDictionaryService.delete(sysDictionary);
        if (flag > 0) {
            map.put("status", 30000);
        } else {
            map.put("status", 30001);
        }
        map.put("code", 20000);
        return map;
    }

}
