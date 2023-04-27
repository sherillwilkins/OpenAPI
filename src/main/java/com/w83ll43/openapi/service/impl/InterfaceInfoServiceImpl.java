package com.w83ll43.openapi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.w83ll43.openapi.common.BusinessException;
import com.w83ll43.openapi.common.Code;
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
            throw new BusinessException(Code.PARAMS_ERROR.getCode(), "请求参数错误");
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
        if (id <= 0) {
            throw new BusinessException(Code.PARAMS_ERROR.getCode(), "参数错误");
        }

        // 2、判断用户是否登录、是否授权
        IsUserLogin(request);

        // 3、查询
        InterfaceInfo interfaceInfo = this.getById(id);

        if (interfaceInfo == null) {
            throw new BusinessException(Code.NO_SUCH_INTERFACE.getCode(), "接口不存在勒");
        }

        return Result.success(interfaceInfo);
    }


    /**
     * 抽取的方法
     * 判断用户是否登录、是否拥有权限
     * @param request
     */
    private void IsUserLogin(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user_login_status");
        if (user == null) {
            throw new BusinessException(Code.NOT_LOGIN.getCode(), "用户未登录");
        }

        if (!user.getUsername().equals("w83ll43")) {
            throw new BusinessException(Code.NO_AUTH_ERROR.getCode(), "用户无权限");
        }
    }

    /**
     * 分页查询
     * @param page
     * @param pageSize
     * @return
     */
    @Override
    public Result<Page> page(int page, int pageSize, HttpServletRequest request) {
        // 1、校验
        if (page <= 0 || pageSize <= 0) {
            throw new BusinessException(Code.PARAMS_ERROR.getCode(), "请求参数错误");
        }

        // 2、判断用户是否登录
        User user = (User) request.getSession().getAttribute("user_login_status");
        if (user == null) {
            throw new BusinessException(Code.NOT_LOGIN.getCode(), "用户未登录");
        }

        // 3、查询
        Page<InterfaceInfo> pageInfo = new Page<>(page, pageSize);

        QueryWrapper<InterfaceInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("createTime");

        this.page(pageInfo, queryWrapper);

        return Result.success(pageInfo);
    }

    /**
     * 根据 ID 更新接口信息
     * @param interfaceInfo
     * @param request
     * @return
     */
    @Override
    public Result<String> updateInterfaceById(InterfaceInfo interfaceInfo, HttpServletRequest request) {
        // 1、校验
        if (interfaceInfo == null) {
            throw new BusinessException(Code.PARAMS_ERROR.getCode(), "请求参数错误");
        }

        if (interfaceInfo.getId() == null) {
            return Result.error("未知接口");
        }

        // 2、判断用户是否登录以及用户是否拥有权限
        IsUserLogin(request);

        // 3、修改公共字段的值
        interfaceInfo.setUpdateTime(LocalDateTime.now());

        // 4、更新
        this.updateById(interfaceInfo);

        return Result.success("更新接口信息成功");
    }

    /**
     * 根据 ID 删除接口
     * @param id
     * @param request
     * @return
     */
    @Override
    public Result<String> deleteInterfaceById(Long id, HttpServletRequest request) {
        // 1、校验
        if (id <= 0) {
            throw new BusinessException(Code.PARAMS_ERROR.getCode(), "请求参数错误");
        }

        // 2、判断用户是否登录、是否拥有权限
        IsUserLogin(request);

        // 3、删除
        this.removeById(id);

        return Result.success("删除接口成功");
    }
}




