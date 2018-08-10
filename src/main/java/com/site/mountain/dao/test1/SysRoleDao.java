package com.site.mountain.dao.test1;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import com.site.mountain.entity.SysRole;

@Mapper
public interface SysRoleDao {
    int insert(@Param("pojo") SysRole pojo);

    int insertSelective(@Param("pojo") SysRole pojo);

    int insertList(@Param("pojos") List<SysRole> pojo);

    int update(@Param("pojo") SysRole pojo);

    List<SysRole> findRoleList(SysRole sysRole);

}
