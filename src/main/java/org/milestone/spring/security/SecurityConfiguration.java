package org.milestone.spring.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

    @Bean
    @SuppressWarnings("removal")
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests()
        .requestMatchers("/ticket/create", "/ticket/*/delete").hasAuthority("ADMIN")
        .requestMatchers("/ticket/*/edit").hasAnyAuthority("ADMIN", "OPERATOR")
        .requestMatchers("/ticket", "/ticket/*").hasAnyAuthority("ADMIN", "OPERATOR")
        .requestMatchers(HttpMethod.POST, "/note/create").hasAnyAuthority("ADMIN", "OPERATOR")
        .requestMatchers(HttpMethod.POST, "/ticket/user/*/state").hasAnyAuthority("ADMIN", "OPERATOR")
        .requestMatchers("/ticket/serach/*").hasAuthority("ADMIN")
        .requestMatchers("/api/ticket", "/api/ticket/*/category", "/api/ticket/*/state").permitAll()
        .requestMatchers("/*").permitAll()
        .requestMatchers("/webjars/**").permitAll()
        .and().formLogin()
        .and().logout()
        .and().exceptionHandling();
        return http.build();
    }

    @Bean
    DatabaseUserDetailsService userDetailsService(){
        return new DatabaseUserDetailsService();
    }
 
    @Bean
    PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
 
    @Bean
    @SuppressWarnings("deprecation")
    DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
}