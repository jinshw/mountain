package com.site.mountain.service.impl;

import com.site.mountain.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.site.mountain.entity.SysRole;
import com.site.mountain.dao.test1.SysRoleDao;

@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private SysRoleDao sysRoleDao;

    public int insert(SysRole pojo){
        return sysRoleDao.insert(pojo);
    }

    public int insertSelective(SysRole pojo){
        return sysRoleDao.insertSelective(pojo);
    }

    public int insertList(List<SysRole> pojos){
        return sysRoleDao.insertList(pojos);
    }

    public int update(SysRole pojo){
        return sysRoleDao.update(pojo);
    }

    public List<SysRole> findRoleList(SysRole sysRole){
        return sysRoleDao.findRoleList(sysRole);
    }
}
