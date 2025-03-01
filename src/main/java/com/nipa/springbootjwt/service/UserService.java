package com.nipa.springbootjwt.service;

import com.nipa.springbootjwt.dto.Response;
import com.nipa.springbootjwt.dto.UserResponseDto;
import com.nipa.springbootjwt.entity.User;
import com.nipa.springbootjwt.repository.UserRepository;
import com.nipa.springbootjwt.utils.ResponseBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Response getAllUsers() {
        List<User> users = userRepository.findAllBy();
        if (users.isEmpty()) {
            return ResponseBuilder.getFailResponse(HttpStatus.BAD_REQUEST, null, "Users not found");
        }
        List<UserResponseDto> userResponseDtos = new ArrayList<>();
        for (User user : users) {
            UserResponseDto userResponseDto = new UserResponseDto();
            userResponseDto.setUsername(user.getUsername());
            userResponseDto.setPhone(user.getPhone());
            userResponseDto.setEmail(user.getEmail());
            userResponseDtos.add(userResponseDto);

        }
        return ResponseBuilder.getSuccessResponse(HttpStatus.OK, userResponseDtos, "Successfully retrieve users");
    }
}
