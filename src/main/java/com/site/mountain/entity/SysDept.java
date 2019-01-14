package com.site.mountain.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.lang.String;
import java.lang.Integer;
import java.util.ArrayList;
import java.util.List;


public class SysDept implements Serializable {
    private BigInteger deptId;
    private BigInteger parentId;
    private String name;
    private Integer orderNum;
    private Integer delFlag;
    private String parentName;
    private List<SysDept> children = new ArrayList<>();

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public List<SysDept> getChildren() {
        return children;
    }

    public void setChildren(List<SysDept> children) {
        this.children = children;
    }

    public BigInteger getDeptId() {
        return deptId;
    }

    public BigInteger getParentId() {
        return parentId;
    }

    public String getName() {
        return name;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDeptId(BigInteger deptId) {
        this.deptId = deptId;
    }

    public void setParentId(BigInteger parentId) {
        this.parentId = parentId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

}
