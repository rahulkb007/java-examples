package com.bhartiya.steps;

import com.bhartiya.business.converter.RomanToDecimalConverter;
import com.bhartiya.business.validate.GalaxyInputValidator;
import com.bhartiya.constants.RomanDigitEnum;

import java.util.Map;
import java.util.logging.Logger;
import java.util.regex.Matcher;

import static com.bhartiya.constants.MerchantConstant.GALAXY_INPUT_INVALID;

/**
 * Created by Rahul on 08-02-2017.
 */
public class CalculateCreditFromGalaxyInput {
    private final static Logger LOGGER = Logger.getLogger(CalculateCreditFromGalaxyInput.class.getName());

    public void calculate(Map<String, RomanDigitEnum> galaxyInputRomanMap, Map<String, Double> galaxyInputCreditMap, String line, Matcher matcher) {
        String[] galaxyInputToken = matcher.group(1).split("\\s");
        String material = matcher.group(2);
        int grossCredit = Integer.valueOf(matcher.group(3));
        if (GalaxyInputValidator.getInstance().isValidGalaxyInput(galaxyInputRomanMap, galaxyInputToken)) {
            int sum = RomanToDecimalConverter.getInstance().convertRomanToDecimal(galaxyInputRomanMap, galaxyInputToken);
            galaxyInputCreditMap.put(material, (grossCredit / Double.valueOf(sum)));
        } else {
            LOGGER.warning(GALAXY_INPUT_INVALID);
        }
    }
}
