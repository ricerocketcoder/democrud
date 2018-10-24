package com.gary.demo.crud.dao;

import com.gary.demo.crud.entity.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonDao extends CrudRepository<Person, String> {
}
