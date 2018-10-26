package com.site.mountain.service;

import com.site.mountain.entity.SysUser;

import java.util.List;

public interface SysUserService {
    public int insert(SysUser pojo);

    public int insertSelective(SysUser pojo);

    public List findList(SysUser SysUser);

    public List<SysUser> selectAllUserAndRoles(SysUser SysUser);

    public int delete(SysUser SysUser);
}
