package com.gary.demo.crud.service;

import com.gary.demo.crud.api.exception.DataNotFoundException;
import com.gary.demo.crud.dao.PersonDao;
import com.gary.demo.crud.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DemoCrudServiceImpl implements DemoCrudService{

    @Autowired
    PersonDao personDao;

    @Override
    public ResponseEntity<Person> getPerson(String id) {
        Optional<com.gary.demo.crud.entity.Person> optionalPersonEntity = personDao.findById(id);
        if(optionalPersonEntity.isPresent()){
            return ResponseEntity.ok(convertToPersonDto(optionalPersonEntity.get()));

        }
        else{
            //throw 404 not found
            throw new DataNotFoundException("Person with id " + id + " does not exist");
        }
    }

    @Override
    public ResponseEntity<Person> createPerson(Person person) {
        return null;
    }

    @Override
    public ResponseEntity<Person> updatePerson(Person person) {
        return null;
    }

    @Override
    public ResponseEntity<Person> deletePerson(String id) {
        return null;
    }

    private com.gary.demo.crud.model.Person convertToPersonDto(com.gary.demo.crud.entity.Person personEntity){
        return new com.gary.demo.crud.model.Person(personEntity.getFirstName(),
                personEntity.getLastName(), personEntity.getId());
    }

    private com.gary.demo.crud.entity.Person convertToPersonEntity(com.gary.demo.crud.model.Person personDto){
        return new com.gary.demo.crud.entity.Person(personDto.getFirstName(),
                personDto.getLastName(), personDto.getId());
    }
}
