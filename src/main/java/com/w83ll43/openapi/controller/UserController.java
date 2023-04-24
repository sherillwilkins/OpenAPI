package com.w83ll43.openapi.controller;


import com.w83ll43.openapi.common.Result;
import com.w83ll43.openapi.entity.User;
import com.w83ll43.openapi.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Result<User> register(@RequestBody User user) {
        return userService.register(user.getUsername(), user.getPassword(), user.getCheckPassword());
    }

}
