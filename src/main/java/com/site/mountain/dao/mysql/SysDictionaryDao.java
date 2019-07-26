package com.site.mountain.dao.mysql;

import com.site.mountain.entity.SysDictionary;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysDictionaryDao {
    List<SysDictionary> findList(SysDictionary sysDictionary);

    int insert(@Param("pojo") SysDictionary pojo);

    int update(@Param("pojo") SysDictionary pojo);

    int delete(SysDictionary sysDictionary);
}