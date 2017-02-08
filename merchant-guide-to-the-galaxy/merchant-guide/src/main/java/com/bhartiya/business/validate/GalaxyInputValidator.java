package com.bhartiya.business.validate;

import com.bhartiya.constants.MerchantConstant;
import com.bhartiya.constants.RomanDigitEnum;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import static com.bhartiya.constants.MerchantConstant.*;

/**
 * Created by Rahul on 08-02-2017.
 */
public class GalaxyInputValidator {
    private static final Logger LOGGER = Logger.getLogger(GalaxyInputValidator.class.getName());
    private static GalaxyInputValidator GALAXY_INPUT_VALIDATOR;

    private GalaxyInputValidator() {
    }

    public static GalaxyInputValidator getInstance() {
        if (GALAXY_INPUT_VALIDATOR == null) {
            GALAXY_INPUT_VALIDATOR = new GalaxyInputValidator();
        }
        return GALAXY_INPUT_VALIDATOR;
    }

    public boolean isValidGalaxyInput(Map<String, RomanDigitEnum> galaxyInputRomanMap, String[] galaxyInputToken) {
        for (String token : galaxyInputToken) {
            if (!galaxyInputRomanMap.containsKey(token)) {
                return false;
            }
        }
        return true;
    }

    public boolean isValidMaterial(Map<String, Double> galaxyInputCreditMap, String material) {
        return galaxyInputCreditMap.containsKey(material);
    }

    public boolean isValidRomanNumber(String romanNumber, List<Integer> integerList) {
        return isRomanNumberFollowsRepetableConstraint(romanNumber) && isRomanNumberFollowsSubtractionConstraint(integerList);
    }

    private boolean isRomanNumberFollowsSubtractionConstraint(List<Integer> integerList) {
        for (int i = 0; i < integerList.size() - 1; i++) {
            //Rule1 - "V", "L", and "D" can never be subtracted.
            boolean romanNumberFollowsVLDRule = isRomanNumberFollowsVLDRule(integerList.get(i));
            //Rule2 - "I" can be subtracted from "V" and "X" only. "X" can be subtracted from "L" and "C" only.
            // "C" can be subtracted from "D" and "M" only.
            boolean romanNumberFollowsIXCSubtractionRule = isRomanNumberFollowsIXCSubtractionRule(integerList.get(i),
                    integerList.get(i + 1));
            if (!romanNumberFollowsVLDRule && romanNumberFollowsIXCSubtractionRule) {
                return false;
            }
        }
        return true;
    }

    private boolean isRomanNumberFollowsIXCSubtractionRule(Integer currentNumber, Integer nextNumberWithNegation) {
        boolean isValid = true;
        int nextNumber = Math.abs(nextNumberWithNegation);
        switch (Math.abs(currentNumber)) {
            case 1:
                if (nextNumber > RomanDigitEnum.I.getIntegerValue() && nextNumber != RomanDigitEnum.V.getIntegerValue()
                        && nextNumber != RomanDigitEnum.X.getIntegerValue()) {
                    LOGGER.warning(I_CAN_BE_SUBTRACTED_FROM_V_AND_X_ONLY);
                    isValid = false;
                }
                break;
            case 10:
                if (nextNumber > RomanDigitEnum.X.getIntegerValue() && nextNumber != RomanDigitEnum.L.getIntegerValue()
                        && nextNumber != RomanDigitEnum.C.getIntegerValue()) {
                    LOGGER.warning(X_CAN_BE_SUBTRACTED_FROM_L_AND_C_ONLY);
                    isValid = false;
                }
                break;
            case 100:
                if (nextNumber > RomanDigitEnum.C.getIntegerValue() && nextNumber != RomanDigitEnum.D.getIntegerValue()
                        && nextNumber != RomanDigitEnum.M.getIntegerValue()) {
                    LOGGER.warning(C_CAN_BE_SUBTRACTED_FROM_D_AND_M_ONLY);
                    isValid = false;
                }
                break;
        }
        return isValid;
    }

    private boolean isRomanNumberFollowsVLDRule(int number) {
        if (number == -RomanDigitEnum.V.getIntegerValue() || number == -RomanDigitEnum.L.getIntegerValue() ||
                number == -RomanDigitEnum.D.getIntegerValue()) {
            LOGGER.warning(V_L_AND_D_CAN_NEVER_BE_SUBTRACTED);
            return false;
        }
        return true;
    }

    private boolean isRomanNumberFollowsRepetableConstraint(String romanNumber) {
        for (int i = 0; i < MerchantConstant.ROMAN_REPETITION.length; i++) {
            if (Pattern.compile(MerchantConstant.ROMAN_REPETITION[i]).matcher(romanNumber).matches()) {
                System.out.format(ROMAN_NUMBER_FAILS_REPETITION_CONSTRAINT, romanNumber);
                return false;
            }
        }
        return true;
    }
}
