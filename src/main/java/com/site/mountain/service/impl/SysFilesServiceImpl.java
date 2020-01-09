package com.site.mountain.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.site.mountain.dao.mysql.SysFilesDao;
import com.site.mountain.entity.SysFiles;
import com.site.mountain.service.SysFilesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysFilesServiceImpl implements SysFilesService {

    @Autowired
    SysFilesDao sysFilesDao;

    @Override
    public List<SysFiles> findList(SysFiles sysFiles) {
        return sysFilesDao.findList(sysFiles);
    }

    public PageInfo<SysFiles> getFileList(SysFiles sysFiles) {
        PageHelper.startPage(sysFiles.getPageNum(), sysFiles.getPageSize());
        List<SysFiles> list = sysFilesDao.findList(sysFiles);
        PageInfo<SysFiles> pageInfo = new PageInfo<SysFiles>(list,sysFiles.getPageSize());
        return pageInfo;
    }

    public int insert(SysFiles sysFiles){
        return sysFilesDao.insert(sysFiles);
    }

    public int delete(SysFiles sysFiles){
        return sysFilesDao.delete(sysFiles);
    }
}
