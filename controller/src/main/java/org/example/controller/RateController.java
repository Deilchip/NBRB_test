package org.example.controller;

import org.example.constant.ConstantsController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class RateController {

    private final RestTemplate restTemplate;

    public RateController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private String getRatesFromNBRB(String url) {
        return restTemplate.getForObject(url, String.class);
    }

    @GetMapping("/{date}/{curId}")
    public String getRatesByDateAndId(@PathVariable String date, @PathVariable String curId) {
        String NBRBUrlApi = ConstantsController.URL_RATE_BASIC_PATTERN
                + ConstantsController.URL_DATE_PARAMETER
                + date
                + ConstantsController.URL_DATE_CURRENCY_ID
                + curId;
        return getRatesFromNBRB(NBRBUrlApi);
    }

    @GetMapping("/{date}")
    public String getRatesByDate(@PathVariable String date) {
        String NBRBUrlApi = ConstantsController.URL_RATE_BASIC_PATTERN
                + ConstantsController.URL_DATE_PARAMETER
                + date;
        return getRatesFromNBRB(NBRBUrlApi);
    }
}