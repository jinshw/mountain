package com.site.mountain.dao.mysql;

import com.site.mountain.entity.SysRoleDept;

import java.util.List;

public interface SysRoleDeptDao {
    int insert(SysRoleDept pojo);
    int insertSelective(List<SysRoleDept> pojo);
    List<String> findList(SysRoleDept pojo);
    int delete(SysRoleDept pojo);

}