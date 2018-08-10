package com.site.mountain.service;

import com.site.mountain.entity.SysRole;

import java.util.List;

public interface SysRoleService {
    public int insert(SysRole pojo);

    public int insertSelective(SysRole pojo);

    public int insertList(List<SysRole> pojos);

    public int update(SysRole pojo);

    public List<SysRole> findRoleList(SysRole sysRole);
}
