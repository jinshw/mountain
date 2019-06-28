package com.site.mountain.dao.mysql;

import java.math.BigInteger;
import java.util.List;
import com.site.mountain.entity.SysRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SysRoleDao {
    int insert(SysRole pojo);
    int insertRoleAndMenu(SysRole pojo);
    int insertRoleAndDept(SysRole pojo);
    int insertSelective(List<SysRole> pojo);
    List<SysRole> findList(SysRole pojo);
    int delete(SysRole pojo);
    int deleteRoleMenu(SysRole pojo);
    int deleteRoleDept(SysRole pojo);
    int update(@Param("pojo") SysRole pojo);

}