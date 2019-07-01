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

    public SysDept getMenuTree(BigInteger id) {
        SysDept tree = new SysDept();
        tree = sysDeptDao.selectByid(id);
        SysDept parentDept = sysDeptDao.selectByid(tree.getParentId());
        if (parentDept != null && !parentDept.getDeptId().toString().equals("-1")) {
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

    public int insert(SysDept sysDept) {
        return sysDeptDao.insert(sysDept);
    }

    public int delete(SysDept sysDept) {
        return sysDeptDao.delete(sysDept);
//        return sysDeptDao.update(sysDept);
    }

    public int update(SysDept sysDept) {
        return sysDeptDao.update(sysDept);
    }
}
