package com.site.mountain.service.impl;

import com.site.mountain.dao.test1.MQI1BeanDao;
import com.site.mountain.dao.oracle.NBaoAttrDao;
import com.site.mountain.dao.test2.MQI2BeanDao;
import com.site.mountain.entity.MQIBean;
import com.site.mountain.entity.NBaoAttr;
import com.site.mountain.service.MQISerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MQIServiceImpl implements MQISerivce{

    @Autowired
    private MQI1BeanDao mqi1BeanDao;
    @Autowired
    private MQI2BeanDao mqi2BeanDao;
    @Autowired
    private NBaoAttrDao nBaoAttrDao;


//    public int insert(MQIBean pojo){
//        return mQIBeanDao.insert(pojo);
//    }
//
//    public int insertSelective(MQIBean pojo){
//        return mQIBeanDao.insertSelective(pojo);
//    }
//
//    public int insertList(List<MQIBean> pojos){
//        return mQIBeanDao.insertList(pojos);
//    }
//
//    public int update(MQIBean pojo){
//        return mQIBeanDao.update(pojo);
//    }

    public List<MQIBean> findList(){
        return mqi1BeanDao.findAll();
    }
    public List<MQIBean> findList2(){
        return mqi2BeanDao.findAll();
    }
    public List<NBaoAttr> findListByOracle(){
        return nBaoAttrDao.findList(new NBaoAttr());
    }
}
