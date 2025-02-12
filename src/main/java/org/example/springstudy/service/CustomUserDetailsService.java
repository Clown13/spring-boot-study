package org.example.springstudy.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service  // ✅ Marks this as a Spring-managed Bean
public class CustomUserDetailsService implements UserDetailsService {

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();  // ✅ Local instance to encode passwords

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("🔍 Searching for user: " + username);

        if ("admin".equals(username)) {
            return new User("admin", passwordEncoder.encode("admin123"), Collections.emptyList());  // ✅ Hash the password before storing
        } else {
            throw new UsernameNotFoundException("User not found: " + username);
        }
    }
}