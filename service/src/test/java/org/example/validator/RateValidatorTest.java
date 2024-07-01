package org.example.validator;

import org.example.constant.DataConstants;
import org.example.creator.CreatorObject;
import org.example.dto.RateDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.security.InvalidParameterException;
import java.util.stream.Stream;

public class RateValidatorTest {


    @ParameterizedTest
    @MethodSource("initValidRate")
    void testValidChair(RateDTO rate) {
        Assertions.assertDoesNotThrow(() -> RateValidator.validate(rate));
    }

    @ParameterizedTest
    @MethodSource("initInvalidRate")
    void testInvalidParameterRate(RateDTO rate) {
        Assertions.assertThrows(InvalidParameterException.class, () -> RateValidator.validate(rate));
    }

    private static Stream<Arguments> initValidRate() {
        RateDTO validChair = CreatorObject.createRateDTO(DataConstants.VALID_CURR_OF_RATE[0],
                DataConstants.VALID_CURR_ID[0],
                DataConstants.VALID_CURR_NAME[0],
                DataConstants.VALID_CUR_SCALE[0],
                DataConstants.VALID_CURR_ABBREVIATION[0],
                DataConstants.VALID_DATES[0]);
        RateDTO validChairFirst = CreatorObject.createRateDTO(DataConstants.VALID_CURR_OF_RATE[1],
                DataConstants.VALID_CURR_ID[1],
                DataConstants.VALID_CURR_NAME[1],
                DataConstants.VALID_CUR_SCALE[1],
                DataConstants.VALID_CURR_ABBREVIATION[1],
                DataConstants.VALID_DATES[1]);
        return Stream.of(Arguments.of(validChair),
                Arguments.of(validChairFirst));
    }

    private static Stream<Arguments> initInvalidRate() {
        RateDTO invalidRate = CreatorObject.createRateDTO(DataConstants.VALID_CURR_OF_RATE[0],
                null,
                DataConstants.VALID_CURR_NAME[0],
                DataConstants.VALID_CUR_SCALE[0],
                DataConstants.VALID_CURR_ABBREVIATION[0],
                DataConstants.VALID_DATES[0]);
        RateDTO invalidRateFirst = CreatorObject.createRateDTO(DataConstants.VALID_CURR_OF_RATE[1],
                DataConstants.VALID_CURR_ID[1],
                DataConstants.VALID_CURR_NAME[1],
                DataConstants.VALID_CUR_SCALE[1],
                DataConstants.VALID_CURR_ABBREVIATION[1],
                DataConstants.NON_VALID_DATES[1]);
        return Stream.of(Arguments.of(invalidRate),
                Arguments.of(invalidRateFirst));
    }


}
