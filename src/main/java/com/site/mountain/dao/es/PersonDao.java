package com.site.mountain.dao.es;

import com.site.mountain.entity.Person;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

@Component
public interface PersonDao extends ElasticsearchRepository<Person,Long> {
}
