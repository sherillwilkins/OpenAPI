package com.w83ll43.openapi.client;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.Sign;
import cn.hutool.crypto.asymmetric.SignAlgorithm;
import com.w83ll43.openapi.entity.User;
import com.w83ll43.openapi.service.UserService;
import com.w83ll43.openapisdk.client.OpenAPIClient;
import com.w83ll43.openapisdk.constant.SDKConstant;
import com.w83ll43.openapisdk.model.entity.Sentence;
import com.w83ll43.openapisdk.model.response.ApiResponse;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

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
    void testSDKSentence() throws UnsupportedEncodingException {
        Sentence sentence = client.getRandomSentenceByType("a");
        System.out.println("sentence = " + sentence.getHitokoto());
    }

    @Test
    void testSDKJoke() throws UnsupportedEncodingException {
        ApiResponse apiResponse = client.getRandomJokeByClient();
        try {
            System.out.println(getResultString(apiResponse));
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private static String getResultString(ApiResponse response) throws IOException {
        StringBuilder result = new StringBuilder();
        result.append("Response from backend server").append(SDKConstant.CLOUDAPI_LF).append(SDKConstant.CLOUDAPI_LF);
        result.append("ResultCode:").append(SDKConstant.CLOUDAPI_LF).append(response.getCode()).append(SDKConstant.CLOUDAPI_LF).append(SDKConstant.CLOUDAPI_LF);
        if(response.getCode() != 200){
            result.append("Error description:").append(response.getHeaders().get("X-Ca-Error-Message")).append(SDKConstant.CLOUDAPI_LF).append(SDKConstant.CLOUDAPI_LF);
        }

        result.append("ResultBody:").append(SDKConstant.CLOUDAPI_LF).append(new String(response.getBody() , SDKConstant.CLOUDAPI_ENCODING));

        return result.toString();
    }
}
