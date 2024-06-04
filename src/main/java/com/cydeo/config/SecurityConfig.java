package com.cydeo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
public class SecurityConfig {
    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder){
        List<UserDetails>userList=new ArrayList<>();

        userList.add(new User("Mike",
                encoder.encode("abc"),
                Arrays.asList((new SimpleGrantedAuthority("ROLE_ADMIN")))));

        userList.add(new User("Cena",
                encoder.encode("def"),
                Arrays.asList((new SimpleGrantedAuthority("ROLE_MANAGER")))));
        return new InMemoryUserDetailsManager(userList);

    }
}
