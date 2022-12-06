package com.verification.main.appUser;

import com.verification.main.Registration.Token.ConfirmationToken;
import com.verification.main.Registration.Token.ConfirmationTokenService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;


@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {

    private static String USER_NOT_FOUND_MESSAGE =
            "user with email %s not found";
    @Autowired
    private final AppUserRepository appUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        return appUserRepository.findByEmail(email).
                orElseThrow(
                        ()->{
                          return  new UsernameNotFoundException(String.format(USER_NOT_FOUND_MESSAGE, email));
                        }
                );
    }



    public String signUpUser( AppUser appUser){

         boolean userExists = appUserRepository
                 .findByEmail(appUser.getEmail())
                 .isPresent();

         if(userExists){
             throw new IllegalStateException("Email already taken!");
         }

         //encode the password
        String encodedPassword = bCryptPasswordEncoder
                .encode(appUser.getPassword());

         appUser.setPassword(encodedPassword);

        appUserRepository.save(appUser);

//         TODO: Send confirmation token

        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                appUser
        );

        confirmationTokenService.saveConfirmationToken(confirmationToken);


//        TODO: SEND MAIL

        return token;
    }
}
