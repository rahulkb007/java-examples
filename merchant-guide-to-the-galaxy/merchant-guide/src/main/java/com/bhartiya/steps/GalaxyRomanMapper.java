package com.bhartiya.steps;

import com.bhartiya.constants.RomanDigitEnum;

import java.util.Map;

/**
 * Created by Rahul on 07-02-2017.
 */
public class GalaxyRomanMapper {
    public void init(Map<String, RomanDigitEnum> galaxyInputRomanMap, String line) {
        String[] token = line.split("[ ]");
        galaxyInputRomanMap.put(token[0], RomanDigitEnum.valueOf(token[2]));
    }
}
