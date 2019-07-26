package com.site.mountain.service.impl;

import com.site.mountain.dao.mysql.SysDictionaryDao;
import com.site.mountain.entity.SysDictionary;
import com.site.mountain.service.SysDictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysDictionaryServiceImpl implements SysDictionaryService {

    @Autowired
    public SysDictionaryDao sysDictionaryDao;

    public List<SysDictionary> findList(SysDictionary sysDictionary) {
        return sysDictionaryDao.findList(sysDictionary);
    }

    public int insert(SysDictionary sysDictionary){
        return sysDictionaryDao.insert(sysDictionary);
    }

    public int update(SysDictionary sysDictionary){
        return sysDictionaryDao.update(sysDictionary);
    }

    public int delete(SysDictionary sysDictionary){
        return sysDictionaryDao.delete(sysDictionary);
    }
}
