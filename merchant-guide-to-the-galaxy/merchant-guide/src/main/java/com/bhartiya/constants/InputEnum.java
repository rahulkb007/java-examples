package com.bhartiya.constants;

/**
 * Created by Rahul on 07-02-2017.
 */
public enum InputEnum {
    INITIALIZATION("^([a-z]+) is ([I|V|X|L|C|D|M])$"),
    CREDITS("((?:[a-z]+ )+)([A-Z]\\w+) is (\\d+) ([A-Z]\\w+)$"),
    QUESTION_HOW_MANY("^how many ([a-zA-Z]\\w+) is ((?:\\w+ )+)([A-Z]\\w+) \\?$"),
    QUESTION_HOW_MUCH("^how much is ((?:\\w+[^0-9] )+)\\?$");

    private final String regex;

    InputEnum(String regex) {
        this.regex = regex;
    }

    public String getRegex() {
        return regex;
    }
}
