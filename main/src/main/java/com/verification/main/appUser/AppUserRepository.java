package com.verification.main.appUser;


import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
@Repository
public interface AppUserRepository {


    //find by email
    Optional<AppUser> findByEmail(String email);
    }
