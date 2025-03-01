package com.nipa.springbootjwt.service;

import com.nipa.springbootjwt.config.JwtTokenProvider;
import com.nipa.springbootjwt.dto.LoginDto;
import com.nipa.springbootjwt.dto.LoginResponseDto;
import com.nipa.springbootjwt.entity.User;
import com.nipa.springbootjwt.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthService(UserRepository userRepository, AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public Object login(LoginDto loginDto, HttpServletRequest httpServletRequest) {
        User user = userRepository.findByPhoneAndStatus(loginDto.getPhone(), 1);
        if (user == null) {
            return "Invalid Phone or Password";
        }
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getPhone(),
                        loginDto.getPassword()));
        if (authentication.isAuthenticated()) {
            LoginResponseDto loginResponseDto = new LoginResponseDto();
            loginResponseDto.setToken(jwtTokenProvider.generateToken(authentication, httpServletRequest));
            loginResponseDto.setUsername(user.getUsername());
            return loginResponseDto;
        }
        return "Invalid Phone or Password";
    }
}
