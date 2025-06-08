package com.example.devFlow.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {


    public SecurityConfig() {
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeRequests(auth -> auth
                .requestMatchers("/", "/images/**", "/css/**", "/js/**", "/webjars/**").permitAll()
                .requestMatchers("/project", "/projects", "/projects/**", "/login", "/create", "/create_profile", "/create_project", 
                                 "/client_dashboard", "/create_profile_dev", "/login_success", "/success", "/login_info", "/register", 
                                 "/signup", "/signup_info", "/check-email", "/check-username", "/check-email-login", "/check-password-login",
                                 "/profile-settings", "/recommended")
                .permitAll()
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login").permitAll()
            )
            .logout(logout -> logout
                .permitAll()
            );

        return http.build();
    }
}
