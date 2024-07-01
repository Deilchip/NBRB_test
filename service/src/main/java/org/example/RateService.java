package org.example;

import org.example.dto.RateDTO;
import org.example.entity.Rate;
import org.example.repository.RateRepository;
import org.example.validator.RateValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RateService {

    private final RateRepository rateRepository;
    @Autowired
    protected ModelMapper modelMapper;

    @Autowired
    public RateService(RateRepository rateRepository) {
        this.rateRepository = rateRepository;
    }

    public List<RateDTO> createRatesByDate(List<RateDTO> ratesDTO) {
        ratesDTO.forEach(RateValidator::validate);
        List<Rate> ratesToSave = new ArrayList<>();
        Optional<Rate> rateOptional = rateRepository.findByDate(ratesDTO.get(0).getDate());
        if (rateOptional.isEmpty()) {
            ratesDTO.forEach(rateDTO -> ratesToSave.add(modelMapper.map(rateDTO, Rate.class)));
            rateRepository.saveAll(ratesToSave);
            return rateRepository.saveAll(ratesToSave)
                    .stream()
                    .map(rate -> modelMapper.map(rate, RateDTO.class))
                    .collect(Collectors.toList());
        } else {
            return ratesDTO;
        }
    }

    public RateDTO createRateByDateAndCurrId(List<RateDTO> ratesDTO, String currId) {
        ratesDTO.forEach(RateValidator::validate);
        Optional<Rate> rateOptional = rateRepository.findByDateAndCurId(ratesDTO.get(0).getDate(), Long.valueOf(currId));
        if (rateOptional.isEmpty()) {
            return modelMapper
                    .map(rateRepository
                            .save(modelMapper
                                    .map(findRateByCurrId(ratesDTO, currId), Rate.class)), RateDTO.class);
        } else {
            return findRateByCurrId(ratesDTO, currId);
        }
    }

    private RateDTO findRateByCurrId(List<RateDTO> ratesDTO, String currId) {
        return ratesDTO.stream()
                .filter(rate -> rate.getCurId().toString().equals(currId))
                .findFirst()
                .orElse(null);
    }
}