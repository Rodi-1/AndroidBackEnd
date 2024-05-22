package com.rodi1.androidbackend.service.impl;

import com.rodi1.androidbackend.dao.UserRepository;
import com.rodi1.androidbackend.entity.User;
import com.rodi1.androidbackend.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> byUsername = userRepository.findByUsername(username);

        if (!byUsername.isPresent()) throw new UserNotFoundException("User with username " + username + " not found");

        return byUsername.get();
    }
}
