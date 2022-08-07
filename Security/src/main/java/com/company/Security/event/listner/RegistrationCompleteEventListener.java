package com.company.Security.event.listner;

import com.company.Security.entity.User;
import com.company.Security.event.RegistrationCompleteEvent;
import com.company.Security.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
public class RegistrationCompleteEventListener implements ApplicationListener<RegistrationCompleteEvent> {

    @Autowired
    private AuthService authService;

    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event) {
        // create verification token for the user with link

        User user = event.getUser();
        String token = UUID.randomUUID().toString();

        authService.saveVerificationTokenForUser(user, token);
        // send mail to the user need to implement
        // get template and bind data senEmail()
        String url = event.getApplicationUrl() + "/verifyRegistration" +
                "?token="+token;

        log.info("click the link to verify your account : {} ", url );
    }
}
