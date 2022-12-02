package com.verification.main.appUser;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {

    private static String USER_NOT_FOUND_MESSAGE =
            "user with email %s not found";
    @Autowired
    private final AppUserRepository appUserRepository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        return appUserRepository.findByEmail(email).
                orElseThrow(
                        ()->{
                          return  new UsernameNotFoundException(String.format(USER_NOT_FOUND_MESSAGE, email));
                        }
                );
    }
}
