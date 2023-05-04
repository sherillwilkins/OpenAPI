package com.w83ll43.openapi.service.impl.inner;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.w83ll43.openapi.service.InterfaceInfoService;
import com.w83ll43.openapicommon.model.InterfaceInfo;
import com.w83ll43.openapicommon.service.InnerInterfaceInfoService;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;

@DubboService
public class InnerInterfaceInfoServiceImpl implements InnerInterfaceInfoService {

    @Resource
    private InterfaceInfoService interfaceInfoService;

    @Override
    public InterfaceInfo getInvokeInterface(String url) {
        QueryWrapper<InterfaceInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("url", url);

        InterfaceInfo interfaceInfo = interfaceInfoService.getOne(queryWrapper);
        return interfaceInfo;
    }
}
