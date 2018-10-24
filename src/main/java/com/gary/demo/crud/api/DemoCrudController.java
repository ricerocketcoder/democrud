package com.gary.demo.crud.api;

import com.gary.demo.crud.model.Person;
import com.gary.demo.crud.service.DemoCrudService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@Api
public class DemoCrudController {

    @Autowired
    DemoCrudService demoCrudService;



    @ApiOperation( value = "Get Person",
            notes = "Get the detail of a person with a given ID",
            nickname = "getPerson")
    @ApiResponses(value = {
            @ApiResponse(code = HttpServletResponse.SC_OK, message = "Success", response = Person.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "Missing ID"),
            @ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "Data Not Found"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "Unknown Error")
    })
    @RequestMapping(value = "/gary/demo/crud/v1/person", method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Person> getPerson(@RequestParam(value="id", required=true) String id){
        return demoCrudService.getPerson(id);
    }


    @ApiOperation( value = "Create Person",
            notes = "create a person in database",
            nickname = "createPerson")
    @ApiResponses(value = {
            @ApiResponse(code = HttpServletResponse.SC_CREATED, message = "Success", response = Person.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "Missing data"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "Unknown Error")
    })
    @RequestMapping(value = "/gary/demo/crud/v1/person", method= RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Person> createPerson(@RequestBody Person person){
        return demoCrudService.createPerson(person);
    }

    @ApiOperation( value = "Update Person",
            notes = "update a person in database",
            nickname = "updatePerson")
    @ApiResponses(value = {
            @ApiResponse(code = HttpServletResponse.SC_OK, message = "Success", response = Person.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "Missing ID"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "Unknown Error")
    })
    @RequestMapping(value = "/gary/demo/crud/v1/person", method= RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Person> updatePerson(@RequestBody Person person){
        return demoCrudService.updatePerson(person);
    }

    @ApiOperation( value = "Delete Person",
            notes = "Delete the person from database",
            nickname = "deletePerson")
    @ApiResponses(value = {
            @ApiResponse(code = HttpServletResponse.SC_OK, message = "Success", response = Person.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "Missing ID"),
            @ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "Data Not Found"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "Unknown Error")
    })
    @RequestMapping(value = "/gary/demo/crud/v1/person", method= RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Person> updatePerson(@RequestParam(value="id", required=true) String id){
        return demoCrudService.deletePerson(id);
    }
}
