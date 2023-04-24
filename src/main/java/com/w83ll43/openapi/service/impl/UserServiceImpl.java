package com.w83ll43.openapi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.w83ll43.openapi.common.Result;
import com.w83ll43.openapi.entity.User;
import com.w83ll43.openapi.mapper.UserMapper;
import com.w83ll43.openapi.service.UserService;
import com.w83ll43.openapi.vo.UserVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletRequest;

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

    /**
     * 用户登录
     * @param username
     * @param password
     * @param request
     * @return
     */
    @Override
    public Result<UserVo> login(String username, String password, HttpServletRequest request) {
        // 1、校验
        if (StringUtils.isAnyBlank(username, password)) {
            return Result.error("参数为空");
        }

        // 2、密码在加密
        String encryptPassword = DigestUtils.md5DigestAsHex(password.getBytes());

        // 3、查询用户是否存在
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
//        queryWrapper.eq("password", encryptPassword);
        User user = this.baseMapper.selectOne(queryWrapper);

        // 3.1、用户不存在
        if (user == null) {
            return Result.error("用户不存在");
        }

        // 用户密码错误
        if (!user.getPassword().equals(encryptPassword)) {
            return Result.error("用户密码错误");
        }

        // 3.2、用户存在
        request.getSession().setAttribute("user_login_state", user);

        // User 转换为 UserVo
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(user, userVo);
        return Result.success(userVo);
    }
}




