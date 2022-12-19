package com.stride.striders.services;

import com.stride.striders.entity.CustomUserDetails;
import com.stride.striders.entity.User;
import com.stride.striders.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if (user == null) {

            throw new UsernameNotFoundException("User Not Found");

        }
        return new CustomUserDetails(user);
    }
}
