package com.site.mountain.service.impl;

import com.site.mountain.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import com.site.mountain.entity.UserInfo;
import com.site.mountain.dao.test1.UserInfoDao;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoDao userInfoDao;

    public int insert(UserInfo pojo) {
        return userInfoDao.insert(pojo);
    }

    public int insertSelective(UserInfo pojo) {
        return userInfoDao.insertSelective(pojo);
    }

    public int insertList(List<UserInfo> pojos) {
        return userInfoDao.insertList(pojos);
    }

    public int update(UserInfo pojo) {
        return userInfoDao.update(pojo);
    }

    public List findList(UserInfo userInfo) {
        return userInfoDao.findList(userInfo);
    }

    public List<UserInfo> selectAllUserAndRoles(UserInfo userInfo){
        return userInfoDao.selectAllUserAndRoles(userInfo);
    }
}
