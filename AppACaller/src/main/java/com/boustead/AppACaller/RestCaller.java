package com.boustead.AppACaller;

import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.*;
import org.apache.commons.httpclient.params.HttpMethodParams;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class RestCaller {

    Logger logger = LoggerFactory.getLogger(RestCaller.class);

    @Value("${app.b.ip}")
    private String appBIP;

    @Value("${app.b.port}")
    private String appBPort;

    @GetMapping("/getDataFromB")
    public String getDataFromA(){

        logger.info("App B ip is: " + appBIP);
        logger.info("App B port is: " + appBPort);

        System.out.println(appBIP + ":" + appBPort);

        String url = "http://" + appBIP + ":" + appBPort + "/returnData";

        HttpClient client = new HttpClient();
        logger.info("Created HttpClient");
        GetMethod method = new GetMethod(url);
        logger.info("Created GetMethod");

        try {
            logger.info("Entered try");
            // Execute the method.
            int statusCode = client.executeMethod(method);
            logger.info("Status code is: " + statusCode);
            if (statusCode != HttpStatus.SC_OK) {
                System.err.println("Method failed: " + method.getStatusLine());
            }

            // Read the response body.
            byte[] responseBody = method.getResponseBody();

            // Deal with the response.
            // Use caution: ensure correct character encoding and is not binary data
            System.out.println(new String(responseBody));
            return "This is App A, App B said: " + new String(responseBody);

        } catch (HttpException e) {
            logger.error("HttpException: " + e.getMessage());
            System.err.println("Fatal protocol violation: " + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            logger.error("IOException: " + e.getMessage());
            System.err.println("Fatal transport error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Release the connection.
            method.releaseConnection();
        }

        return "error";

    }
}
