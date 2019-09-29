package com.tobi.stock.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.tobi.stock.exceptions.QuoteNotFoundException;
import com.tobi.stock.services.api.IEXQuote;
import com.tobi.stock.services.api.Quote;
import com.tobi.stock.utils.RestClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Kayode Emmanuel Oluwatobi
 * Date: 26/09/2019
 * Time: 8:33 PM
 **/

@Service
@ConditionalOnProperty(
        value="iex.trading.api.enabled",
        havingValue = "true",
        matchIfMissing = true)
public class IEXQuoteService implements QuoteService {

    @Value("${iex.trading.api.token:secret}")
    private String token;

    @Value("${iex.trading.api.quote.endpoint}")
    private String quoteEndPoint;

    @Value("${iex.trading.api.batch.quote.endpoint}")
    private String batchQuoteEndPoint;
    private RestClient restClient;


    public IEXQuoteService() {
        restClient = new RestClient();
    }

    public Quote getQuote(String symbol){
        restClient.use(String.format(quoteEndPoint, symbol, token));
        try {
            ResponseEntity<IEXQuote> responseEntity = restClient.makeGetRequest(IEXQuote.class);
            if(responseEntity.getStatusCodeValue() == HttpStatus.OK.value()) {
                return responseEntity.getBody();
            } else {
                throw new QuoteNotFoundException(responseEntity.getBody().toString());
            }
        } catch (HttpClientErrorException httpEx){
            throw new QuoteNotFoundException(httpEx.getMessage());
        }
    }

    public Map<String, Quote> getQuote(List<String> symbols) {
        if(symbols.isEmpty()){
            return new HashMap<>();
        }
        restClient.use(String.format(batchQuoteEndPoint, String.join(",", symbols), token));
        try {
            ResponseEntity<String> responseEntity = restClient.makeGetRequest(String.class);
            if(responseEntity.getStatusCodeValue() == HttpStatus.OK.value()) {
                final ObjectMapper mapper = new ObjectMapper();
                final JsonNode input = mapper.readTree(responseEntity.getBody());

                Map<String, Quote> symbolsMapped = new HashMap<>();
                symbols.forEach(s ->
                        symbolsMapped.put(s.toLowerCase(), new Gson().fromJson(String.valueOf(input.get(s.toUpperCase()).get("quote")), IEXQuote.class))
                );

                return symbolsMapped;
            } else {
                throw new QuoteNotFoundException(responseEntity.getBody());
            }
        } catch (HttpClientErrorException | IOException httpEx) {
            throw new QuoteNotFoundException(httpEx.getMessage());
        }
    }


}
