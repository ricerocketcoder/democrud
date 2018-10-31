package com.gary.demo.crud.service;

import com.gary.demo.crud.model.Person;
import com.gary.demo.crud.model.PersonResponse;

public interface DemoCrudService {

    public PersonResponse getPerson(String id);
    public PersonResponse createPerson(Person person);
    public PersonResponse updatePerson(Person person);
    public PersonResponse deletePerson(String id);

}
