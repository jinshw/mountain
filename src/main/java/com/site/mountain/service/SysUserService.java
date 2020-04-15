package com.site.mountain.service;

import com.github.pagehelper.PageInfo;
import com.site.mountain.entity.SysMenu;
import com.site.mountain.entity.SysUser;

import java.util.List;

public interface SysUserService {
    int insert(SysUser pojo);

    int insertSelective(SysUser pojo);

    List findList(SysUser SysUser);

    PageInfo<SysUser> findListPage(SysUser pojo);

    List<SysUser> selectAllUserAndRoles(SysUser SysUser);

    int delete(SysUser SysUser);

    int update(SysUser sysUser);

    SysMenu getMenuTree(SysUser sysUser);

    int updatePassword(SysUser sysUser);
}
