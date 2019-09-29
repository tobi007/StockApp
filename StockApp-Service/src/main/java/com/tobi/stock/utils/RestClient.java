package com.tobi.stock.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Kayode Emmanuel Oluwatobi
 * Date: 26/09/2019
 * Time: 8:06 PM
 **/


public class RestClient {

    private static final Logger logger = LoggerFactory.getLogger(RestClient.class);

    private String endPoint;
    private RestTemplate restTemplate;

    public RestClient() {
        restTemplate = new RestTemplate();
    }

    public void use(String endPoint){
        this.endPoint = endPoint;
    }

    public <T> ResponseEntity<T> makeGetRequest(Class<T> aClass) {
        ResponseEntity<T> response;
        response = restTemplate.getForEntity(endPoint, aClass);
        logger.info(String.format("Result - status (%s) has body: %s",response.getStatusCode(), response.hasBody()));
        return response;
    }
}
