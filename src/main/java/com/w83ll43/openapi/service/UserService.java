package com.w83ll43.openapi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.w83ll43.openapi.common.Result;
import com.w83ll43.openapi.entity.User;

/**
* @author w83ll43
* @description 针对表【user】的数据库操作Service
* @createDate 2023-04-24 21:06:04
*/
public interface UserService extends IService<User> {

    /**
     * 用户注册
     * @param username
     * @param password
     * @param checkPassword
     * @return
     */
    Result<User> register(String username, String password, String checkPassword);
}
