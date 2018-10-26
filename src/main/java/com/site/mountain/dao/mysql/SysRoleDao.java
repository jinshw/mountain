package com.site.mountain.dao.mysql;

import java.util.List;
import com.site.mountain.entity.SysRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SysRoleDao {
    int insert(@Param("pojo") SysRole pojo);
    int insertSelective(List<SysRole> pojo);
    List<SysRole> findList(SysRole pojo);
    int delete(SysRole pojo);

}