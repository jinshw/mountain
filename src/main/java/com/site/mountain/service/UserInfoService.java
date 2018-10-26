package com.site.mountain.service;

import com.site.mountain.entity.UserInfo;

import java.util.List;

public interface UserInfoService {
    public int insert(UserInfo pojo);

    public int add(UserInfo pojo);

    public int insertSelective(UserInfo pojo);

    public int insertList(List<UserInfo> pojos);

    public int update(UserInfo pojo);

    public List findList(UserInfo userInfo);

    public List<UserInfo> selectAllUserAndRoles(UserInfo userInfo);

    public int delete(UserInfo userInfo);
}
