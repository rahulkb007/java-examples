package com.bhartiya.business.converter;

import com.bhartiya.business.validate.GalaxyInputValidator;
import com.bhartiya.constants.RomanDigitEnum;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Rahul on 08-02-2017.
 */
public class RomanToDecimalConverter {
    private static RomanToDecimalConverter romanToDecimalConverter;

    private RomanToDecimalConverter() {
    }

    public static RomanToDecimalConverter getInstance() {
        if (romanToDecimalConverter == null) {
            romanToDecimalConverter = new RomanToDecimalConverter();
        }
        return romanToDecimalConverter;
    }

    public Integer convertRomanToDecimal(Map<String, RomanDigitEnum> galaxyInputRomanMap, String[] galaxyInputToken) {
        StringBuilder sb = new StringBuilder(galaxyInputToken.length);
        for (String inputToken : galaxyInputToken) {
            sb.append(galaxyInputRomanMap.get(inputToken));
        }
        return convert(sb.toString());
    }

    private Integer convert(String romanNumber) {
        List<Integer> integerList = new ArrayList<>();
        for (char romanCharacter : romanNumber.toCharArray()) {
            integerList.add(RomanDigitEnum.valueOf(Character.toString(romanCharacter)).getIntegerValue());
        }
        for (int i = 0; i < integerList.size() - 1; i++) {
            Integer currentItem = integerList.get(i);
            if (currentItem < integerList.get(i + 1)) {
                integerList.set(i, -currentItem);
            }
        }
        if (GalaxyInputValidator.getInstance().isValidRomanNumber(romanNumber, integerList)) {
            return calculateNumber(integerList);
        }
        return 0;
    }

    private Integer calculateNumber(List<Integer> integerList) {
        int result = 0;
        for (Integer number : integerList) {
            result += number;
        }
        return result;
    }
}
