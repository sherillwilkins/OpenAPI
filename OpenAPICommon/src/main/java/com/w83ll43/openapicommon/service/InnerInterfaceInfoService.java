package com.w83ll43.openapicommon.service;

import com.w83ll43.openapicommon.model.InterfaceInfo;

public interface InnerInterfaceInfoService {

    /**
     * 根据 url 获取调用的接口
     * @param url
     * @return
     */
    InterfaceInfo getInvokeInterface(String url);
}
