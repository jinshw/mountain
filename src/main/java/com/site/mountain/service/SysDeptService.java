package com.site.mountain.service;

import com.site.mountain.entity.SysDept;

import java.math.BigInteger;

public interface SysDeptService {

    public SysDept getMenuTree(BigInteger id);

    public int insert(SysDept sysDept);

    public int delete(SysDept sysDept);
}
