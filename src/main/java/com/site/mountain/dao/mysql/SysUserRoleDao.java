package com.site.mountain.dao.mysql;

import java.util.List;
import com.site.mountain.entity.SysUserRole;

public interface SysUserRoleDao {
    int insert(SysUserRole pojo);
    int insertSelective(List<SysUserRole> pojo);
    List<String> findList(SysUserRole pojo);
    int delete(SysUserRole pojo);

}