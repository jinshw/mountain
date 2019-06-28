package com.site.mountain.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.lang.String;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


public class SysRole implements Serializable {
    private BigInteger roleId;
    private String roleName;
    private String remark;
    private BigInteger deptId;
    private Timestamp createTime;
    private List<SysMenu> menus = new ArrayList<>();
    private List<SysDept> depts = new ArrayList<>();

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

    public List<SysMenu> getMenus() {
        return menus;
    }

    public void setMenus(List<SysMenu> menus) {
        this.menus = menus;
    }

    public List<SysDept> getDepts() {
        return depts;
    }

    public void setDepts(List<SysDept> depts) {
        this.depts = depts;
    }
}
