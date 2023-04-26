package com.w83ll43.openapi.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.w83ll43.openapi.common.Result;
import com.w83ll43.openapi.entity.InterfaceInfo;

import javax.servlet.http.HttpServletRequest;

/**
* @author w83ll43
* @description 针对表【interface_info】的数据库操作Service
* @createDate 2023-04-25 13:40:34
*/
public interface InterfaceInfoService extends IService<InterfaceInfo> {

    /**
     * 添加接口
     * @param interfaceInfo
     * @param request
     * @return
     */
    Result<InterfaceInfo> addInterface(InterfaceInfo interfaceInfo, HttpServletRequest request);

    /**
     * 根据 ID 获取接口信息
     * @param id
     * @param request
     * @return
     */
    Result<InterfaceInfo> getInterfaceById(Long id, HttpServletRequest request);

    /**
     * 分页查询
     * @param page
     * @param pageSize
     * @return
     */
    Result<Page> page(int page, int pageSize, HttpServletRequest request);

    /**
     * 根据 ID 更新接口信息
     * @param interfaceInfo
     * @param request
     * @return
     */
    Result<String> updateInterfaceById(InterfaceInfo interfaceInfo, HttpServletRequest request);

    /**
     * 根据 ID 删除接口
     * @param id
     * @param request
     * @return
     */
    Result<String> deleteInterfaceById(Long id, HttpServletRequest request);
}
