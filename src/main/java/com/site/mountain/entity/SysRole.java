package com.site.mountain.entity;
import java.io.Serializable;
import java.math.BigInteger;
import java.lang.String;
import java.sql.Timestamp;


public class SysRole implements Serializable {
    private BigInteger roleId;
    private String roleName;
    private String remark;
    private BigInteger deptId;
    private Timestamp createTime;

    public BigInteger getRoleId() {
        return roleId;
    }
    public String getRoleName() {
        return roleName;
    }
    public String getRemark() {
        return remark;
    }
    public BigInteger getDeptId() {
        return deptId;
    }
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setRoleId(BigInteger roleId) {
        this.roleId = roleId;
    }
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public void setDeptId(BigInteger deptId) {
        this.deptId = deptId;
    }
    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

}
