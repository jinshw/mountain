package com.site.mountain.service.impl;

import com.site.mountain.dao.mysql.SysUserDao;
import com.site.mountain.entity.SysUser;
import com.site.mountain.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserDao sysUserDao;

    public int insert(SysUser pojo) {
        return sysUserDao.insert(pojo);
    }

    public int insertSelective(SysUser pojo) {
        return sysUserDao.insertSelective(pojo);
    }

    public List findList(SysUser SysUser) {
        return sysUserDao.findList(SysUser);
    }

    public List<SysUser> selectAllUserAndRoles(SysUser SysUser){
        return sysUserDao.selectAllUserAndRoles(SysUser);
    }

    public int delete(SysUser SysUser){
        return sysUserDao.delete(SysUser);
    }

    public int update(SysUser sysUser){
        return  sysUserDao.update(sysUser);
    }
}
