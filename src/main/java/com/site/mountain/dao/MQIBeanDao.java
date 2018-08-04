package com.site.mountain.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import com.site.mountain.entity.MQIBean;

@Mapper
public interface MQIBeanDao {
    int insert(@Param("pojo") MQIBean pojo);

    int insertSelective(@Param("pojo") MQIBean pojo);

    int insertList(@Param("pojos") List<MQIBean> pojo);

    int update(@Param("pojo") MQIBean pojo);

    List<MQIBean> findAll();


}
