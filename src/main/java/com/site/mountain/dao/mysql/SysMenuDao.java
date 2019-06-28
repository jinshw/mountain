package com.site.mountain.dao.mysql;

import com.site.mountain.entity.SysMenu;
import org.apache.ibatis.annotations.Param;

import java.math.BigInteger;
import java.util.List;

public interface SysMenuDao {
    int insert(@Param("pojo") SysMenu pojo);

    int insertSelective(SysMenu pojo);

    List<SysMenu> findList(SysMenu pojo);

    int delete(SysMenu pojo);

    List<SysMenu> selectByPid(SysMenu sysMenu);

    SysMenu selectByid(BigInteger id);

    List<SysMenu> selectByRoleId(BigInteger id);
}