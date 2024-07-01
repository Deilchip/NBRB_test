package org.example.creator;

import lombok.experimental.UtilityClass;
import org.example.dto.RateDTO;

import java.util.Date;

@UtilityClass
public class CreatorObject {
    public RateDTO createRateDTO(Double currOffRate, Long curId, String curName, Integer curScale, String currAbbreviation, Date date) {
        return RateDTO
                .builder()
                .curOfficialRate(currOffRate)
                .curId(curId)
                .curName(curName)
                .curScale(curScale)
                .curAbbreviation(currAbbreviation)
                .date(date)
                .build();
    }
}
