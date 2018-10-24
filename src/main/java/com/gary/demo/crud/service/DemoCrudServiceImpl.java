package com.gary.demo.crud.service;

import com.gary.demo.crud.api.exception.DataNotFoundException;
import com.gary.demo.crud.api.exception.ValidationException;
import com.gary.demo.crud.dao.PersonDao;
import com.gary.demo.crud.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Service
public class DemoCrudServiceImpl implements DemoCrudService{

    @Autowired
    PersonDao personDao;

    @Override
    public ResponseEntity<Person> getPerson(String id) {
        if(StringUtils.isEmpty(id)){
            throw new ValidationException("Missing ID");
        }
        Optional<com.gary.demo.crud.entity.Person> optionalPersonEntity = personDao.findById(Long.parseLong(id));
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
        if(StringUtils.isEmpty(person.getFirstName()) || StringUtils.isEmpty(person.getLastName())){
            throw new ValidationException("Missing First Name or Last Name");
        }

        com.gary.demo.crud.entity.Person personEntity = personDao.save(convertToPersonEntity(person));
        return ResponseEntity.status(HttpStatus.CREATED).body(convertToPersonDto(personEntity));

    }

    @Override
    public ResponseEntity<Person> updatePerson(Person person) {
        if(StringUtils.isEmpty(person.getId())){
            throw new ValidationException("Missing ID");
        }
        if(StringUtils.isEmpty(person.getFirstName()) && StringUtils.isEmpty(person.getLastName())){
            throw new ValidationException("Must at least have either First Name or Last Name");
        }

        com.gary.demo.crud.entity.Person personEntity = personDao.save(convertToPersonEntity(person));
        return ResponseEntity.ok(convertToPersonDto(personEntity));
    }

    @Override
    public ResponseEntity<Person> deletePerson(String id) {
        if(StringUtils.isEmpty(id)){
            throw new ValidationException("Missing ID");
        }
        personDao.deleteById(Long.parseLong(id));
        return ResponseEntity.ok(null);
    }

    private com.gary.demo.crud.model.Person convertToPersonDto(com.gary.demo.crud.entity.Person personEntity){
        return new com.gary.demo.crud.model.Person(personEntity.getFirstName(),
                personEntity.getLastName(), personEntity.getId().toString());
    }

    private com.gary.demo.crud.entity.Person convertToPersonEntity(com.gary.demo.crud.model.Person personDto){
        return new com.gary.demo.crud.entity.Person(personDto.getId(), personDto.getFirstName(),
                personDto.getLastName());
    }
}
