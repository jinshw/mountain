package com.site.mountain.service;

import com.site.mountain.entity.SysMenu;
import com.site.mountain.entity.SysPermission;

import java.math.BigInteger;
import java.util.List;

public interface SysMenuService {
    public int insert(SysMenu pojo);

    public int insertSelective(SysMenu pojo);

//    public int insertList(List<SysPermission> pojos);

    public int update(SysMenu pojo);

    public List<SysMenu> findList(SysMenu sysMenu);

    public int delete(SysMenu sysMenu);

    public SysMenu getMenuTree(BigInteger id);
}
