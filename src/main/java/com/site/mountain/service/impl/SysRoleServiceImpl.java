package com.site.mountain.service.impl;

import com.site.mountain.dao.mysql.SysRoleDao;
import com.site.mountain.entity.SysRole;
import com.site.mountain.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private SysRoleDao sysRoleDao;

    @Override
    public int insert(SysRole pojo) {
        int flag = sysRoleDao.insert(pojo);
        if (flag > 0) {
            if (pojo.getMenus().size() > 0) {
                flag = sysRoleDao.insertRoleAndMenu(pojo);
            }
            if (pojo.getDepts().size() > 0) {
                flag = sysRoleDao.insertRoleAndDept(pojo);
            }
        }
        return flag;
    }

    @Override
    public List<SysRole> find(SysRole sysRole) {
        return sysRoleDao.findList(sysRole);
    }

    @Override
    public int delete(SysRole sysRole) {
        int flag = sysRoleDao.delete(sysRole);
//        if (flag > 0) {
//            flag = sysRoleDao.deleteRoleMenu(sysRole);
//            flag = sysRoleDao.deleteRoleDept(sysRole);
//        }
        return flag;
    }

    @Override
    public int edit(SysRole sysRole) {
        int flag = sysRoleDao.update(sysRole);
        if (sysRole.getMenus().size() > 0) {
            sysRoleDao.deleteRoleMenu(sysRole);
            sysRoleDao.insertRoleAndMenu(sysRole);
        }
        if (sysRole.getDepts().size() > 0) {
            sysRoleDao.deleteRoleDept(sysRole);
            sysRoleDao.insertRoleAndDept(sysRole);
        }
        return flag;
    }
}
