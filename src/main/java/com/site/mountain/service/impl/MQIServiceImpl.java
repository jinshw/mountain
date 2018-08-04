package com.site.mountain.service.impl;

import com.site.mountain.service.MQISerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.site.mountain.entity.MQIBean;
import com.site.mountain.dao.MQIBeanDao;

@Service
public class MQIServiceImpl implements MQISerivce{

    @Autowired
    private MQIBeanDao mQIBeanDao;

    public int insert(MQIBean pojo){
        return mQIBeanDao.insert(pojo);
    }

    public int insertSelective(MQIBean pojo){
        return mQIBeanDao.insertSelective(pojo);
    }

    public int insertList(List<MQIBean> pojos){
        return mQIBeanDao.insertList(pojos);
    }

    public int update(MQIBean pojo){
        return mQIBeanDao.update(pojo);
    }

    public List<MQIBean> findList(){
        return mQIBeanDao.findAll();
    }
}
