package com.bhartiya.business.impl;

import com.bhartiya.business.QuestionHandler;
import com.bhartiya.business.converter.RomanToDecimalConverter;
import com.bhartiya.business.validate.GalaxyInputValidator;
import com.bhartiya.constants.MerchantConstant;
import com.bhartiya.constants.RomanDigitEnum;

import java.util.Map;
import java.util.regex.Matcher;

/**
 * Created by Rahul on 08-02-2017.
 */
public class QuestionHandlerImpl implements QuestionHandler {
    @Override
    public void handleHowManyQuestions(Map<String, RomanDigitEnum> galaxyInputRomanMap,
                                       Map<String, Double> galaxyInputCreditMap,
                                       Map<String, String> galaxyInputQuestionCreditMap,
                                       String line, Matcher matcher) {
        String creditName = matcher.group(1);
        String[] galaxyInputToken = matcher.group(2).split("\\s");
        String material = matcher.group(3);
        GalaxyInputValidator validator = GalaxyInputValidator.getInstance();
        if (validator.isValidGalaxyInput(galaxyInputRomanMap, galaxyInputToken) &&
                validator.isValidMaterial(galaxyInputCreditMap, material)) {
            Integer decimalValueFromRomanNumber = RomanToDecimalConverter.getInstance().
                    convertRomanToDecimal(galaxyInputRomanMap, galaxyInputToken);
            if (decimalValueFromRomanNumber != 0) {
                StringBuilder answerBuilder = new StringBuilder();
                Double total = galaxyInputCreditMap.get(material) * decimalValueFromRomanNumber;
                answerBuilder.append(matcher.group(2)).append(material).append(" is ").
                        append(total.intValue()).append(" ").append(creditName);
                galaxyInputQuestionCreditMap.put(line, answerBuilder.toString());
            } else {
                galaxyInputQuestionCreditMap.put(line, MerchantConstant.NO_IDEA);
            }
        }
    }

    @Override
    public void handleHowMuchQuestions(Map<String, RomanDigitEnum> galaxyInputRomanMap,
                                       Map<String, Double> galaxyInputCreditMap,
                                       Map<String, String> galaxyInputQuestionCreditMap,
                                       String line, Matcher matcher) {
        String galaxyInput = matcher.group(1);
        String[] galaxyInputToken = matcher.group(1).split("\\s");
        GalaxyInputValidator validator = GalaxyInputValidator.getInstance();
        if (validator.isValidGalaxyInput(galaxyInputRomanMap, galaxyInputToken)) {
            Integer decimalValueFromRomanNumber = RomanToDecimalConverter.getInstance().convertRomanToDecimal(galaxyInputRomanMap, galaxyInputToken);
            if (decimalValueFromRomanNumber != 0) {
                StringBuilder answerBuilder = new StringBuilder();
                answerBuilder.append(galaxyInput).append("is ").append(decimalValueFromRomanNumber);
                galaxyInputQuestionCreditMap.put(line, answerBuilder.toString());
            } else {
                galaxyInputQuestionCreditMap.put(line, MerchantConstant.NO_IDEA);
            }
        }
    }
}
