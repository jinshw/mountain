package com.site.mountain.dao.mysql;

import java.util.List;
import com.site.mountain.entity.SysFiles;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface SysFilesDao {

    int delete(SysFiles pojo);

    List<SysFiles> findList(SysFiles pojo);

    int insert(SysFiles pojo);

    int insertSelective(List<SysFiles> pojo);

}