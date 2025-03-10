package com.nipa.springbootjwt.controller;


import com.nipa.springbootjwt.dto.Response;
import com.nipa.springbootjwt.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/getAll")
    public Response getAllUsers(){
        return userService.getAllUsers();
    }
}
