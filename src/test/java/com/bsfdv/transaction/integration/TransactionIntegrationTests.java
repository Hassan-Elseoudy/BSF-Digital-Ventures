package com.bsfdv.transaction.integration;

import com.bsfdv.transaction.TransactionApplicationTests;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TransactionIntegrationTests extends TransactionApplicationTests {

    @BeforeEach
    public void setup() {
        super.setUp();
    }

    @AfterEach
    public void tearDown() {
        super.tearDown();
    }

    @Test
    public void getAllCustomers() throws Exception {
        // Act
        mockMvc.perform(MockMvcRequestBuilders.
                        get("/customer")
                        .contentType(MediaType.APPLICATION_JSON))
                // Assert
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(8)));
    }

    @Test
    public void getAllCustomersTrueState() throws Exception {
        // Act
        mockMvc.perform(MockMvcRequestBuilders.
                        get("/customer")
                        .param("state", String.valueOf(true))
                        .contentType(MediaType.APPLICATION_JSON))
                // Assert
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(4)));
    }

    @Test
    public void getAllCustomersFalseState() throws Exception {
        // Act
        mockMvc.perform(MockMvcRequestBuilders.
                        get("/customer")
                        .param("state", String.valueOf(false))
                        .contentType(MediaType.APPLICATION_JSON))
                // Assert
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(4)));
    }

    @Test
    public void getAllCustomersCameroon() throws Exception {
        // Act
        mockMvc.perform(MockMvcRequestBuilders.
                        get("/customer")
                        .param("countryId", "0")
                        .contentType(MediaType.APPLICATION_JSON))
                // Assert
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(4)));
    }

    @Test
    public void getAllCustomersInCameroonTrueState() throws Exception {
        // Act
        mockMvc.perform(MockMvcRequestBuilders.
                        get("/customer")
                        .param("state", String.valueOf(true))
                        .param("countryId", "0")
                        .contentType(MediaType.APPLICATION_JSON))
                // Assert
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void getAllCustomersInCameroonFalseState() throws Exception {
        // Act
        mockMvc.perform(MockMvcRequestBuilders.
                        get("/customer")
                        .param("state", String.valueOf(false))
                        .param("countryId", "0")
                        .contentType(MediaType.APPLICATION_JSON))
                // Assert
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }


}
