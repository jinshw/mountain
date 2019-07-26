package com.site.mountain.entity;

import java.math.BigInteger;
import java.sql.Timestamp;

/**
 * 字典表
 */
public class SysDictionary {
    private BigInteger dictId;
    private String dictName;
    private String dictGroupName;
    private String dictValue;
    private int orders;
    private String remark;
    private Timestamp createTime;

    public BigInteger getDictId() {
        return dictId;
    }

    public void setDictId(BigInteger dictId) {
        this.dictId = dictId;
    }

    public String getDictName() {
        return dictName;
    }

    public void setDictName(String dictName) {
        this.dictName = dictName;
    }

    public String getDictGroupName() {
        return dictGroupName;
    }

    public void setDictGroupName(String dictGroupName) {
        this.dictGroupName = dictGroupName;
    }

    public String getDictValue() {
        return dictValue;
    }

    public void setDictValue(String dictValue) {
        this.dictValue = dictValue;
    }

    public int getOrders() {
        return orders;
    }

    public void setOrders(int orders) {
        this.orders = orders;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }
}
