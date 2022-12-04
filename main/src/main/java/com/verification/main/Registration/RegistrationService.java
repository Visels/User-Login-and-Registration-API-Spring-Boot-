package com.verification.main.Registration;

import com.verification.main.appUser.AppUser;
import com.verification.main.appUser.AppUserRole;
import com.verification.main.appUser.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final EmailValidator emailValidator;
    private final AppUserService appUserService;

    public String register(RegistrationRequest request) {

            boolean isValidEmail = emailValidator.test(request.getEmail());

            if(!isValidEmail){
                throw new IllegalStateException("Email not valid");
            }
        return appUserService.signUpUser(
                new AppUser(
                     request.getFirstName(),
                        request.getLastName(),
                        request.getPassword(),
                        request.getEmail(),
                        AppUserRole.USER
                )
        );
    }
}
