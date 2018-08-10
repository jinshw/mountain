package com.site.mountain.service.impl;

import com.site.mountain.service.SysPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
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
}
