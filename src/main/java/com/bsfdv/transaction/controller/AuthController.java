package com.bsfdv.transaction.controller;

import com.bsfdv.transaction.controller.dto.LoginRequest;
import com.bsfdv.transaction.controller.dto.MessageResponse;
import com.bsfdv.transaction.controller.dto.SignupDto;
import com.bsfdv.transaction.controller.dto.UserInfoResponse;
import com.bsfdv.transaction.model.Account;
import com.bsfdv.transaction.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AccountService accountService;

    @Autowired
    public AuthController(AccountService accountService){
        this.accountService = accountService;
    }

    
    @PostMapping("/signin")
    public ResponseEntity<UserInfoResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        UserInfoResponse userInfoResponse = accountService.login(loginRequest);
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, userInfoResponse.getJwtcookie()).body(userInfoResponse);
    }

    @PostMapping("/signup")
    public ResponseEntity<Account> registerUser(@Valid @RequestBody SignupDto signUpRequest) throws Exception {
        Account account = accountService.createOne(signUpRequest);
        return ResponseEntity.ok(account);
    }
    @PostMapping("/signout")
    public ResponseEntity<?> logoutUser() {
        ResponseCookie responseCookie = accountService.logout();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, responseCookie.toString())
                .body(new MessageResponse("You've been signed out!"));
    }
}
