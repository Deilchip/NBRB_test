package org.example.service;


import org.example.constant.DataConstants;
import org.example.creator.CreatorObject;
import org.example.dto.RateDTO;
import org.example.entity.Rate;
import org.example.repository.RateRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RateServiceTest {
    @Mock
    private RateRepository rateRepository;

    private final ModelMapper modelMapper = new ModelMapper();

    private RateService rateService;

    private List<RateDTO> ratesDTOList;

    @BeforeEach
    void setUp() {
        rateService = new RateService(rateRepository, new ModelMapper());
        ratesDTOList = new ArrayList<>();
        for (int i = 0; i < 1; i++) {
            ratesDTOList.add(CreatorObject.createRateDTO(DataConstants.VALID_CURR_OF_RATE[i],
                    DataConstants.VALID_CURR_ID[i],
                    DataConstants.VALID_CURR_NAME[i],
                    DataConstants.VALID_CUR_SCALE[i],
                    DataConstants.VALID_CURR_ABBREVIATION[i],
                    DataConstants.VALID_DATES[i]));
        }
    }

    @Test
    void createRatesByDateValid() {
        when(rateRepository.findByDate(any())).thenReturn(Optional.empty());
        List<Rate> savedRates = new ArrayList<>();
        for (RateDTO rateDTO : ratesDTOList) {
            Rate rate = new Rate();
            modelMapper.map(rateDTO, rate);
            savedRates.add(rate);
        }
        when(rateRepository.saveAll(anyList())).thenReturn(savedRates);
        List<RateDTO> result = rateService.createRatesByDate(ratesDTOList);
        assertEquals(ratesDTOList, result);
    }
    @Test
    void createRateByDateAndCurrId() {
        when(rateRepository.findByDateAndCurId(any(), any())).thenReturn(Optional.empty());
        List<Rate> savedRates = new ArrayList<>();
        for (RateDTO rateDTO : ratesDTOList) {
            Rate rate = new Rate();
            modelMapper.map(rateDTO, rate);
            savedRates.add(rate);
        }
        when(rateRepository.save(any())).thenReturn(savedRates.get(0));
        RateDTO result = rateService.createRateByDateAndCurrId(ratesDTOList, String.valueOf(DataConstants.VALID_CURR_ID[0]));
        assertEquals(ratesDTOList.get(0), result);
    }
}