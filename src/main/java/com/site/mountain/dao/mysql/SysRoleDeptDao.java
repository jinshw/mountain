package com.site.mountain.dao.mysql;

import java.util.List;
import com.site.mountain.entity.SysRoleDept;

public interface SysRoleDeptDao {
    int insert(SysRoleDept pojo);
    int insertSelective(List<SysRoleDept> pojo);
    List<String> findList(SysRoleDept pojo);
    int delete(SysRoleDept pojo);

}