package com.site.mountain.dao.oracle;


import com.site.mountain.entity.NBaoAttr;

import java.util.List;

public interface NBaoAttrDao {
    List<NBaoAttr> findList(NBaoAttr nBaoAttr);
}
