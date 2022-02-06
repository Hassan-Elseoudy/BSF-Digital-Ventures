package com.bsfdv.transaction.controller;

import com.bsfdv.transaction.controller.dto.*;
import com.bsfdv.transaction.controller.response.AccountResponseDtoV1;
import com.bsfdv.transaction.service.AccountService;
import com.bsfdv.transaction.util.error.ApiError;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @Operation(summary = "Create New Account")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created Successfully",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = AccountResponseDtoV1.class))}),
            @ApiResponse(responseCode = "409", description = "Username or Email Exists",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))})})
    @PostMapping
    public ResponseEntity<AccountResponseDtoV1> createOne(@Valid @RequestBody SignupDto accountDto) throws Exception {
        AccountResponseDtoV1 responseDtoV1 = AccountResponseDtoV1.toDto(accountService.createOne(accountDto));
        return ResponseEntity
                .created(URI.create(responseDtoV1.getId().toString()))
                .body(responseDtoV1);
    }

    /**
     *
     * @param id The id of the account
     * @return The persisted account.
     */
    @Operation(summary = "Get a specific account's information")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the Account",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = AccountResponseDtoV1.class)) }),
            @ApiResponse(responseCode = "404", description = "Account not found",
                    content = @Content) })
    @GetMapping(path = "/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<AccountResponseDtoV1> getOne(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(AccountResponseDtoV1.toDto(accountService.getOne(id)));
    }

    /**
     *
     * @param id         The id of the account
     * @param accountDto Account new details
     * @return The persisted account.
     */
    @Operation(summary = "Update a specific account's information")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Updated Successfully",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = AccountResponseDtoV1.class)) })})
    @PutMapping(path = "/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<AccountResponseDtoV1> updateOne(@Valid @RequestBody UpdateAccountDto accountDto, @PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(AccountResponseDtoV1.toDto(accountService.updateOne(accountDto, id)));
    }

    /**
     * Delete a specific account's.
     *
     * @param id The id of the account
     * @return The persisted account.
     */
    @Operation(summary = "Delete a specific account's")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deleted Successfully",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = AccountResponseDtoV1.class)) })})
    @DeleteMapping(path = "/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<AccountResponseDtoV1> deleteOne(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(AccountResponseDtoV1.toDto(accountService.deleteOne(id)));
    }

    @Operation(summary = "Login")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Logged in Successfully",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = UserInfoResponse.class)) })})
    @PostMapping("/login")
    public ResponseEntity<UserInfoResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        UserInfoResponse userInfoResponse = accountService.login(loginRequest);
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, userInfoResponse.getJwtcookie())
                .body(userInfoResponse);
    }

    @Operation(summary = "Logout")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Logged out Successfully",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = MessageResponse.class)) })})
    @PostMapping("/logout")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<MessageResponse> logoutUser() {
        ResponseCookie responseCookie = accountService.logout();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, responseCookie.toString())
                .body(new MessageResponse("You've been signed out!"));
    }


}