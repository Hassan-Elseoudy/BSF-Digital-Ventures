package com.bsfdv.transaction.util;

import com.bsfdv.transaction.model.Role;
import com.bsfdv.transaction.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import static com.bsfdv.transaction.model.ERole.*;

@Component
public class DataLoader implements ApplicationRunner {

    private final RoleRepository roleRepository;

    @Autowired
    public DataLoader(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public void run(ApplicationArguments args) {
        roleRepository.save(new Role(ROLE_USER));
        roleRepository.save(new Role(ROLE_MODERATOR));
        roleRepository.save(new Role(ROLE_ADMIN));
    }
}