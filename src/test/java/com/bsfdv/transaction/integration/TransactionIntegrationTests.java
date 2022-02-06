package com.bsfdv.transaction.integration;

import com.bsfdv.transaction.TransactionApplicationTests;
import com.bsfdv.transaction.controller.dto.CreateTransactionDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.util.NestedServletException;

import static com.bsfdv.transaction.data.MockData.zeroBalanceAccount;
import static com.bsfdv.transaction.model.TransactionType.*;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class TransactionIntegrationTests extends TransactionApplicationTests {

    @Autowired
    public ObjectMapper mapper;

    @BeforeEach
    public void setup() {
        super.setUp();
    }

    @Test
    @WithMockUser(value = "normalUser")
    void invalidPayTransactionZeroAmount() throws Exception {
        CreateTransactionDto transactionDto = new CreateTransactionDto(PAY, 0L, zeroBalanceAccount.getId());
        // Act
        mockMvc.perform(MockMvcRequestBuilders.post("/transactions")
                        .content(this.mapper.writeValueAsString(transactionDto))
                        .contentType(MediaType.APPLICATION_JSON))
                // Assert
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(value = "normalUser")
    void invalidPayTransactionNoReceiver() throws Exception {
        CreateTransactionDto transactionDto = new CreateTransactionDto(PAY, 1L, null);
        // Act
        mockMvc.perform(MockMvcRequestBuilders.post("/transactions")
                        .content(this.mapper.writeValueAsString(transactionDto))
                        .contentType(MediaType.APPLICATION_JSON))
                // Assert
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.subErrors[0].message", is("Incorrect Transaction parties.")));
    }

    @Test
    @WithMockUser(value = "normalUser")
    void validDepositTransactionNoReceiver() {

        assertThrows(NestedServletException.class, () -> {
            CreateTransactionDto transactionDto = new CreateTransactionDto(DEPOSIT, 1L, null);
            // Act
            mockMvc.perform(MockMvcRequestBuilders.post("/transactions")
                            .content(this.mapper.writeValueAsString(transactionDto))
                            .contentType(MediaType.APPLICATION_JSON))
                    // Assert
                    .andDo(print())
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.subErrors[0].message", is("Incorrect Transaction parties.")));

        });

    }

    @Test
    @WithMockUser(value = "normalUser")
    void validWithdrawTransactionNoReceiver() {

        assertThrows(NestedServletException.class, () -> {
            CreateTransactionDto transactionDto = new CreateTransactionDto(WITHDRAW, 1L, null);
            // Act
            mockMvc.perform(MockMvcRequestBuilders.post("/transactions")
                            .content(this.mapper.writeValueAsString(transactionDto))
                            .contentType(MediaType.APPLICATION_JSON))
                    // Assert
                    .andDo(print())
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.subErrors[0].message", is("Incorrect Transaction parties.")));

        });

    }

}
