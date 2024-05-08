package com.spider.userservice.security.services;

import com.spider.userservice.models.User;
import com.spider.userservice.repositories.UserRepository;
import com.spider.userservice.security.models.CustomUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


//created this class so that it can create a custom user details service for us
@Service
public class CustomeUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    public CustomeUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> response = userRepository.findByEmail(username);
        if (response.isEmpty()) {
            throw new UsernameNotFoundException("user with user email/user name " + username + " not found");
        }
        User user = response.get();
        CustomUserDetails customUserDetails = new CustomUserDetails(user);
        return customUserDetails;
    }
}
