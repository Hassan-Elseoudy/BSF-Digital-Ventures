package com.bsfdv.transaction.integration;

import com.bsfdv.transaction.TransactionApplicationTests;
import com.bsfdv.transaction.controller.dto.SignupDto;
import com.bsfdv.transaction.controller.dto.UpdateAccountDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.bsfdv.transaction.data.MockData.accountsData;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AccountIntegrationTests extends TransactionApplicationTests {

    @Autowired
    public ObjectMapper mapper;

    @BeforeEach
    public void setup() {
        super.setUp();
    }

    @Test
    public void getOneValidAccount() throws Exception {
        // Act
        mockMvc.perform(MockMvcRequestBuilders.get("/accounts/1")
                        .contentType(MediaType.APPLICATION_JSON))
                // Assert
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(accountsData.get(0).getId().intValue())))
                .andExpect(jsonPath("$.name", is(accountsData.get(0).getName())))
                .andExpect(jsonPath("$.country", is(accountsData.get(0).getCountry())))
                .andExpect(jsonPath("$.balance", is(accountsData.get(0).getBalance().intValue())));
    }

    @Test
    public void getNonValidAccount() throws Exception {
        // Act
        mockMvc.perform(MockMvcRequestBuilders.get("/accounts/-1")
                        .contentType(MediaType.APPLICATION_JSON))
                // Assert
                .andDo(print())
                .andExpect(jsonPath("$.status", is("NOT_FOUND")))
                .andExpect(status().isNotFound());

    }

    @Test
    public void createValidAccount() throws Exception {
        SignupDto accountDto = new SignupDto("Hassan", "UK");
        // Act
        mockMvc.perform(MockMvcRequestBuilders.post("/accounts")
                        .content(this.mapper.writeValueAsString(accountDto))
                        .contentType(MediaType.APPLICATION_JSON))
                // Assert
                .andDo(print())
                .andExpect(jsonPath("$.name", is(accountDto.getName())))
                .andExpect(jsonPath("$.country", is(accountDto.getCountry())))
                .andExpect(jsonPath("$.balance", is(0)))
                .andExpect(status().isCreated());
    }

    @Test
    public void createNotValidAccountMissingNameAndCountry() throws Exception {
        SignupDto accountDto = new SignupDto("", "");
        // Act
        mockMvc.perform(MockMvcRequestBuilders.post("/accounts")
                        .content(this.mapper.writeValueAsString(accountDto))
                        .contentType(MediaType.APPLICATION_JSON))
                // Assert
                .andDo(print())
                .andExpect(jsonPath("$.subErrors", hasSize(2)))
                .andExpect(jsonPath("$.subErrors[*].field", hasItems("country", "name")))
                .andExpect(jsonPath("$.subErrors[*].message", hasItems("must not be blank", "must not be blank")))
                .andExpect(jsonPath("$.subErrors[*].rejectedValue", hasItems(accountDto.getName(), accountDto.getCountry())))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void updateValidAccountWithValidParameters() throws Exception {
        UpdateAccountDto accountDto = new UpdateAccountDto("Semsem", "UK");
        // Act
        mockMvc.perform(MockMvcRequestBuilders.put("/accounts/1")
                        .content(this.mapper.writeValueAsString(accountDto))
                        .contentType(MediaType.APPLICATION_JSON))
                // Assert
                .andDo(print())
                .andExpect(jsonPath("$.name", is(accountDto.getName())))
                .andExpect(jsonPath("$.country", is(accountDto.getCountry())))
                .andExpect(jsonPath("$.id", is(accountsData.get(0).getId().intValue())))
                .andExpect(jsonPath("$.balance", is(accountsData.get(0).getBalance().intValue())))
                .andExpect(status().isOk());
    }

    @Test
    public void updateNotFoundAccountWithValidParameters() throws Exception {
        UpdateAccountDto accountDto = new UpdateAccountDto("Semsem", "UK");
        // Act
        mockMvc.perform(MockMvcRequestBuilders.put("/accounts/-1")
                        .content(this.mapper.writeValueAsString(accountDto))
                        .contentType(MediaType.APPLICATION_JSON))
                // Assert
                .andDo(print())
                .andExpect(jsonPath("$.status", is("NOT_FOUND")))
                .andExpect(status().isNotFound());
    }

    @Test
    public void updateValidAccountWithInvalidParameters() throws Exception {
        UpdateAccountDto accountDto = new UpdateAccountDto("", "");
        // Act
        mockMvc.perform(MockMvcRequestBuilders.put("/accounts/1")
                        .content(this.mapper.writeValueAsString(accountDto))
                        .contentType(MediaType.APPLICATION_JSON))
                // Assert
                .andDo(print())
                .andExpect(jsonPath("$.subErrors", hasSize(2)))
                .andExpect(jsonPath("$.subErrors[*].field", hasItems("country", "name")))
                .andExpect(jsonPath("$.subErrors[*].message", hasItems("must not be blank", "must not be blank")))
                .andExpect(jsonPath("$.subErrors[*].rejectedValue", hasItems(accountDto.getName(), accountDto.getCountry())))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void deleteValidAccount() throws Exception {
        // Act
        mockMvc.perform(MockMvcRequestBuilders.delete("/accounts/1")
                        .contentType(MediaType.APPLICATION_JSON))
                // Assert
                .andDo(print())
                .andExpect(jsonPath("$.id", is(accountsData.get(0).getId().intValue())))
                .andExpect(jsonPath("$.name", is(accountsData.get(0).getName())))
                .andExpect(jsonPath("$.country", is(accountsData.get(0).getCountry())))
                .andExpect(jsonPath("$.balance", is(accountsData.get(0).getBalance().intValue())))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteNotFoundAccount() throws Exception {
        // Act
        mockMvc.perform(MockMvcRequestBuilders.delete("/accounts/-1")
                        .contentType(MediaType.APPLICATION_JSON))
                // Assert
                .andDo(print())
                .andExpect(jsonPath("$.status", is("NOT_FOUND")))
                .andExpect(status().isNotFound());
    }


}
