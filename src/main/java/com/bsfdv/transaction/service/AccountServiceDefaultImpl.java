package com.bsfdv.transaction.service;

import com.bsfdv.transaction.controller.dto.LoginRequest;
import com.bsfdv.transaction.controller.dto.SignupDto;
import com.bsfdv.transaction.controller.dto.UpdateAccountDto;
import com.bsfdv.transaction.controller.dto.UserInfoResponse;
import com.bsfdv.transaction.model.Account;
import com.bsfdv.transaction.model.ERole;
import com.bsfdv.transaction.repository.AccountRepository;
import com.bsfdv.transaction.repository.RoleRepository;
import com.bsfdv.transaction.util.security.JwtUtils;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AccountServiceDefaultImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final AuthenticationManager authenticationManager;
    private final RoleRepository roleRepository;
    private final PasswordEncoder encoder;
    private final JwtUtils jwtUtils;

    @Autowired
    public AccountServiceDefaultImpl(AccountRepository accountRepository, AuthenticationManager authenticationManager, RoleRepository roleRepository, PasswordEncoder encoder, JwtUtils jwtUtils) {
        this.accountRepository = accountRepository;
        this.authenticationManager = authenticationManager;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
        this.jwtUtils = jwtUtils;
    }

    @Override
    public Account getOne(Long id) {
        return accountRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Account deleteOne(Long id) {
        Account account = getOne(id);
        accountRepository.deleteById(account.getId());
        return account;
    }

    @Override
    public Account updateOne(UpdateAccountDto accountDto, Long id) {
        Account account = UpdateAccountDto.toModel(accountDto, getOne(id));
        return accountRepository.save(account);
    }

    @Override
    public Account createOne(SignupDto signupDto) throws Exception {
        if (accountRepository.existsByUsername(signupDto.getUsername()) || accountRepository.existsByEmail(signupDto.getEmail())) {
            throw new EntityExistsException("Error: Username or Email is already taken!");
        }

        Account account = SignupDto.toModel(signupDto);
        account.setPassword(encoder.encode(signupDto.getPassword()));
        account.setRoles(Set.of(roleRepository.findByName(ERole.ROLE_USER).orElseThrow(() -> new RuntimeException("Error: Role is not found."))));

        return accountRepository.save(account);
    }

    @Override
    public Account setBalance(Long accountId, Long balance) {
        Account account = getOne(accountId);
        account.setBalance(balance);
        return accountRepository.save(account);
    }

    @Override
    public UserInfoResponse login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        AccountDetailsImpl userDetails = (AccountDetailsImpl) authentication.getPrincipal();
        ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        return new UserInfoResponse(userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), roles, jwtCookie.toString());
    }

    @Override
    public ResponseCookie logout() {
        return jwtUtils.getCleanJwtCookie();
    }
}
