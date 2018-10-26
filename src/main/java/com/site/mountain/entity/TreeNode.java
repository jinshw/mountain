package com.site.mountain.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * 菜单树
 */
public class TreeNode {
    public String id;
    public String name;
    public String pid;
    public List<TreeNode> chidren = new ArrayList<TreeNode>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public List<TreeNode> getChidren() {
        return chidren;
    }

    public void setChidren(List<TreeNode> chidren) {
        this.chidren = chidren;
    }
}
