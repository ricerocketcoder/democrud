package com.gary.demo.crud.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gary.demo.crud.app.Application;
import com.gary.demo.crud.model.Person;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DemoCrudControllerTest {

    @Autowired
    protected MockMvc mockMvc;


    @Autowired
    DemoCrudController demoCrudController;


    @Test
    public void contextLoadingTest(){
        assertNotNull(demoCrudController);
    }
    @Test
    public void testDemoCrudController1() throws Exception {
        MvcResult result =this.mockMvc.perform(get("/gary/demo/crud/v1/person?id=1")).andDo(print()).andExpect(status().is4xxClientError()).andReturn();
        String resultString = result.getResponse().getContentAsString();
        System.out.println("Gary Demo: " + resultString);

    }

    @Test
    public void testDemoCrudController2() throws Exception {
        Person person = new Person("Aaron", "Wang", null);
        ObjectMapper mapper = new ObjectMapper();
        String json = "";

        json = mapper.writeValueAsString(person);
        MvcResult result =this.mockMvc.perform(post("/gary/demo/crud/v1/person")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().is2xxSuccessful()).andReturn();
        String resultString = result.getResponse().getContentAsString();
        System.out.println("Gary Demo: " + resultString);

    }
}
