package com.w83ll43.openapi.client;


import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONUtil;
import com.w83ll43.openapi.common.Result;
import com.w83ll43.openapi.utils.SignUtils;

import java.util.HashMap;

/**
 * 调用第三方接口的客户端
 */
public class OpenAPIClient1 {

    private static final String GATEWAY_HOST = "http://47.113.223.234:8080";

    private String accessKey;

    private String secretKey;

    public OpenAPIClient1(String accessKey, String secretKey) {
        this.accessKey = accessKey;
        this.secretKey = secretKey;
    }

    public Result userSendMegInReggie(String phone) {
        // 可以单独传入http参数 这样参数会自动做URL编码 拼接在URL中
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("phone", phone);

        String jsonStr = JSONUtil.toJsonStr(hashMap);

        String result = HttpRequest.post(GATEWAY_HOST + "/user/sendMsg")
                .header(Header.CONTENT_TYPE, "application/json")
                .body(jsonStr)
                .execute().body();

        Result bean = JSONUtil.toBean(result, Result.class);

        return bean;
    }

    public String userLoginInReggie(String phone, String code) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("phone", phone);
        hashMap.put("code", code);

        String jsonStr = JSONUtil.toJsonStr(hashMap);

        String result = HttpRequest.post(GATEWAY_HOST + "/user/login")
                .addHeaders(getHeaderMap(jsonStr))
                .body(jsonStr)
                .execute().body();
        return result;
    }

    private HashMap<String, String> getHeaderMap(String body) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("accessKey", accessKey);
        hashMap.put("nonce", RandomUtil.randomNumbers(4));
        hashMap.put("body", body);
        hashMap.put("timestamp", String.valueOf(System.currentTimeMillis() / 1000));
        hashMap.put("sign", SignUtils.genSign(body, secretKey));
        return hashMap;
    }

    public String getSentence(String type) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("accessKey", this.accessKey);

//        Sign sign = SecureUtil.sign(SignAlgorithm.MD5withRSA);
//        byte[] signed = sign.sign(this.secretKey.getBytes());
//        hashMap.put("sign", new String(signed));
        hashMap.put("sign", this.secretKey);

        String result = HttpRequest.get("http://localhost:8081/sentences/get/" + type)
                .addHeaders(hashMap)
                .execute().body();
        return result;
    }

}
