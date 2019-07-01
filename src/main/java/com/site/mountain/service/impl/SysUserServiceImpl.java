package com.site.mountain.service.impl;

import com.site.mountain.dao.mysql.SysUserDao;
import com.site.mountain.entity.SysUser;
import com.site.mountain.service.SysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SysUserServiceImpl implements SysUserService {
    private final static Logger logger = LoggerFactory.getLogger(SysUserServiceImpl.class);

    @Autowired
    private SysUserDao sysUserDao;

    @Transactional(value = "mysqlTransactionManager", rollbackFor = Exception.class, timeout = 36000)
    public int insert(SysUser pojo) {
        int flag = 0;
        flag = sysUserDao.insert(pojo);
        int ss = 1 / 0;
        if (pojo.getRoles().length > 0) {
            flag = sysUserDao.insertUserAndRole(pojo);
        }
        return flag;
    }

    public int insertSelective(SysUser pojo) {
        return sysUserDao.insertSelective(pojo);
    }

    public List findList(SysUser SysUser) {
        return sysUserDao.findList(SysUser);
    }

    public List<SysUser> selectAllUserAndRoles(SysUser SysUser) {
        return sysUserDao.selectAllUserAndRoles(SysUser);
    }

    public int delete(SysUser SysUser) {
        return sysUserDao.delete(SysUser);
    }

    public int update(SysUser sysUser) {
        return sysUserDao.update(sysUser);
    }
}
