package com.bsfdv.transaction.integration;

import com.bsfdv.transaction.TransactionApplicationTests;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;

public class TransactionIntegrationTests extends TransactionApplicationTests {

    @Autowired
    public ObjectMapper mapper;

    @BeforeEach
    public void setup() {
        super.setUp();
    }




}
