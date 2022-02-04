package com.bsfdv.transaction.controller;

import com.bsfdv.transaction.model.Account;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Collections;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAccountDto {


    @NotBlank
    private String name;

    @NotBlank
    private String country;

    public static Account toModel(UpdateAccountDto accountDto) {
        Account account = new Account();
        account.setName(accountDto.getName());
        account.setCountry(accountDto.getCountry());
        return account;
    }

}
