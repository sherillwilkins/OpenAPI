package com.w83ll43.openapi.controller;

import com.w83ll43.openapisdk.client.OpenAPIClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("/interface")
public class InterfaceController {

    @Resource
    private OpenAPIClient client;

    @GetMapping("/sentence/{type}")
    public String getSentence(@PathVariable String type) throws UnsupportedEncodingException {
        return client.getRandomSentenceByType(type).getHitokoto();
    }

    @GetMapping("/joke")
    public String getJoke() throws UnsupportedEncodingException {
        return client.getRandomJoke().getText();
    }
}
