package com.w83ll43.openapi.controller;


import com.w83ll43.openapi.common.Result;
import com.w83ll43.openapi.entity.User;
import com.w83ll43.openapi.service.UserService;
import com.w83ll43.openapi.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 用户注册
     * @param user
     * @return
     */
    @PostMapping("/register")
    public Result<User> register(@RequestBody User user) {
        return userService.register(user.getUsername(), user.getPassword(), user.getCheckPassword());
    }

    /**
     * 用户登录
     * @param user
     * @return
     */
    @PostMapping("/login")
    public Result<UserVo> login(@RequestBody User user, HttpServletRequest request) {
        return userService.login(user.getUsername(), user.getPassword(), request);
    }

    /**
     * 用户登出
     * @param request
     * @return
     */
    @GetMapping("/logout")
    public Result<String> logout(HttpServletRequest request) {
        return userService.logout(request);
    }

}
