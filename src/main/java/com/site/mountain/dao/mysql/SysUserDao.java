package com.site.mountain.dao.mysql;

import com.site.mountain.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigInteger;
import java.util.List;

@Mapper
public interface SysUserDao {
    int insert(SysUser pojo);

    int insertUserAndRole(SysUser pojo);

    int insertSelective(SysUser pojo);

    List<String> findList(SysUser pojo);

    SysUser findUser(BigInteger createPerson);

    int delete(SysUser pojo);

    int deleteUserAndRole(SysUser pojo);

    List<SysUser> selectAllUserAndRoles(SysUser sysUser);

    int update(@Param("pojo") SysUser projo);
}