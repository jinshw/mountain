package com.site.mountain.service.impl;

import com.site.mountain.dao.es.PersonDao;
import com.site.mountain.entity.Person;
import com.site.mountain.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    PersonDao personDao;

    @Override
    public boolean insert(Person person) {
        boolean flag = false;
        try {
            personDao.save(person);
            flag = true;
        } catch (Exception e) {
            flag = false;
            e.printStackTrace();
        }
        return flag;
    }
}
