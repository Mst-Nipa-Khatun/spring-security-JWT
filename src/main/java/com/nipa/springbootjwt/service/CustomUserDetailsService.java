package com.nipa.springbootjwt.service;

import com.nipa.springbootjwt.dto.UserPrincipal;
import com.nipa.springbootjwt.entity.User;
import com.nipa.springbootjwt.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String phone) throws UsernameNotFoundException {
        User user = userRepository.findByPhoneAndStatus(phone,1);
        if (user==null){
            throw new UsernameNotFoundException("Phone Number Not Found");
        }
        UserPrincipal userDetails = UserPrincipal.create(user);
        if (userDetails == null) {
            throw new UsernameNotFoundException("Phone Number Not Found");
        }
        return userDetails;
    }
}