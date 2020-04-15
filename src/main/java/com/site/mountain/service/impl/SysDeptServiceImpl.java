package com.site.mountain.service.impl;

import com.site.mountain.dao.mysql.SysDeptDao;
import com.site.mountain.entity.SysDept;
import com.site.mountain.service.SysDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
public class SysDeptServiceImpl implements SysDeptService {

    @Autowired
    public SysDeptDao sysDeptDao;

    @Override
    public SysDept getMenuTree(BigInteger id) {
        SysDept tree = new SysDept();
        tree = sysDeptDao.selectByid(id);
        SysDept parentDept = sysDeptDao.selectByid(tree.getParentId());
        if (parentDept != null && !"-1".equals(parentDept.getDeptId().toString())) {
            tree.setParentName(parentDept.getName());
        }
        SysDept sysDept = new SysDept();
        sysDept.setParentId(id);
        List<SysDept> children = sysDeptDao.selectByPid(sysDept);
        for (SysDept sd : children) {
            SysDept s = getMenuTree(sd.getDeptId());
            tree.getChildren().add(s);
        }
        return tree;
    }

    @Override
    public int insert(SysDept sysDept) {
        return sysDeptDao.insert(sysDept);
    }

    @Override
    public int delete(SysDept sysDept) {
        return sysDeptDao.delete(sysDept);
//        return sysDeptDao.update(sysDept);
    }

    @Override
    public int update(SysDept sysDept) {
        return sysDeptDao.update(sysDept);
    }
}
