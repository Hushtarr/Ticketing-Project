package com.cydeo.config;

import com.cydeo.service.SecurityService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
public class SecurityConfig {
//    @Bean
//    public UserDetailsService userDetailsService(PasswordEncoder encoder){
//        List<UserDetails>userList=new ArrayList<>();
//
//        userList.add(new User("Mike",
//                encoder.encode("abc"),
//                List.of((new SimpleGrantedAuthority("ROLE_ADMIN")))));
//
//        userList.add(new User("Cena",
//                encoder.encode("def"),
//                List.of((new SimpleGrantedAuthority("ROLE_MANAGER")))));
//        return new InMemoryUserDetailsManager(userList);
//
//    }
    private final SecurityService securityService;
    private final AuthSuccessHandler authSuccessHandler;

    public SecurityConfig(SecurityService securityService, AuthSuccessHandler authSuccessHandler) {
        this.securityService = securityService;
        this.authSuccessHandler = authSuccessHandler;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeRequests()
                // .antMatchers("/user/**").hasRole("ADMIN") // TODO
                // .hasrole and hasAuthority concerns to naming in DB
//                .antMatchers("/task/**").hasAnyRole("EMPLOYEE","ADMIN")
//                .antMatchers("/task/**").hasAuthority("ROLE_EMPLOYEE")
                    .antMatchers("/user/**").hasAuthority("Admin")
                    .antMatchers("/project/**").hasAuthority("Manager")
                    .antMatchers("/task/employee/**").hasAuthority("Employee")
                    .antMatchers("/task/**").hasAuthority("Manager")

                    .antMatchers("/","/login","/fragments/**","/assets/**","/images/**")
                    .permitAll()
                .anyRequest()
                .authenticated()
                //.httpBasic()
                .and()
                .formLogin()
                    .loginPage("/login")
                    //.defaultSuccessUrl("/welcome")
                    .successHandler(authSuccessHandler)
                    .failureUrl("/login?error=true")
                    .permitAll()
                .and()
                .logout()
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    .logoutSuccessUrl("/login")
                .and()
                .rememberMe()
                    .tokenValiditySeconds(120)
                    .key("App")
                    .userDetailsService(securityService)
                .and()
                .build();
    }
}

