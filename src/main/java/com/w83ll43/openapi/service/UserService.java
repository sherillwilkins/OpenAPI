package com.w83ll43.openapi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.w83ll43.openapi.common.Result;
import com.w83ll43.openapi.vo.UserVo;
import com.w83ll43.openapicommon.model.User;

import javax.servlet.http.HttpServletRequest;

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

    /**
     * 用户登录
     * @param username
     * @param password
     * @param request
     * @return
     */
    Result<UserVo> login(String username, String password, HttpServletRequest request);

    /**
     * 用户登出
     * @param request
     * @return
     */
    Result<String> logout(HttpServletRequest request);

    /**
     * 生成签名
     * @return
     */
    Result<String> generateSign();

    /**
     * 生成 AccessKey 和 SecretKey
     * @return
     */
    Result<User> generateAccessKeyAndSecret();
}
