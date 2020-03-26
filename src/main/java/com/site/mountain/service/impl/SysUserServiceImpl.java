package com.site.mountain.service.impl;

import com.site.mountain.dao.mysql.SysMenuDao;
import com.site.mountain.dao.mysql.SysRoleMenuDao;
import com.site.mountain.dao.mysql.SysUserDao;
import com.site.mountain.dao.mysql.SysUserRoleDao;
import com.site.mountain.entity.SysMenu;
import com.site.mountain.entity.SysRoleMenu;
import com.site.mountain.entity.SysUser;
import com.site.mountain.entity.SysUserRole;
import com.site.mountain.service.SysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
public class SysUserServiceImpl implements SysUserService {
    private final static Logger logger = LoggerFactory.getLogger(SysUserServiceImpl.class);

    @Autowired
    private SysUserDao sysUserDao;
    @Autowired
    private SysUserRoleDao sysUserRoleDao;
    @Autowired
    private SysMenuDao sysMenuDao;
    @Autowired
    private SysRoleMenuDao sysRoleMenuDao;

    @Override
    @Transactional(value = "mysqlTransactionManager", rollbackFor = Exception.class, timeout = 36000)
    public int insert(SysUser pojo) {
        int flag = 0;
        flag = sysUserDao.insert(pojo);
        if (pojo.getRoles().length > 0) {
            flag = sysUserDao.insertUserAndRole(pojo);
        }
        return flag;
    }

    @Override
    public int insertSelective(SysUser pojo) {
        return sysUserDao.insertSelective(pojo);
    }

    @Override
    public List findList(SysUser SysUser) {
        return sysUserDao.findList(SysUser);
    }

    @Override
    public List<SysUser> selectAllUserAndRoles(SysUser sysUser) {
        return sysUserDao.selectAllUserAndRoles(sysUser);
    }

    @Override
    public SysMenu getMenuTree(SysUser sysUser) {
//        SysMenu sysMenu = null;
        List<String> selectedMenu = new ArrayList<String>();
        List<SysUser> list = sysUserDao.selectAllUserAndRoles(sysUser);
        SysUserRole sysUserRole = new SysUserRole();
        for (SysMenu temp : list.get(0).getMenuList()) {
            selectedMenu.add(temp.getMenuId().toString());
        }


//        sysUserRole.setUserId(list.get(0).getUserId());
//        List<SysUserRole> sysUserRoleList = sysUserRoleDao.findList(sysUserRole);
//        BigInteger bigInteger = new BigInteger("-1");
//        for (SysUserRole sysUserRoleTemp : sysUserRoleList) {
////            sysMenu = getMenuTreeByRoleId(bigInteger, sysUserRoleTemp.getRoleId());
//            SysRoleMenu sysRoleMenu = new SysRoleMenu();
//            sysRoleMenu.setRoleId(sysUserRoleTemp.getRoleId());
//        }
        BigInteger bigInteger = new BigInteger("-1");
        SysMenu sysMenu = getMenuTreeByRoleId(bigInteger, selectedMenu);
        return sysMenu;
    }

    public SysMenu getMenuTreeByRoleId(BigInteger menuId, List selectedMenu) {
        SysMenu tree = new SysMenu();
        tree = sysMenuDao.selectByid(menuId);
        SysMenu spn = new SysMenu();
        spn.setParentId(menuId);
        List<SysMenu> children = sysMenuDao.selectByPid(spn);
        for (SysMenu menu : children) {
            SysMenu s = getMenuTreeByRoleId(menu.getMenuId(), selectedMenu);

//            SysRoleMenu sysRoleMenu = new SysRoleMenu();
//            sysRoleMenu.setMenuId(menu.getMenuId());
//            sysRoleMenu.setRoleId(roleId);
//            int count = sysRoleMenuDao.findCount(sysRoleMenu);
            if (selectedMenu.contains(menu.getMenuId().toString())) {
                tree.getChildren().add(s);
            } else {
                if (s.getChildren().size() > 0) {
                    tree.getChildren().add(s);
                }
            }
        }
//        if (children.size() == 0) {
//            SysRoleMenu sysRoleMenu = new SysRoleMenu();
//            sysRoleMenu.setMenuId(menuId);
//            sysRoleMenu.setRoleId(roleId);
//            int count = sysRoleMenuDao.findCount(sysRoleMenu);
//            if (count == 0) {
//                tree.getChildren().remove(tree.getChildren().size() - 1);
//            }
//        }
        return tree;
    }

    @Override
    public int delete(SysUser SysUser) {
        return sysUserDao.delete(SysUser);
    }

    @Override
    public int update(SysUser sysUser) {
        int flag = sysUserDao.update(sysUser);
        flag = sysUserDao.deleteUserAndRole(sysUser);
        flag = sysUserDao.insertUserAndRole(sysUser);
        return flag;
    }

    @Override
    public int updatePassword(SysUser sysUser) {
        return sysUserDao.updatePassword(sysUser);
    }
}
