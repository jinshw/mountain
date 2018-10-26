package com.site.mountain.dao.test1;

import com.site.mountain.entity.SysPermission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysPermissionDao {
    int insert(@Param("pojo") SysPermission pojo);

    int insertSelective(@Param("pojo") SysPermission pojo);

    int insertList(@Param("pojos") List<SysPermission> pojo);

    int update(@Param("pojo") SysPermission pojo);

    List<SysPermission> findList(SysPermission sysPermission);

    int delete(SysPermission sysPermission);

    List<SysPermission> selectByPid(SysPermission sysPermission);

    SysPermission selectByid(Integer id);
}
