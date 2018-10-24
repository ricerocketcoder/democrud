package com.gary.demo.crud.service;

import com.gary.demo.crud.model.Person;
import org.springframework.http.ResponseEntity;

public interface DemoCrudService {

    public ResponseEntity<Person> getPerson(String id);
    public ResponseEntity<Person> createPerson(Person person);
    public ResponseEntity<Person> updatePerson(Person person);
    public ResponseEntity<Person> deletePerson(String id);

}
