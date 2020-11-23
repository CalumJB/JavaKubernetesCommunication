package com.boustead.AppBEndpoint;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestEndpoint {

    @Value("${passed.value}")
    private String passed;

    @GetMapping("/returnData")
    public String returnData(){
        return "Hello from App B:" + passed;
    }
}
