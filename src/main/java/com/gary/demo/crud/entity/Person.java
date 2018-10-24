package com.gary.demo.crud.entity;

import com.google.common.base.MoreObjects;
import org.springframework.util.StringUtils;

import javax.persistence.*;

@Entity
@Table(name = "PERSON")
public class Person {

    @Id
    @Column(name = "ID", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PERSON_ID_SEQ")
    @SequenceGenerator(sequenceName = "PERSON_ID_SEQ", initialValue = 1, allocationSize = 1, name = "PERSON_ID_SEQ")
    private Long id;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    public Person(Long id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }
    public Person(String id, String firstName, String lastName) {
        if(!StringUtils.isEmpty(id)){
            this.id = Long.parseLong(id);
        }
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Person(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("firstName", firstName)
                .add("lastName", lastName)
                .toString();
    }
}
