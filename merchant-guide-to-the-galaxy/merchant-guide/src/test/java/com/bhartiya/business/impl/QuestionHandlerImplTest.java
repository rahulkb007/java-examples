package com.bhartiya.business.impl;

import com.bhartiya.constants.InputEnum;
import com.bhartiya.constants.RomanDigitEnum;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.*;

/**
 * Created by Rahul on 08-02-2017.
 */
public class QuestionHandlerImplTest {
    QuestionHandlerImpl questionHandler = new QuestionHandlerImpl();

    @Test
    public void testHandleHowManyQuestions() throws Exception {
        Map<String, RomanDigitEnum> galaxyInputRomanMap = new HashMap<>();
        Map<String, Double> galaxyInputCreditMap = new HashMap<>();
        Map<String, String> galaxyInputQuestionCreditMap = new HashMap<>();
        String line = "how many Credits is glob prok Silver ?";
        Matcher matcher = Pattern.compile(InputEnum.QUESTION_HOW_MANY.getRegex()).matcher(line);
        matcher.matches();
        questionHandler.handleHowManyQuestions(galaxyInputRomanMap, galaxyInputCreditMap, galaxyInputQuestionCreditMap, line, matcher);
        assertThat(galaxyInputQuestionCreditMap, is(notNullValue()));
    }

    @Test
    public void testHandleHowMuchQuestions() throws Exception {
        Map<String, RomanDigitEnum> galaxyInputRomanMap = new HashMap<>();
        Map<String, Double> galaxyInputCreditMap = new HashMap<>();
        Map<String, String> galaxyInputQuestionCreditMap = new HashMap<>();
        String line = "how much is pish tegj glob glob ?";
        Matcher matcher = Pattern.compile(InputEnum.QUESTION_HOW_MUCH.getRegex()).matcher(line);
        matcher.matches();
        questionHandler.handleHowMuchQuestions(galaxyInputRomanMap, galaxyInputCreditMap, galaxyInputQuestionCreditMap, line, matcher);
        assertThat(galaxyInputQuestionCreditMap, is(notNullValue()));

    }

}