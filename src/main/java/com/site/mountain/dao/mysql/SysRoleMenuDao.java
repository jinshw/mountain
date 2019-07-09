package com.site.mountain.dao.mysql;

import java.util.List;
import com.site.mountain.entity.SysRoleMenu;

public interface SysRoleMenuDao {
    int insert(SysRoleMenu pojo);
    int insertSelective(List<SysRoleMenu> pojo);
    List<String> findList(SysRoleMenu pojo);
    Integer findCount(SysRoleMenu pojo);
    int delete(SysRoleMenu pojo);

}