package com.site.mountain.dao.mysql;

import com.site.mountain.entity.SysRoleMenu;

import java.util.List;

public interface SysRoleMenuDao {
    int insert(SysRoleMenu pojo);
    int insertSelective(List<SysRoleMenu> pojo);
    List<String> findList(SysRoleMenu pojo);
    Integer findCount(SysRoleMenu pojo);
    int delete(SysRoleMenu pojo);

}