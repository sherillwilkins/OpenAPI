package com.w83ll43.openapi.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.w83ll43.openapi.common.Result;
import com.w83ll43.openapi.entity.InterfaceInfo;
import com.w83ll43.openapi.service.InterfaceInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/interface")
public class InterfaceInfoController {

    @Resource
    private InterfaceInfoService interfaceInfoService;

    /**
     * 添加接口
     * @param interfaceInfo
     * @param request
     * @return
     */
    @PostMapping("/add")
    public Result<InterfaceInfo> addInterface(@RequestBody InterfaceInfo interfaceInfo, HttpServletRequest request) {
        return interfaceInfoService.addInterface(interfaceInfo, request);
    }

    /**
     * 根据 ID 获取接口信息
     * @param id
     * @param request
     * @return
     */
    @GetMapping("/{id}")
    public Result<InterfaceInfo> getInterfaceById(@PathVariable Long id, HttpServletRequest request) {
        return interfaceInfoService.getInterfaceById(id, request);
    }

    /**
     * 分页查询
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("/page")
    public Result<Page> page(int page, int pageSize, HttpServletRequest request) {
        return interfaceInfoService.page(page, pageSize, request);
    }

}
