package com.bhartiya;

import com.bhartiya.constants.RomanDigitEnum;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.equalTo;

/**
 * Created by Rahul on 05-02-2017.
 */
public class RomanDigitEnumTest {
    @Test
    public void testEnumContainsAllRomanDigits() {
        assertThat(1, is(equalTo(RomanDigitEnum.I.getIntegerValue())));
        assertThat(5, is(equalTo(RomanDigitEnum.V.getIntegerValue())));
        assertThat(10, is(equalTo(RomanDigitEnum.X.getIntegerValue())));
        assertThat(50, is(equalTo(RomanDigitEnum.L.getIntegerValue())));
        assertThat(100, is(equalTo(RomanDigitEnum.C.getIntegerValue())));
        assertThat(500, is(equalTo(RomanDigitEnum.D.getIntegerValue())));
        assertThat(1000, is(equalTo(RomanDigitEnum.M.getIntegerValue())));
    }

    @Test
    public void testEnumValuesAreRepeatableAndSubtractable() {
        assertThat(true, is(equalTo(RomanDigitEnum.I.isRepeatableAndSubtractable())));
        assertThat(false, is(equalTo(RomanDigitEnum.V.isRepeatableAndSubtractable())));
        assertThat(true, is(equalTo(RomanDigitEnum.X.isRepeatableAndSubtractable())));
        assertThat(false, is(equalTo(RomanDigitEnum.L.isRepeatableAndSubtractable())));
        assertThat(true, is(equalTo(RomanDigitEnum.C.isRepeatableAndSubtractable())));
        assertThat(false, is(equalTo(RomanDigitEnum.D.isRepeatableAndSubtractable())));
        assertThat(true, is(equalTo(RomanDigitEnum.M.isRepeatableAndSubtractable())));
    }
}
