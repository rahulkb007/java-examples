package com.bhartiya.business.validate;

import com.bhartiya.constants.RomanDigitEnum;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

/**
 * Created by Rahul on 08-02-2017.
 */
public class GalaxyInputValidatorTest {
    GalaxyInputValidator galaxyInputValidator;

    @Before
    public void setup() {
        galaxyInputValidator = GalaxyInputValidator.getInstance();
    }

    @Test
    public void isValidGalaxyInput() throws Exception {
        Map<String, RomanDigitEnum> galaxyInputRomanMap = new HashMap<>();
        String[] galaxyInputToken = "glob glob".split("[ ]");
        boolean result = galaxyInputValidator.isValidGalaxyInput(galaxyInputRomanMap, galaxyInputToken);
        assertThat(result, is(equalTo(false)));
    }

    @Test
    public void isValidMaterial() throws Exception {
        Map<String, Double> galaxyInputCreditMap = new HashMap<>();
        String material = "Gold";
        boolean result = galaxyInputValidator.isValidMaterial(galaxyInputCreditMap, material);
        assertThat(result, is(equalTo(false)));
    }

    @Test
    public void isValidRomanNumber() throws Exception {
        String romanNumber = "X";
        List<Integer> integerList = new ArrayList<>();
        integerList.add(RomanDigitEnum.X.getIntegerValue());
        boolean result = galaxyInputValidator.isValidRomanNumber(romanNumber, integerList);
        assertThat(result, is(equalTo(true)));
    }

}