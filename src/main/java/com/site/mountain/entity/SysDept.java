package com.site.mountain.entity;
import java.io.Serializable;
import java.math.BigInteger;
import java.lang.String;
import java.lang.Integer;


public class SysDept implements Serializable {
    private BigInteger deptId;
    private BigInteger parentId;
    private String name;
    private Integer orderNum;
    private Integer delFlag;

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
