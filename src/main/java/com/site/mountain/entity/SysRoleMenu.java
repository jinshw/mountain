package com.site.mountain.entity;
import java.io.Serializable;
import java.math.BigInteger;


public class SysRoleMenu implements Serializable {
    private BigInteger menuId;
    private BigInteger roleId;
    private BigInteger id;

    public BigInteger getMenuId() {
        return menuId;
    }
    public BigInteger getRoleId() {
        return roleId;
    }
    public BigInteger getId() {
        return id;
    }

    public void setMenuId(BigInteger menuId) {
        this.menuId = menuId;
    }
    public void setRoleId(BigInteger roleId) {
        this.roleId = roleId;
    }
    public void setId(BigInteger id) {
        this.id = id;
    }

}
