package com.w83ll43.openapicommon.service;

import com.w83ll43.openapicommon.model.User;

public interface InnerUserService {

    /**
     * 根据 accessKey 获取用户
     * @param accessKey
     * @return
     */
    User getInvokeUser(String accessKey);
}
