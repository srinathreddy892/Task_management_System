package com.teluguskillhub.taskproject.security;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.teluguskillhub.taskproject.entity.Users;
import com.teluguskillhub.taskproject.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Users user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(
                        String.format("User with email %s is not found", email)));

        // Example: Assign ROLE_ADMIN statically
        Set<String> roles = new HashSet<>();
        roles.add("ROLE_ADMIN"); 

        return new User(
                user.getEmail(),
                user.getPassword(),
                userAuthorities(roles)
        );
    }

    private Collection<? extends GrantedAuthority> userAuthorities(Set<String> roles) {
        return roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}
