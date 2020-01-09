package com.site.mountain.entity;

import java.math.BigInteger;
import java.lang.String;
import java.lang.Integer;
import java.sql.Timestamp;


public class SysFiles {
    private BigInteger fid;
    private String fname;
    private Integer ftype;
    private String suffixName;
    private Long size;
    private String path;
    private Timestamp createTime;
    private BigInteger createPerson;
    private Integer isDelete;
    private Integer pageNum;
    private Integer pageSize;

    private SysUser sysUser;

    public SysUser getSysUser() {
        return sysUser;
    }

    public void setSysUser(SysUser sysUser) {
        this.sysUser = sysUser;
    }

    public BigInteger getFid() {
        return fid;
    }

    public String getFname() {
        return fname;
    }

    public Integer getFtype() {
        return ftype;
    }

    public String getSuffixName() {
        return suffixName;
    }


    public String getPath() {
        return path;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public BigInteger getCreatePerson() {
        return createPerson;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setFid(BigInteger fid) {
        this.fid = fid;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public void setFtype(Integer ftype) {
        this.ftype = ftype;
    }

    public void setSuffixName(String suffixName) {
        this.suffixName = suffixName;
    }


    public void setPath(String path) {
        this.path = path;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public void setCreatePerson(BigInteger createPerson) {
        this.createPerson = createPerson;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }
}
