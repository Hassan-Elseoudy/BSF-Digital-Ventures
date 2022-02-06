package com.bsfdv.transaction.controller.dto;

import com.bsfdv.transaction.model.Account;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAccountDto {


    @NotBlank
    private String name;

    @NotBlank
    private String country;

    public static Account toModel(UpdateAccountDto accountDto, Account account) {
        account.setName(accountDto.getName());
        account.setCountry(accountDto.getCountry());
        return account;
    }

}
