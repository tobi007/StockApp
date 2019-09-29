package com.tobi.stock.utils;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Created by Kayode Emmanuel Oluwatobi
 * Date: 26/09/2019
 * Time: 8:06 PM
 **/

public class RestClientIntegrationTest {
    private static final Logger logger = LoggerFactory.getLogger(RestClientIntegrationTest.class);

    @Test
    public void test_get_IEX_Api_Documentation(){
        RestClient restClient = new RestClient();
        restClient.use("https://cloud-sse.iexapis.com/stable/");
        ResponseEntity<String> responseEntity = restClient.makeGetRequest(String.class);
        Assertions.assertThat(responseEntity.getStatusCode()).isEqualByComparingTo(HttpStatus.OK);
        Assertions.assertThat(responseEntity.getBody()).isNotEmpty();

    }
}
