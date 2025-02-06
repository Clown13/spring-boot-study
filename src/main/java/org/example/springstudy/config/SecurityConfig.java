package org.example.springstudy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())  // ✅ CSRF disabled for REST APIs
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/bank/transaction").permitAll()  // ✅ Allow POST request for transactions
                        .anyRequest().authenticated()  // ✅ Secure all other endpoints
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));  // ✅ APIs should be stateless

        return http.build();
    }
}