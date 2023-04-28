package com.w83ll43.openapi.client;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.Sign;
import cn.hutool.crypto.asymmetric.SignAlgorithm;
import com.w83ll43.openapi.common.Result;
import com.w83ll43.openapi.entity.User;
import com.w83ll43.openapi.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class ClientTest {

    @Resource
    private UserService userService;

    @Test
    void test() {
        String phone = "13412345678";
        User user = userService.getById(1650498539809599489L);
        OpenAPIClient client = new OpenAPIClient(user.getAccessKey(), user.getSecretKey());
        Result code = client.userSendMegInReggie(phone);
        String result = client.userLoginInReggie(phone, (String) code.getData());
        System.out.println("code = " + code);
        System.out.println("result = " + result);
    }

    void createKey() {
        String accessKey = "w83ll43";
        Sign sign = SecureUtil.sign(SignAlgorithm.MD5withRSA);
        byte[] bytes = sign.sign(accessKey.getBytes());
        String secretKey = new String(bytes);
        System.out.println("accessKey = " + accessKey);
        System.out.println("secretKey = " + secretKey);
    }
}
