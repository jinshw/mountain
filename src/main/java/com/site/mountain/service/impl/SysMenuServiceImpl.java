package com.site.mountain.service.impl;

import com.site.mountain.dao.mysql.SysMenuDao;
import com.site.mountain.dao.mysql.SysRoleMenuDao;
import com.site.mountain.entity.SysMenu;
import com.site.mountain.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
public class SysMenuServiceImpl implements SysMenuService {

    @Autowired
    private SysMenuDao sysMenuDao;

    @Autowired
    SysRoleMenuDao sysRoleMenuDao;

    @Override
    public int insert(SysMenu pojo) {
        return sysMenuDao.insert(pojo);
    }

    @Override
    public int insertSelective(SysMenu pojo) {
        return sysMenuDao.insertSelective(pojo);
    }

    public int insertList(List<SysMenu> pojos) {
//        return sysMenuDao.insertList(pojos);
        return 0;
    }

    @Override
    public int update(SysMenu pojo) {
        return sysMenuDao.update(pojo);
    }

    @Override
    public List<SysMenu> findList(SysMenu sysMenu) {
        return sysMenuDao.findList(sysMenu);
    }

    @Override
    public int delete(SysMenu sysMenu) {
        return sysMenuDao.delete(sysMenu);
    }

    @Override
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
