package com.site.mountain.service.impl;

import com.site.mountain.dao.mysql.SysRoleDao;
import com.site.mountain.entity.SysRole;
import com.site.mountain.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;

@Service
@Transactional
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private SysRoleDao sysRoleDao;

    public int insert(SysRole pojo) {
        int flag = sysRoleDao.insert(pojo);
        if (flag > 0) {
            flag = sysRoleDao.insertRoleAndMenu(pojo);
            flag = sysRoleDao.insertRoleAndDept(pojo);
        }
        return flag;
    }

    public List<SysRole> find(SysRole sysRole) {
        return sysRoleDao.findList(sysRole);
    }

    public int delete(SysRole sysRole) {
        int flag = sysRoleDao.delete(sysRole);
        if (flag > 0) {
            flag = sysRoleDao.deleteRoleMenu(sysRole);
            flag = sysRoleDao.deleteRoleDept(sysRole);
        }
        return flag;
    }
}
