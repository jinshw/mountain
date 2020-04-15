package com.site.mountain.dao.mysql;

import com.site.mountain.entity.SysUserRole;

import java.util.List;

public interface SysUserRoleDao {
    int insert(SysUserRole pojo);
    int insertSelective(List<SysUserRole> pojo);
    List<SysUserRole> findList(SysUserRole pojo);
    int delete(SysUserRole pojo);

}