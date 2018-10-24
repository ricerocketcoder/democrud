package com.gary.demo.crud.api;

import com.gary.demo.crud.app.Application;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
        MvcResult result =this.mockMvc.perform(get("/gary/demo/crud/v1/person/1")).andDo(print()).andExpect(status().isOk()).andReturn();
        String resultString = result.getResponse().getContentAsString();
        System.out.println("Gary Demo: " + resultString);

    }

}
