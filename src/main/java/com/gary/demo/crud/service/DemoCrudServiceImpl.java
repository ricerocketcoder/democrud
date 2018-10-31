package com.gary.demo.crud.service;

import com.gary.demo.crud.api.exception.DataNotFoundException;
import com.gary.demo.crud.api.exception.ValidationException;
import com.gary.demo.crud.dao.PersonDao;
import com.gary.demo.crud.model.Person;
import com.gary.demo.crud.model.PersonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class DemoCrudServiceImpl implements DemoCrudService{

    @Autowired
    PersonDao personDao;

    @Override
    public PersonResponse getPerson(String id) {
        if(StringUtils.isEmpty(id)){
            throw new ValidationException("Missing ID");
        }
        Optional<com.gary.demo.crud.entity.Person> optionalPersonEntity = personDao.findById(Long.parseLong(id));
        if(optionalPersonEntity.isPresent()){
            return new PersonResponse(HttpStatus.OK, "Successful", convertToPersonDto(optionalPersonEntity.get()));

        }
        else{
            //throw 404 not found
            throw new DataNotFoundException("Person with id " + id + " does not exist");
        }
    }

    @Override
    public PersonResponse createPerson(Person person) {
        if(StringUtils.isEmpty(person.getFirstName()) || StringUtils.isEmpty(person.getLastName())){
            throw new ValidationException("Missing First Name or Last Name");
        }

        com.gary.demo.crud.entity.Person personEntity = personDao.save(convertToPersonEntity(person));
        return new PersonResponse(HttpStatus.CREATED, "Created", convertToPersonDto(personEntity));
    }

    @Override
    @Transactional
    public PersonResponse updatePerson(Person person) {
        if(StringUtils.isEmpty(person.getId())){
            throw new ValidationException("Missing ID");
        }
        if(StringUtils.isEmpty(person.getFirstName()) && StringUtils.isEmpty(person.getLastName())){
            throw new ValidationException("Must at least have either First Name or Last Name");
        }

        Optional<com.gary.demo.crud.entity.Person> optionalPersonEntity = personDao.findById(Long.parseLong(person.getId()));
        if(!optionalPersonEntity.isPresent()){
            //throw 404 not found
            throw new DataNotFoundException("Person with id " + person.getId() + " does not exist");
        }
        com.gary.demo.crud.entity.Person personEntity = optionalPersonEntity.get();
        personEntity.setFirstName(person.getFirstName());
        personEntity.setLastName(person.getLastName());
        personEntity = personDao.save(personEntity);
        return new PersonResponse(HttpStatus.OK, "Updated", convertToPersonDto(personEntity));
    }

    @Override
    @Transactional
    public PersonResponse deletePerson(String id) {
        if(StringUtils.isEmpty(id)){
            throw new ValidationException("Missing ID");
        }
        Optional<com.gary.demo.crud.entity.Person> optionalPersonEntity = personDao.findById(Long.parseLong(id));
        if(!optionalPersonEntity.isPresent()){
            //throw 404 not found
            throw new DataNotFoundException("Person with id " + id + " does not exist");
        }
        personDao.deleteById(Long.parseLong(id));
        return new PersonResponse(HttpStatus.OK, "Deleted");
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
