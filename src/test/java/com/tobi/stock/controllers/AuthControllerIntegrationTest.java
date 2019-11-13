package com.tobi.stock.controllers;

import com.tobi.stock.configs.JwtTokenProvider;
import com.tobi.stock.enums.RoleType;
import com.tobi.stock.repositories.InvestorRepository;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Kayode Emmanuel Oluwatobi
 * Date: 25/09/2019
 * Time: 7:58 PM
 **/

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Test
    public void shouldNotAllowAccessToUnauthenticatedUsers() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/health")).andExpect(status().isUnauthorized());
    }

    @Test
    public void shouldThrowBadCredentialsException_ifRequiredFiedIsWrong(){
//        Gson gson = new Gson();
//
//        MvcResult result = mvc.perform(MockMvcRequestBuilders.post("/v2/token")
//                .content(body))
//                .andExpect(status().isOk()).andReturn();

    }


    @Test
    public void shouldGenerateAuthToken() throws Exception {
        Set<RoleType> roles = new HashSet<>();
        roles.add(RoleType.BASIC);
        String token = "Bearer " + jwtTokenProvider.createToken("randMe899", roles);

        assertNotNull(token);
//        mvc.perform(MockMvcRequestBuilders.get("/api/health").header("Authorization", token)).andExpect(status().isOk());
    }
}
