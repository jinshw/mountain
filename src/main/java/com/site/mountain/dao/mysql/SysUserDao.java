package com.site.mountain.dao.mysql;

import com.site.mountain.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysUserDao {
    int insert(@Param("pojo") SysUser pojo);
    int insertSelective( SysUser pojo);
    List<String> findList(SysUser pojo);
    int delete( SysUser pojo);
    List<SysUser> selectAllUserAndRoles( SysUser sysUser);
    int update(@Param("pojo") SysUser projo);
}