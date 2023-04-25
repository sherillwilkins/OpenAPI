package com.w83ll43.openapi.controller;

import com.w83ll43.openapi.common.Result;
import com.w83ll43.openapi.entity.InterfaceInfo;
import com.w83ll43.openapi.service.InterfaceInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/interface")
public class InterfaceInfoController {

    @Resource
    private InterfaceInfoService interfaceInfoService;

    @PostMapping("/add")
    public Result<InterfaceInfo> addInterface(@RequestBody InterfaceInfo interfaceInfo, HttpServletRequest request) {
        return interfaceInfoService.addInterface(interfaceInfo, request);
    }

}
