package com.nipa.springbootjwt.controller;

import com.nipa.springbootjwt.dto.LoginDto;
import com.nipa.springbootjwt.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@CrossOrigin(origins = "*",
        methods = {RequestMethod.GET, RequestMethod.PUT, RequestMethod.POST,
                RequestMethod.DELETE, RequestMethod.OPTIONS})
@RestController
public class AuthController {
    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }
    @PostMapping("/login")
    public Object login(@RequestBody LoginDto loginDto,  HttpServletRequest httpServletRequest) {
        return authService.login(loginDto, httpServletRequest);
    }


}