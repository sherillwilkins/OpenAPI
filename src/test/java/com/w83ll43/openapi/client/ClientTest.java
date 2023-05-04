package com.w83ll43.openapi.client;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.Sign;
import cn.hutool.crypto.asymmetric.SignAlgorithm;
import com.w83ll43.openapi.service.UserService;
import com.w83ll43.openapisdk.client.OpenAPIClient;
import com.w83ll43.openapisdk.model.response.ApiResponse;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class ClientTest {

    @Resource
    private UserService userService;

    @Resource
    private OpenAPIClient client;


    void createKey() {
        String accessKey = "w83ll43";
        Sign sign = SecureUtil.sign(SignAlgorithm.MD5withRSA);
        byte[] bytes = sign.sign(accessKey.getBytes());
        String secretKey = new String(bytes);
        System.out.println("accessKey = " + accessKey);
        System.out.println("secretKey = " + secretKey);
    }

    @Test
    void testSDKSentencePost() {
        ApiResponse apiResponse = client.getRandomSentencePostByType("a");
        try {
            System.out.println(OpenAPIClient.getResultString(apiResponse));
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @Test
    void testSDKSentenceGet() {
        ApiResponse apiResponse = client.getRandomSentenceGetByType("a");
        try {
            System.out.println(OpenAPIClient.getResultString(apiResponse));
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @Test
    void testSDKJoke() {
        ApiResponse apiResponse = client.getRandomJoke();
        try {
            System.out.println(OpenAPIClient.getResultString(apiResponse));
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
