package com.gary.demo.crud.model;

import com.google.common.base.MoreObjects;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.http.HttpStatus;

public class PersonResponse extends DemoCrudResponse {

    @ApiModelProperty(value = "a person object")
    Person person;

    public PersonResponse(HttpStatus status, String responseMessage) {
        super(status, responseMessage);
    }

    public PersonResponse(HttpStatus status) {
        super(status);
    }

    public PersonResponse(HttpStatus status, String responseMessage, Person person) {
        super(status, responseMessage);
        this.person = person;
    }

    public Person getPerson() {
        return person;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("person", person)
                .toString();
    }
}
