package com.bsfdv.transaction.controller;

import com.bsfdv.transaction.controller.dto.AddAccountDto;
import com.bsfdv.transaction.controller.dto.UpdateAccountDto;
import com.bsfdv.transaction.controller.response.AccountResponseDtoV1;
import com.bsfdv.transaction.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    /**
     * Persists the given account in the database.
     *
     * @param accountDto The account to persist
     * @return The persisted account
     */
    @PostMapping
    public ResponseEntity<AccountResponseDtoV1> createOne(@Valid @RequestBody AddAccountDto accountDto) {
        AccountResponseDtoV1 responseDtoV1 = AccountResponseDtoV1.toDto(accountService.createOne(accountDto));
        return ResponseEntity
                .created(URI.create(responseDtoV1.getId().toString()))
                .body(responseDtoV1);
    }

    /**
     * Get a specific account's information.
     *
     * @param id The id of the account
     * @return The persisted account.
     */
    @GetMapping(path = "/{id}")
    public ResponseEntity<AccountResponseDtoV1> getOne(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(AccountResponseDtoV1.toDto(accountService.getOne(id)));
    }

    /**
     * Update a specific account's information.
     *
     * @param id The id of the account
     * @param accountDto Account new details
     * @return The persisted account.
     */
    @GetMapping(path = "/{id}")
    public ResponseEntity<AccountResponseDtoV1> updateOne(@Valid @RequestBody UpdateAccountDto accountDto, @PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(AccountResponseDtoV1.toDto(accountService.updateOne(accountDto, id)));
    }

    /**
     * Delete a specific account's.
     *
     * @param id The id of the account
     * @return The persisted account.
     */
    @GetMapping(path = "/{id}")
    public ResponseEntity<AccountResponseDtoV1> deleteOne(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(AccountResponseDtoV1.toDto(accountService.deleteOne(id)));
    }

}