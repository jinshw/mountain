package com.site.mountain.service;

import com.site.mountain.entity.SysPermission;

import java.util.List;

public interface SysPermissionService {
    public int insert(SysPermission pojo);

    public int insertSelective(SysPermission pojo);

    public int insertList(List<SysPermission> pojos);

    public int update(SysPermission pojo);

    public List<SysPermission> findList(SysPermission sysPermission);
}
