package com.site.mountain.dao.test2;

import com.site.mountain.entity.MQIBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MQI2BeanDao {
    int insert(@Param("pojo") MQIBean pojo);

    int insertSelective(@Param("pojo") MQIBean pojo);

    int insertList(@Param("pojos") List<MQIBean> pojo);

    int update(@Param("pojo") MQIBean pojo);

    List<MQIBean> findAll();


}
