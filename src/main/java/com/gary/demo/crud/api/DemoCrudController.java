package com.gary.demo.crud.api;

import com.gary.demo.crud.model.Person;
import com.gary.demo.crud.service.DemoCrudService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController

public class DemoCrudController {

    @Autowired
    DemoCrudService demoCrudService;



    @RequestMapping(value = "/gary/demo/crud/v1/person/{id}", method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Person> getPerson(@PathVariable("id") String id){
        return demoCrudService.getPerson(id);
    }

}
