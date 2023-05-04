package com.w83ll43.openapi.service.impl.inner;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.w83ll43.openapi.service.UserService;
import com.w83ll43.openapicommon.model.User;
import com.w83ll43.openapicommon.service.InnerUserService;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;

@DubboService
public class InnerUserServiceImpl implements InnerUserService {

    @Resource
    private UserService userService;

    /**
     * 根据用户 accessKey 获取用户信息
     * @param accessKey
     * @return
     */
    @Override
    public User getInvokeUser(String accessKey) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("access_key", accessKey);

        User user = userService.getOne(queryWrapper);
        return user;
    }
}
