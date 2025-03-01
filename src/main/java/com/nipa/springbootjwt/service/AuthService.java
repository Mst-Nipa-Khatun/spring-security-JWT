package com.nipa.springbootjwt.service;

import com.nipa.springbootjwt.config.JwtTokenProvider;
import com.nipa.springbootjwt.dto.LoginDto;
import com.nipa.springbootjwt.dto.LoginResponseDto;
import com.nipa.springbootjwt.dto.Response;
import com.nipa.springbootjwt.dto.UserDto;
import com.nipa.springbootjwt.entity.User;
import com.nipa.springbootjwt.repository.UserRepository;
import com.nipa.springbootjwt.utils.ResponseBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.passwordEncoder = passwordEncoder;
    }

    public Response login(LoginDto loginDto, HttpServletRequest httpServletRequest) {
        User user = userRepository.findByPhoneAndStatus(loginDto.getPhone(), 1);
        if (user == null) {
            return ResponseBuilder.getFailResponse(HttpStatus.BAD_REQUEST,
                    null, "Invalid Phone or Password");
        }
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getPhone(),
                        loginDto.getPassword()));
        if (authentication.isAuthenticated()) {
            LoginResponseDto loginResponseDto = new LoginResponseDto();
            loginResponseDto.setToken(jwtTokenProvider.generateToken(authentication, httpServletRequest));
            loginResponseDto.setUsername(user.getUsername());
            return ResponseBuilder.getFailResponse(HttpStatus.OK,
                    loginResponseDto, "Login Successful");
        }
        return ResponseBuilder.getFailResponse(HttpStatus.BAD_REQUEST,
                null, "Invalid Phone or Password");
    }

    public Response register(UserDto userDto) {
        User user = userRepository.findByPhoneAndStatus(userDto.getPhone(), 1);
        if (user == null) {
            user = new User();
            user.setStatus(1);
            user.setUsername(userDto.getUsername());
            user.setPhone(userDto.getPhone());
            String encodedPass = passwordEncoder.encode(userDto.getPassword());
            user.setPassword(encodedPass);
            user.setEmail(userDto.getEmail());
            User savedUser = userRepository.save(user);

            return ResponseBuilder.getSuccessResponse(HttpStatus.CREATED, null,
                    "Register Successful");
        }
        return ResponseBuilder.getFailResponse(HttpStatus.BAD_REQUEST, null, "Exist user using this phone");
    }
}
