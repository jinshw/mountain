package com.site.mountain.service;

import com.site.mountain.entity.SysRole;

import java.util.List;

public interface SysRoleService {
    public int insert(SysRole pojo);

    public List<SysRole> find(SysRole sysRole);

    public int delete(SysRole sysRole);

    public int edit(SysRole sysRole);
}
