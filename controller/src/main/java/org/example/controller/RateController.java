package org.example.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.example.constant.ConstantsController;
import org.example.dto.RateDTO;
import org.example.json.DateDeserializer;
import org.example.service.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;

@RestController
public class RateController {
    private final RestTemplate restTemplate;
    @Autowired
    private RateService rateService;

    public RateController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private List<RateDTO> getRatesFromNBRB(String url) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Date.class, new DateDeserializer());
        Gson gson = gsonBuilder.create();
        return gson.fromJson(restTemplate.getForObject(url, String.class),
                new TypeToken<List<RateDTO>>() {
                }.getType());
    }

    @GetMapping("/rates/{date}/{curId}")
    public RateDTO getRatesByDateAndId(@PathVariable String date, @PathVariable String curId) {
        String NBRBUrlApi = ConstantsController.URL_RATE_BASIC_PATTERN
                + ConstantsController.URL_DATE_PARAMETER
                + date
                + ConstantsController.URL_DATE_CURRENCY_ID
                + curId;
        return rateService.createRateByDateAndCurrId(getRatesFromNBRB(NBRBUrlApi), curId);
    }

    @GetMapping("/rates/{date}")
    public List<RateDTO> getRatesByDate(@PathVariable String date) {
        String NBRBUrlApi = ConstantsController.URL_RATE_BASIC_PATTERN
                + ConstantsController.URL_DATE_PARAMETER
                + date;
        return rateService.createRatesByDate(getRatesFromNBRB(NBRBUrlApi));

    }
}