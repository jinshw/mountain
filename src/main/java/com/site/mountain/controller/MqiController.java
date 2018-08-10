package com.site.mountain.controller;

import com.site.mountain.entity.MQIBean;
import com.site.mountain.service.MQISerivce;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/mqi")
@Api(value = "MQI养护指数Controller",tags = {"养护指数接口"})
public class MqiController {
    @Autowired
    private MQISerivce mqiSerivce;

    @ApiOperation(value = "获取养护指数信息",tags ={"获取全部养护指数信息"}, notes = "注意问题点")
//    @ApiImplicitParam(name = "id", value = "学生ID", paramType = "path", required = true, dataType = "Integer")
    @RequestMapping("/find")
    @ResponseBody
    public List findMQI(@ApiParam(name = "id",value = "主键ID",required = false) String id,
                        @ApiParam(name = "养护指数对象",value = "传入json格式",required = true)MQIBean mqiBean) {
        List list = mqiSerivce.findList();
        System.out.print(list.size());
        return list;
    }
    @RequestMapping("/find2")
    @ResponseBody
    public List findMQI2() {
        List list = mqiSerivce.findList2();
        System.out.print(list.size());
        return list;
    }
    @RequestMapping("/oracle")
    @ResponseBody
    public List findOracle() {
        List list = mqiSerivce.findListByOracle();
        System.out.print(list.size());
        return list;
    }
}
