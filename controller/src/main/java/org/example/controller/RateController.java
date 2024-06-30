package org.example.controller;

import org.example.constant.ConstantsController;
import org.example.dto.RateDTO;
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

    private RateDTO getRatesFromNBRB(String url) {
        return restTemplate.getForObject(url, org.example.dto.RateDTO.class);
    }

    @GetMapping("/{date}/{curId}")
    public RateDTO getRatesByDateAndId(@PathVariable String date, @PathVariable String curId) {
        String NBRBUrlApi = ConstantsController.URL_RATE_BASIC_PATTERN
                + ConstantsController.URL_DATE_PARAMETER
                + date
                + ConstantsController.URL_DATE_CURRENCY_ID
                + curId;
        return getRatesFromNBRB(NBRBUrlApi);
    }

    @GetMapping("/{date}")
    public RateDTO getRatesByDate(@PathVariable String date) {
        String NBRBUrlApi = ConstantsController.URL_RATE_BASIC_PATTERN
                + ConstantsController.URL_DATE_PARAMETER
                + date;
        return getRatesFromNBRB(NBRBUrlApi);
    }
}