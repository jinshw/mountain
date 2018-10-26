package com.site.mountain.entity;
import java.io.Serializable;
import java.math.BigInteger;


public class SysRoleDept implements Serializable {
    private BigInteger id;
    private BigInteger deptId;
    private BigInteger roleId;

    public BigInteger getId() {
        return id;
    }
    public BigInteger getDeptId() {
        return deptId;
    }
    public BigInteger getRoleId() {
        return roleId;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }
    public void setDeptId(BigInteger deptId) {
        this.deptId = deptId;
    }
    public void setRoleId(BigInteger roleId) {
        this.roleId = roleId;
    }

}
