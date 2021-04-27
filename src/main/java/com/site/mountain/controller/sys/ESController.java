package com.site.mountain.controller.sys;

import com.site.mountain.entity.Person;
import com.site.mountain.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/es")
@ResponseBody
public class ESController {
    @Autowired
    PersonService personService;

    @RequestMapping(value = "add",method = RequestMethod.POST)
    public Map add(){
        Map map = new HashMap();
        map.put("code",20000);
        map.put("message","success");
        Person person1 = new Person(1,"zhangsan",22,"1988-02-02");
        Person person2 = new Person(2,"zhangsan22",222,"1988-02-03");
        Person person3 = new Person(3,"zhangsan33",333,"1988-03-04");

        boolean result = personService.insert(person1);
        result = personService.insert(person2);
        result = personService.insert(person3);
        return map;
    }
}
