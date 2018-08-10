package com.site.mountain.dao.test1;

import com.site.mountain.entity.MQIBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MQI1BeanDao {
    int insert(@Param("pojo") MQIBean pojo);

    int insertSelective(@Param("pojo") MQIBean pojo);

    int insertList(@Param("pojos") List<MQIBean> pojo);

    int update(@Param("pojo") MQIBean pojo);

    List<MQIBean> findAll();


}
