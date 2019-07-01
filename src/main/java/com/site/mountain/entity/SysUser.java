package com.site.mountain.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.lang.String;
import java.lang.Integer;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


public class SysUser implements Serializable {
    private BigInteger userId;
    private String username;
    private String password;
    private String email;
    private String mobile;
    private Integer status;
    private BigInteger deptId;
    private Timestamp createTime;
    private String[] roles = new String[100];

    private List<SysRole> roleList = new ArrayList<SysRole>();
    private List<SysMenu> menuList = new ArrayList<SysMenu>();

    public List<SysRole> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<SysRole> roleList) {
        this.roleList = roleList;
    }

    public List<SysMenu> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<SysMenu> menuList) {
        this.menuList = menuList;
    }

    public BigInteger getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getMobile() {
        return mobile;
    }

    public Integer getStatus() {
        return status;
    }

    public BigInteger getDeptId() {
        return deptId;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setUserId(BigInteger userId) {
        this.userId = userId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setDeptId(BigInteger deptId) {
        this.deptId = deptId;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String[] getRoles() {
        return roles;
    }

    public void setRoles(String[] roles) {
        this.roles = roles;
    }
}
