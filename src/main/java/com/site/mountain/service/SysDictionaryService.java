package com.site.mountain.service;

import com.site.mountain.entity.SysDictionary;

import java.util.List;

public interface SysDictionaryService {
    List<SysDictionary> findList(SysDictionary sysDictionary);

    public int insert(SysDictionary sysDictionary);

    public int update(SysDictionary sysDictionary);

    public int delete(SysDictionary sysDictionary);
}
