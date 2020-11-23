package com.boustead.AppACaller;

import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.*;
import org.apache.commons.httpclient.params.HttpMethodParams;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class RestCaller {

    @GetMapping("/getDataFromA")
    public String getDataFromA(){

        @Value({"${ip}")
        String ip;

        @Value({"${port}")
        String ip;

        @Value({"${}")
        String ip;

        String ip = System.getProperty("ip");
        String port = System.getProperty("port");

        System.out.println(ip + ":" + port);

        String url = "http://" + ip + ":" + port + "/returnData";

        HttpClient client = new HttpClient();
        GetMethod method = new GetMethod(url);
        try {
            // Execute the method.
            int statusCode = client.executeMethod(method);

            if (statusCode != HttpStatus.SC_OK) {
                System.err.println("Method failed: " + method.getStatusLine());
            }

            // Read the response body.
            byte[] responseBody = method.getResponseBody();

            // Deal with the response.
            // Use caution: ensure correct character encoding and is not binary data
            System.out.println(new String(responseBody));
            return new String(responseBody);

        } catch (HttpException e) {
            System.err.println("Fatal protocol violation: " + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Fatal transport error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Release the connection.
            method.releaseConnection();
        }

        return "error";

    }
}
