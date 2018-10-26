package com.site.mountain.service.impl;

import com.site.mountain.entity.TreeNode;
import com.site.mountain.service.SysPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import com.site.mountain.entity.SysPermission;
import com.site.mountain.dao.test1.SysPermissionDao;

@Service
public class SysPermissionServiceImpl implements SysPermissionService {

    @Autowired
    private SysPermissionDao sysPermissionDao;

    public int insert(SysPermission pojo){
        return sysPermissionDao.insert(pojo);
    }

    public int insertSelective(SysPermission pojo){
        return sysPermissionDao.insertSelective(pojo);
    }

    public int insertList(List<SysPermission> pojos){
        return sysPermissionDao.insertList(pojos);
    }

    public int update(SysPermission pojo){
        return sysPermissionDao.update(pojo);
    }

    public List<SysPermission> findList(SysPermission sysPermission){
        return sysPermissionDao.findList(sysPermission);
    }

    public int delete(SysPermission sysPermission){
        return sysPermissionDao.delete(sysPermission);
    }

    public SysPermission getMenuTree(Integer id){
        SysPermission tree = new SysPermission();
        tree = sysPermissionDao.selectByid(id);
        SysPermission spn = new SysPermission();
        spn.setParentId(Long.valueOf(id));
        List<SysPermission> children = sysPermissionDao.selectByPid(spn);
        for(SysPermission p:children){
            SysPermission s = getMenuTree(p.getId());
            tree.getChildren().add(s);
        }
        return tree;
    }



}
