package com.gary.demo.crud.api;

import com.gary.demo.crud.model.Person;
import com.gary.demo.crud.model.PersonResponse;
import com.gary.demo.crud.service.DemoCrudService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@Api
public class DemoCrudController {

    @Autowired
    DemoCrudService demoCrudService;



    @ApiOperation( value = "Get Person",
            notes = "Get the detail of a person with a given ID",
            nickname = "getPerson",
            authorizations = {@Authorization(value="apiKey")})
    @ApiResponses(value = {
            @ApiResponse(code = HttpServletResponse.SC_OK, message = "Success", response = PersonResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "Missing ID"),
            @ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "Data Not Found"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "Unknown Error")
    })
    @RequestMapping(value = "/gary/demo/crud/v1/person", method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonResponse getPerson(@RequestParam(value="id", required=true) String id, HttpServletResponse response){
        PersonResponse personResponse = demoCrudService.getPerson(id);
        response.setStatus(personResponse.getResponseStatus().value());
        return personResponse;
    }


    @ApiOperation( value = "Create Person",
            notes = "create a person in database",
            nickname = "createPerson",
            authorizations = {@Authorization(value="apiKey")})
    @ApiResponses(value = {
            @ApiResponse(code = HttpServletResponse.SC_CREATED, message = "Success", response = PersonResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "Missing data"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "Unknown Error")
    })
    @RequestMapping(value = "/gary/demo/crud/v1/person", method= RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonResponse createPerson(@RequestBody Person person, HttpServletResponse response){
        PersonResponse personResponse = demoCrudService.createPerson(person);
        response.setStatus(personResponse.getResponseStatus().value());
        return personResponse;
    }

    @ApiOperation( value = "Update Person",
            notes = "update a person in database",
            nickname = "updatePerson",
            authorizations = {@Authorization(value="apiKey")})
    @ApiResponses(value = {
            @ApiResponse(code = HttpServletResponse.SC_OK, message = "Success", response = PersonResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "Missing ID"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "Unknown Error")
    })
    @RequestMapping(value = "/gary/demo/crud/v1/person", method= RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonResponse updatePerson(@RequestBody Person person, HttpServletResponse response){
        PersonResponse personResponse = demoCrudService.updatePerson(person);
        response.setStatus(personResponse.getResponseStatus().value());
        return personResponse;
    }

    @ApiOperation( value = "Delete Person",
            notes = "Delete the person from database",
            nickname = "deletePerson",
            authorizations = {@Authorization(value="apiKey")})
    @ApiResponses(value = {
            @ApiResponse(code = HttpServletResponse.SC_OK, message = "Success", response = PersonResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "Missing ID"),
            @ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "Data Not Found"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "Unknown Error")
    })
    @RequestMapping(value = "/gary/demo/crud/v1/person", method= RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonResponse updatePerson(@RequestParam(value="id", required=true) String id, HttpServletResponse response){
        PersonResponse personResponse = demoCrudService.deletePerson(id);
        response.setStatus(personResponse.getResponseStatus().value());
        return personResponse;
    }
}
