package com.bhartiya.business.impl;

import com.bhartiya.business.InputProcessor;
import com.bhartiya.business.QuestionHandler;
import com.bhartiya.constants.InputEnum;
import com.bhartiya.constants.MerchantConstant;
import com.bhartiya.constants.RomanDigitEnum;
import com.bhartiya.steps.CalculateCreditFromGalaxyInput;
import com.bhartiya.steps.GalaxyRomanMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Rahul on 07-02-2017.
 */
public class InputProcessorImpl implements InputProcessor {
    private GalaxyRomanMapper galaxyRomanMapper;
    private CalculateCreditFromGalaxyInput calculateCreditFromGalaxyInput;
    private QuestionHandler questionHandler;
    private Map<String, RomanDigitEnum> galaxyInputRomanMap = null;
    private Map<String, Double> galaxyInputCreditMap = null;
    private Map<String, String> galaxyInputQuestionCreditMap = null;

    public InputProcessorImpl() {
        galaxyRomanMapper = new GalaxyRomanMapper();
        calculateCreditFromGalaxyInput = new CalculateCreditFromGalaxyInput();
        questionHandler = new QuestionHandlerImpl();
    }

    @Override
    public Map<String, String> process(String fileNameWithPath) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(fileNameWithPath));
        galaxyInputRomanMap = new HashMap<>();
        galaxyInputCreditMap = new HashMap<>();
        galaxyInputQuestionCreditMap = new HashMap<>();
        for (String line : lines) {
            Matcher matcher = null;
            InputEnum matchedInputEnum = null;
            for (InputEnum input : InputEnum.values()) {
                matcher = Pattern.compile(input.getRegex()).matcher(line);
                if (matcher.matches()) {
                    matchedInputEnum = input;
                    break;
                }
            }
            applyStrategy(line, matcher, matchedInputEnum);
        }
        return galaxyInputQuestionCreditMap;
    }

    private void applyStrategy(String line, Matcher matcher, InputEnum matchedInputEnum) {
        if (matchedInputEnum != null) {
            switch (matchedInputEnum) {
                case INITIALIZATION:
                    galaxyRomanMapper.init(galaxyInputRomanMap, line);
                    break;
                case CREDITS:
                    calculateCreditFromGalaxyInput.calculate(galaxyInputRomanMap, galaxyInputCreditMap, line, matcher);
                    break;
                case QUESTION_HOW_MANY:
                    questionHandler.handleHowManyQuestions(galaxyInputRomanMap, galaxyInputCreditMap,
                            galaxyInputQuestionCreditMap, line, matcher);
                    break;
                case QUESTION_HOW_MUCH:
                    questionHandler.handleHowMuchQuestions(galaxyInputRomanMap, galaxyInputCreditMap,
                            galaxyInputQuestionCreditMap, line, matcher);
                    break;
                default:
                    galaxyInputQuestionCreditMap.put(line, MerchantConstant.NO_IDEA);
            }
        } else {
            galaxyInputQuestionCreditMap.put(line, MerchantConstant.NO_IDEA);
        }
    }

    public Map<String, RomanDigitEnum> getGalaxyInputRomanMap() {
        return galaxyInputRomanMap;
    }

    public Map<String, Double> getGalaxyInputCreditMap() {
        return galaxyInputCreditMap;
    }

    public Map<String, String> getGalaxyInputQuestionCreditMap() {
        return galaxyInputQuestionCreditMap;
    }

}
