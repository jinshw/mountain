package com.site.mountain.dao.mysql;

import java.math.BigInteger;
import java.util.List;

import com.site.mountain.entity.SysDept;
import org.apache.ibatis.annotations.Param;

public interface SysDeptDao {
    int insert(@Param("pojo") SysDept pojo);

    int insertSelective(List<SysDept> pojo);

    List<String> findList(SysDept pojo);

    int delete(SysDept pojo);

    List<SysDept> selectByPid(SysDept sysMenu);

    List<SysDept> selectByRoleId(BigInteger id);

    SysDept selectByid(BigInteger id);

    int update(@Param("pojo") SysDept sysDept);

}