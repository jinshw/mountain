package com.site.mountain.entity;
import java.io.Serializable;
import java.math.BigInteger;


public class SysUserRole implements Serializable {
    private BigInteger id;
    private BigInteger userId;
    private BigInteger roleId;

    public BigInteger getId() {
        return id;
    }
    public BigInteger getUserId() {
        return userId;
    }
    public BigInteger getRoleId() {
        return roleId;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }
    public void setUserId(BigInteger userId) {
        this.userId = userId;
    }
    public void setRoleId(BigInteger roleId) {
        this.roleId = roleId;
    }

}
