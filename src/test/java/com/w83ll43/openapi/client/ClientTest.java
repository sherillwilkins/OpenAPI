package com.w83ll43.openapi.client;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.Sign;
import cn.hutool.crypto.asymmetric.SignAlgorithm;
import com.w83ll43.openapi.service.UserService;
import com.w83ll43.openapicommon.model.User;
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

    @Test
    void test() {
        User user = userService.getById(1650498539809599489L);
        OpenAPIClient1 client = new OpenAPIClient1(user.getAccessKey(), "secret1");
        String sentence = client.getSentence("a");
        System.out.println("sentence = " + sentence);
    }

    void createKey() {
        String accessKey = "w83ll43";
        Sign sign = SecureUtil.sign(SignAlgorithm.MD5withRSA);
        byte[] bytes = sign.sign(accessKey.getBytes());
        String secretKey = new String(bytes);
        System.out.println("accessKey = " + accessKey);
        System.out.println("secretKey = " + secretKey);
    }

    @Test
    void testSDKJoke() {
        ApiResponse apiResponse = client.getRandomSentenceByClient("a");
        try {
            System.out.println(OpenAPIClient.getResultString(apiResponse));
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
