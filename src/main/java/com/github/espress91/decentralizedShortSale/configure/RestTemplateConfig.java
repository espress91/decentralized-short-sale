package com.github.espress91.decentralizedShortSale.configure;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {
    @Autowired
    private ObjectMapper objectMapper;

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().stream()
                .filter(messageConverter -> messageConverter instanceof MappingJackson2HttpMessageConverter)
                .forEach(messageConverter -> ((MappingJackson2HttpMessageConverter)messageConverter).setObjectMapper(objectMapper));
        restTemplate.setErrorHandler(getEmptyResponseErrorHandler());
        return restTemplate;
    }

    private ResponseErrorHandler getEmptyResponseErrorHandler() {
        return new ResponseErrorHandler() {
            @Override
            public boolean hasError(ClientHttpResponse clientHttpResponse) {
                return false;
            }

            @Override
            public void handleError(ClientHttpResponse clientHttpResponse) {

            }
        };
    }
}
