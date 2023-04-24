package com.w83ll43.openapi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.w83ll43.openapi.common.Result;
import com.w83ll43.openapi.entity.User;
import com.w83ll43.openapi.mapper.UserMapper;
import com.w83ll43.openapi.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

/**
* @author w83ll43
* @description 针对表【user】的数据库操作Service实现
* @createDate 2023-04-24 21:06:04
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    /**
     * 用户注册
     * @param username
     * @param password
     * @param checkPassword
     * @return
     */
    @Override
    public Result<User> register(String username, String password, String checkPassword) {
        // 1、校验
        if (!password.equals(checkPassword)) {
            return Result.error("两次密码不一致！");
        }

        // 2、加密密码
        String encryptPassword = DigestUtils.md5DigestAsHex(password.getBytes());

        // 3、插入数据
        User user = new User();
        user.setUsername(username);
        user.setPassword(encryptPassword);
        this.save(user);
        return Result.success(user);
    }
}




