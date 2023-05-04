package com.w83ll43.openapi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.w83ll43.openapi.common.BaseContext;
import com.w83ll43.openapi.common.BusinessException;
import com.w83ll43.openapi.common.Code;
import com.w83ll43.openapi.common.Result;
import com.w83ll43.openapi.mapper.UserMapper;
import com.w83ll43.openapi.service.UserService;
import com.w83ll43.openapi.utils.AppUtils;
import com.w83ll43.openapi.vo.UserVo;
import com.w83ll43.openapicommon.model.User;
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
        // 参数不能为空
        if (StringUtils.isAnyBlank(username, password, checkPassword)) {
            throw new BusinessException(Code.PARAMS_ERROR.getCode(), "参数为空");
        }

        if (!password.equals(checkPassword)) {
            return Result.error("两次密码不一致！");
        }

        // 2、查询用户是否已经注册
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        User user = this.baseMapper.selectOne(queryWrapper);

        if (user != null) {
            throw new BusinessException(Code.NO_SUCH_USER.getCode(), "用户不存在");
        }

        // 3、加密密码
        String encryptPassword = DigestUtils.md5DigestAsHex(password.getBytes());

        // 4、插入数据
        user = new User();
        user.setUsername(username);
        user.setPassword(encryptPassword);

        // 5、生成 accessKey 和 secretKey
        String appId = AppUtils.getAppId();
        String appSecret = AppUtils.getAppSecret(appId);
        user.setAccessKey(appId);
        user.setSecretKey(appSecret);

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
            throw new BusinessException(Code.PARAMS_ERROR.getCode(), "参数为空");
        }

        // 2、密码在加密
        String encryptPassword = DigestUtils.md5DigestAsHex(password.getBytes());

        // 3、查询用户是否存在
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        User user = this.baseMapper.selectOne(queryWrapper);

        // 3.1、用户不存在
        if (user == null) {
            throw new BusinessException(Code.NO_SUCH_USER.getCode(), "用户不存在");
        }

        // 用户密码错误
        if (!user.getPassword().equals(encryptPassword)) {
            throw new BusinessException(Code.PASSWORD_ERROR.getCode(), "用户密码错误");
        }

        // 3.2、用户存在
        request.getSession().setAttribute("user_login_status", user);

        // User 转换为 UserVo
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(user, userVo);
        return Result.success(userVo);
    }

    /**
     * 用户登出
     * @param request
     * @return
     */
    @Override
    public Result<String> logout(HttpServletRequest request) {
        // 1、判断用户是否已经登录
        User user = (User)request.getSession().getAttribute("user_login_status");
        if (user == null) {
            throw new BusinessException(Code.NOT_LOGIN.getCode(), "用户未登录");
        }

        // 2、移除 session 信息
        request.getSession().removeAttribute("user_login_status");
        return Result.success("退出登录成功");
    }

    /**
     * 生成 AccessKey 和 SecretKey
     * @return
     */
    @Override
    public Result<String> generateAccessKeyAndSecret() {
        // 1、获取当前登录用户 ID
        Long id = BaseContext.getCurrentId();

        // 2、查询当前用户
        User user = this.getById(id);

        // 3、生成 accessKey 和 secretKey
        String appId = AppUtils.getAppId();
        String appSecret = AppUtils.getAppSecret(appId);
        user.setAccessKey(appId);
        user.setSecretKey(appSecret);

        // 5、更新数据
        this.updateById(user);
        return Result.success("生成签名成功！");
    }
}




