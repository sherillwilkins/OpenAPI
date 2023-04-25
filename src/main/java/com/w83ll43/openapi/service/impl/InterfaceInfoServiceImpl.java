package com.w83ll43.openapi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.w83ll43.openapi.common.Result;
import com.w83ll43.openapi.entity.InterfaceInfo;
import com.w83ll43.openapi.entity.User;
import com.w83ll43.openapi.mapper.InterfaceInfoMapper;
import com.w83ll43.openapi.service.InterfaceInfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

/**
* @author w83ll43
* @description 针对表【interface_info】的数据库操作Service实现
* @createDate 2023-04-25 13:40:34
*/
@Service
public class InterfaceInfoServiceImpl extends ServiceImpl<InterfaceInfoMapper, InterfaceInfo>
    implements InterfaceInfoService{

    /**
     * 添加接口
     * @param interfaceInfo
     * @param request
     * @return
     */
    @Override
    public Result<InterfaceInfo> addInterface(InterfaceInfo interfaceInfo, HttpServletRequest request) {
        // 1、校验
        if (StringUtils.isAnyBlank(interfaceInfo.getName(), interfaceInfo.getUrl(), interfaceInfo.getMethod())) {
            return Result.error("必要参数为空");
        }

        // 2、判断用户是否拥有权限
        User user = (User) request.getSession().getAttribute("user_login_status");
        if (user == null) {
            return Result.error("用户未登录");
        }

        if (!user.getUsername().equals("w83ll43")) {
            return Result.error("用户未授权");
        }

        // 3、添加
        interfaceInfo.setCreateTime(LocalDateTime.now());
        interfaceInfo.setUpdateTime(LocalDateTime.now());
        interfaceInfo.setCreateUser(user.getId());
        this.save(interfaceInfo);

        return Result.success(interfaceInfo);
    }

    /**
     * 根据 ID 获取接口信息
     * @param id
     * @param request
     * @return
     */
    @Override
    public Result<InterfaceInfo> getInterfaceById(Long id, HttpServletRequest request) {
        // 1、校验
        if (id == null) {
            return Result.error("请求参数错误");
        }

        // 2、判断用户是否登录、是否授权
        User user = (User) request.getSession().getAttribute("user_login_status");
        if (user == null) {
            return Result.error("用户未登录");
        }

        if (!user.getUsername().equals("w83ll43")) {
            return Result.error("用户未授权");
        }

        // 3、查询
        InterfaceInfo interfaceInfo = this.getById(id);
        return Result.success(interfaceInfo);
    }
}




