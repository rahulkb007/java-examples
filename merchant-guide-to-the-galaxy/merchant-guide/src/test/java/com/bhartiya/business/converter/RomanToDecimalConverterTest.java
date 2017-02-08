package com.bhartiya.business.converter;

import com.bhartiya.constants.RomanDigitEnum;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

/**
 * Created by Rahul on 08-02-2017.
 */
public class RomanToDecimalConverterTest {
    RomanToDecimalConverter romanToDecimalConverter;

    @Before
    public void setup() {
        romanToDecimalConverter = RomanToDecimalConverter.getInstance();
    }

    @Test
    public void convertRomanToDecimal() throws Exception {
        Map<String, RomanDigitEnum> galaxyInputRomanMap = new HashMap<>();
        galaxyInputRomanMap.put("pish", RomanDigitEnum.X);
        galaxyInputRomanMap.put("tegj", RomanDigitEnum.L);
        galaxyInputRomanMap.put("glob", RomanDigitEnum.I);
        String[] galaxyInputToken = "pish tegj glob glob".split("[ ]");
        Integer result = romanToDecimalConverter.convertRomanToDecimal(galaxyInputRomanMap, galaxyInputToken);
        assertThat(result, is(equalTo(42)));
    }

}