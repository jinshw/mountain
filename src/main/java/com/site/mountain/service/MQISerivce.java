package com.site.mountain.service;

import com.site.mountain.entity.MQIBean;
import com.site.mountain.entity.NBaoAttr;

import java.util.List;

public interface MQISerivce {
    public List<MQIBean> findList();
    public List<MQIBean> findList2();
    public List<NBaoAttr> findListByOracle();
}
