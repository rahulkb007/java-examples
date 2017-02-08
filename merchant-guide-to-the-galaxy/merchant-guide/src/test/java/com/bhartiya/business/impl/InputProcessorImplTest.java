package com.bhartiya.business.impl;

import com.bhartiya.constants.RomanDigitEnum;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

/**
 * Created by Rahul on 07-02-2017.
 */
public class InputProcessorImplTest {
    InputProcessorImpl inputProcessor = new InputProcessorImpl();

    @Test
    public void testProcess() throws IOException {
        inputProcessor.process("src/test/resources/input.txt");
        assertThat(inputProcessor.getGalaxyInputRomanMap(), is(notNullValue()));
        assertThat(inputProcessor.getGalaxyInputRomanMap().containsKey("glob"), is(equalTo(true)));
        assertThat(inputProcessor.getGalaxyInputCreditMap().containsKey("Gold"), is(equalTo(true)));
        assertThat(inputProcessor.getGalaxyInputCreditMap().containsKey("Silver"), is(equalTo(true)));
        assertThat(inputProcessor.getGalaxyInputCreditMap().containsKey("Iron"), is(equalTo(true)));
        assertThat(inputProcessor.getGalaxyInputQuestionCreditMap().containsKey("how much is pish tegj glob glob ?"),
                is(equalTo(true)));
    }
}