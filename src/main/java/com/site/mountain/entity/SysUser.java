package com.site.mountain.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


public class SysUser implements Serializable {
    private BigInteger userId;
    private String username;
    private String nickname;
    private String password;
    private String email;
    private String mobile;
    private Integer status;
    private BigInteger deptId;
    private Timestamp createTime;
    private Integer[] roles = new Integer[100];
    private Integer[] depts = new Integer[1000];
    private List<BigInteger> authUsers = new ArrayList<>();

    private List<SysRole> roleList = new ArrayList<SysRole>();
    private List<SysMenu> menuList = new ArrayList<SysMenu>();
    private List<SysDept> deptList = new ArrayList<SysDept>();

    // 扩展字段
    private Integer pageNum;
    private Integer pageSize;
    private SysUser sysUser;
    private SysDept sysDept;

    public SysDept getSysDept() {
        return sysDept;
    }

    public void setSysDept(SysDept sysDept) {
        this.sysDept = sysDept;
    }

    public List<BigInteger> getAuthUsers() {
        return authUsers;
    }

    public void setAuthUsers(List<BigInteger> authUsers) {
        this.authUsers = authUsers;
    }

    public Integer[] getDepts() {
        return depts;
    }

    public void setDepts(Integer[] depts) {
        this.depts = depts;
    }

    public List<SysDept> getDeptList() {
        return deptList;
    }

    public void setDeptList(List<SysDept> deptList) {
        this.deptList = deptList;
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

    public SysUser getSysUser() {
        return sysUser;
    }

    public void setSysUser(SysUser sysUser) {
        this.sysUser = sysUser;
    }

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

    public Integer[] getRoles() {
        return roles;
    }

    public void setRoles(Integer[] roles) {
        this.roles = roles;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
