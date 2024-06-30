package com.scm.service.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.scm.helpers.ResourceNotFound;
import com.scm.repository.UserRepository;


@Service
public class CustomSecurityUserDetails implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        //Get User By Email
        return userRepository.findByEmail(username).orElseThrow(() ->
        new ResourceNotFound("User not found" + username));

    }
    
}
