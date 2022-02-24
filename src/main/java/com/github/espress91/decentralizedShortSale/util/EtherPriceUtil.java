package com.github.espress91.decentralizedShortSale.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URL;
import java.net.URLEncoder;

@Component
@Slf4j
public class EtherPriceUtil {

    @Autowired
    private RestTemplate restTemplate;

    private final String url = "https://min-api.cryptocompare.com/data/price?fsym=ETH&tsyms=USD";

    public String getEtherPrice() {
        try {
            ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
            return responseEntity.getBody();
        } catch (Exception e) {
            log.error("can not get ether price {}", e);
            return null;
        }

    }
}
