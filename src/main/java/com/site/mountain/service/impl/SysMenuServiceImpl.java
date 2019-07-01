package com.site.mountain.service.impl;

import com.site.mountain.dao.mysql.SysMenuDao;
import com.site.mountain.dao.test1.SysPermissionDao;
import com.site.mountain.entity.SysMenu;
import com.site.mountain.entity.SysPermission;
import com.site.mountain.service.SysMenuService;
import com.site.mountain.service.SysPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
public class SysMenuServiceImpl implements SysMenuService {

    @Autowired
    private SysMenuDao sysMenuDao;

    public int insert(SysMenu pojo) {
        return sysMenuDao.insert(pojo);
    }

    public int insertSelective(SysMenu pojo) {
        return sysMenuDao.insertSelective(pojo);
    }

    public int insertList(List<SysMenu> pojos) {
//        return sysMenuDao.insertList(pojos);
        return 0;
    }

    public int update(SysMenu pojo) {
        return sysMenuDao.update(pojo);
    }

    public List<SysMenu> findList(SysMenu sysMenu) {
        return sysMenuDao.findList(sysMenu);
    }

    public int delete(SysMenu sysMenu) {
        return sysMenuDao.delete(sysMenu);
    }

    public SysMenu getMenuTree(BigInteger id) {
        SysMenu tree = new SysMenu();
        tree = sysMenuDao.selectByid(id);
        SysMenu spn = new SysMenu();
        spn.setParentId(id);
        List<SysMenu> children = sysMenuDao.selectByPid(spn);
        for (SysMenu menu : children) {
            SysMenu s = getMenuTree(menu.getMenuId());
            tree.getChildren().add(s);
        }
        return tree;
    }

}
