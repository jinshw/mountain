package com.site.mountain.dao.test1;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

import com.site.mountain.entity.UserInfo;

@Mapper
public interface UserInfoDao {
    int insert(@Param("pojo") UserInfo pojo);

    int add(@Param("pojo") UserInfo pojo);

    int insertSelective(@Param("pojo") UserInfo pojo);

    int insertList(@Param("pojos") List<UserInfo> pojo);

    int update(@Param("pojo") UserInfo pojo);

    List<String> findList(UserInfo userInfo);

//    List<UserInfo> selectAllUserAndRoles(UserInfo userInfo);

    int delete(UserInfo userInfo);
}
