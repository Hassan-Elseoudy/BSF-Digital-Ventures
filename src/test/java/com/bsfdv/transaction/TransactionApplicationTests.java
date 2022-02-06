package com.bsfdv.transaction;

import com.bsfdv.transaction.repository.AccountRepository;
import com.bsfdv.transaction.repository.RoleRepository;
import com.bsfdv.transaction.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static com.bsfdv.transaction.data.MockData.accountsData;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public
class TransactionApplicationTests {

    @Autowired
    public MockMvc mockMvc;

    @Autowired
    public TransactionRepository transactionRepository;

    @Autowired
    public AccountRepository accountRepository;

    @Autowired
    public RoleRepository roleRepository;

    protected void setUp() {
        accountRepository.saveAll(accountsData);

    }

    protected void tearDown() {
    }


}