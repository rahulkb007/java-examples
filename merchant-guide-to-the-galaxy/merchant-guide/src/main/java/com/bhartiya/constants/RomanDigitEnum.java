package com.bhartiya.constants;

/**
 * Created by Rahul on 05-02-2017.
 */
public enum RomanDigitEnum {
    I(1, true), V(5, false), X(10, true), L(50, false), C(100, true), D(500, false), M(1000, true);
    private final int integerValue;
    private final boolean repeatableAndSubtractable;

    RomanDigitEnum(int integerValue, boolean repeatableAndSubtractable) {
        this.integerValue = integerValue;
        this.repeatableAndSubtractable = repeatableAndSubtractable;

    }

    public int getIntegerValue() {
        return integerValue;
    }

    public boolean isRepeatableAndSubtractable() {
        return repeatableAndSubtractable;
    }
}
