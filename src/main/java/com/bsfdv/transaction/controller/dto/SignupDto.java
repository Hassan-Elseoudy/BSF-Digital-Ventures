package com.bsfdv.transaction.controller.dto;

import com.bsfdv.transaction.model.Account;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignupDto {

    @NotBlank
    private String name;

    @NotBlank
    private String country;

    @NotBlank
    @Size(min = 3, max = 20)
    private String username;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;

    public static Account toModel(SignupDto accountDto) {
        Account account = new Account();
        account.setName(accountDto.getName());
        account.setCountry(accountDto.getCountry());
        account.setPassword(accountDto.getPassword());
        account.setUsername(accountDto.getUsername());
        account.setEmail(accountDto.getEmail());
        account.setBalance(0L);
        account.setRoles(Collections.emptySet());
        account.setIngoingTransactions(Collections.emptyList());
        account.setOutgoingTransactions(Collections.emptyList());
        return account;
    }

}
