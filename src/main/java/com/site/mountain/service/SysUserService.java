package com.site.mountain.service;

import com.site.mountain.entity.SysMenu;
import com.site.mountain.entity.SysUser;

import java.util.List;

public interface SysUserService {
    public int insert(SysUser pojo);

    public int insertSelective(SysUser pojo);

    public List findList(SysUser SysUser);

    public List<SysUser> selectAllUserAndRoles(SysUser SysUser);

    public int delete(SysUser SysUser);

    public int update(SysUser sysUser);

    public SysMenu getMenuTree(SysUser sysUser);
}
