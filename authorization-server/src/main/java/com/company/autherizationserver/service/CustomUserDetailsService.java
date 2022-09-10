package com.company.autherizationserver.service;

import com.company.autherizationserver.entity.User;
import com.company.autherizationserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUserName(userName);

        if (!user.isPresent())
            throw new UsernameNotFoundException("No user found");
 
        return new org.springframework.security.core.userdetails
                .User(
                user.get().getEmail(),
                user.get().getPassword(),
                user.get().isEnable(),
                true,
                true,
                true,
                Objects.requireNonNull(getUserAuthorities(Collections.singletonList(user.get().getRole())))
                );

    }

    private Collection<? extends GrantedAuthority> getUserAuthorities(List<String> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();

        for (String role : roles ){
            authorities.add(new SimpleGrantedAuthority(role));
        }
        return authorities;
    }
}
