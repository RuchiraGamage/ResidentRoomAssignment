package com.company.Security.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.stereotype.Component;

@Component
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private static final String[] WHITE_LISTED_URLS = {
            "/auth/register",
            "/auth/verifyRegistration",
            "/auth/user/*/resendVerificationToken",
            "/auth/request/passwordReset",
            "/auth/passwordReset"
    };

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .cors()
                .disable()
                .authorizeRequests()
                .antMatchers(WHITE_LISTED_URLS).permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }
}
