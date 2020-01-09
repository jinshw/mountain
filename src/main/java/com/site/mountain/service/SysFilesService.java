package com.site.mountain.service;

import com.github.pagehelper.PageInfo;
import com.site.mountain.entity.SysFiles;

import java.util.List;

public interface SysFilesService {
    List<SysFiles> findList(SysFiles sysFiles);

    public PageInfo<SysFiles> getFileList(SysFiles sysFiles);

    public int insert(SysFiles sysFiles);

    public int delete(SysFiles sysFiles);
}
