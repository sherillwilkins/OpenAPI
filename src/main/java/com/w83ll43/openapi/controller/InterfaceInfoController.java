package com.w83ll43.openapi.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.w83ll43.openapi.common.BusinessException;
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

    /**
     * 根据 ID 更新接口信息
     * @param interfaceInfo
     * @param request
     * @return
     */
    @PutMapping("/update")
    public Result<String> updateInterfaceById(@RequestBody InterfaceInfo interfaceInfo, HttpServletRequest request) {
        return interfaceInfoService.updateInterfaceById(interfaceInfo, request);
    }

    /**
     * 根据 ID 删除接口
     * @param id
     * @param request
     * @return
     */
    @DeleteMapping("/{id}")
    public Result<String> deleteInterfaceById(@PathVariable Long id, HttpServletRequest request) {
        return interfaceInfoService.deleteInterfaceById(id, request);
    }

    /**
     * 测试全局异常处理
     * @return
     */
    @GetMapping
    public Result<String> testExceptionHandler() {
        throw new BusinessException(200, "自定义业务异常");
    }

}
