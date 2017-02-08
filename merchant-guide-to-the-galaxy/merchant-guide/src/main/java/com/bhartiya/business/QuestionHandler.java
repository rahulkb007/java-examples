package com.bhartiya.business;

import com.bhartiya.constants.RomanDigitEnum;

import java.util.Map;
import java.util.regex.Matcher;

/**
 * Created by Rahul on 08-02-2017.
 */
public interface QuestionHandler {
    void handleHowManyQuestions(Map<String, RomanDigitEnum> galaxyInputRomanMap,
                                Map<String, Double> galaxyInputCreditMap,
                                Map<String, String> galaxyInputQuestionCreditMap,
                                String line,
                                Matcher matcher);

    void handleHowMuchQuestions(Map<String, RomanDigitEnum> galaxyInputRomanMap,
                                Map<String, Double> galaxyInputCreditMap,
                                Map<String, String> galaxyInputQuestionCreditMap,
                                String line,
                                Matcher matcher);
}
