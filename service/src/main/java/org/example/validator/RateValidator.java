package org.example.validator;

import lombok.experimental.UtilityClass;
import org.example.constants.ConstantsMessage;
import org.example.constants.ConstantsValidator;
import org.example.dto.RateDTO;

import java.security.InvalidParameterException;

@UtilityClass
public class RateValidator {
    public static void validate(RateDTO rate) throws InvalidParameterException {
        StringBuilder allExceptionsMessage = new StringBuilder();
        if (!validateNullFields(rate)) {
            allExceptionsMessage.append(ConstantsMessage.CURRENCIES_EXIST_BY_DATE).append(ConstantsMessage.PARAGRAPH_SEPARATOR);
        }
        if (!validateDate(rate)) {
            allExceptionsMessage.append(ConstantsMessage.ILLEGAL_DATE).append(ConstantsMessage.PARAGRAPH_SEPARATOR);
        }
        if (allExceptionsMessage.length() != ConstantsValidator.ZERO) {
            throw new InvalidParameterException(allExceptionsMessage.toString());
        }
    }

    private static boolean validateNullFields(RateDTO rate) {
        return rate.getDate() != null && rate.getCurOfficialRate() != null
                && rate.getCurId() != null && rate.getCurName() != null
                && rate.getCurAbbreviation() != null;
    }

    private static boolean validateDate(RateDTO rate) {
        return rate.getDate().compareTo(ConstantsValidator.MIN_DATE) >= 0
                && rate.getDate().compareTo(ConstantsValidator.MAX_DATE) <= 0;
    }
}
