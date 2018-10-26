package com.site.mountain.entity;
import java.io.Serializable;
import java.math.BigInteger;
import java.lang.String;
import java.lang.Integer;
import java.util.List;


public class SysMenu implements Serializable {
    private BigInteger menuId;
    private BigInteger parentId;
    private String name;
    private String url;
    private String perms;
    private Integer type;
    private String icon;
    private Integer orderNum;

    private List<SysRole> roles;

    public List<SysRole> getRoles() {
        return roles;
    }

    public void setRoles(List<SysRole> roles) {
        this.roles = roles;
    }

    public BigInteger getMenuId() {
        return menuId;
    }
    public BigInteger getParentId() {
        return parentId;
    }
    public String getName() {
        return name;
    }
    public String getUrl() {
        return url;
    }
    public String getPerms() {
        return perms;
    }
    public Integer getType() {
        return type;
    }
    public String getIcon() {
        return icon;
    }
    public Integer getOrderNum() {
        return orderNum;
    }

    public void setMenuId(BigInteger menuId) {
        this.menuId = menuId;
    }
    public void setParentId(BigInteger parentId) {
        this.parentId = parentId;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public void setPerms(String perms) {
        this.perms = perms;
    }
    public void setType(Integer type) {
        this.type = type;
    }
    public void setIcon(String icon) {
        this.icon = icon;
    }
    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

}
