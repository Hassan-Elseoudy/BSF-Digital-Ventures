package com.bsfdv.transaction.controller.dto;

import com.bsfdv.transaction.model.Account;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddAccountDto {

    @NotBlank
    private String name;

    @NotBlank
    private String country;

    public static Account toModel(AddAccountDto accountDto) {
        Account account = new Account();
        account.setName(accountDto.getName());
        account.setCountry(accountDto.getCountry());
        account.setBalance(0L);
        account.setIngoingTransactions(Collections.emptyList());
        account.setOutgoingTransactions(Collections.emptyList());
        return account;
    }

}
