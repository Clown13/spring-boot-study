package org.example.springstudy.config;

/***
 * This is required so that we want to override the existing CSRF, CSRF needs to be disabled for REST API services so that it does not block all requests from coming in.
 *
 */

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.http.SessionCreationPolicy;
//When using @SpringBootApplication, Spring Boot automatically scans for components (@Component, @Service, @Repository, @RestController) and registers them as beans in the application context
//You need @Configuration when you manually define beans using @Bean.
@Configuration
public class SecurityConfig {
    //@Bean is method that tells Spring to create and manage instance of an object. Difference: Unline regular method, @Bean method is executed only once a startup to create and register an object in Spring's application cotext.

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())  // ✅ Disable CSRF for APIs
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().authenticated()  // ✅ Requires authentication for ALL endpoints
                )
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }

}