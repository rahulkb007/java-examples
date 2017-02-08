package com.bhartiya.e2e

import com.bhartiya.MerchantGuide
import com.bhartiya.business.InputProcessor
import com.bhartiya.business.impl.InputProcessorImpl
import com.bhartiya.constants.RomanDigitEnum
import spock.lang.Specification

import java.nio.file.Files
import java.nio.file.Paths
import java.util.stream.Stream

/**
 * Created by Rahul on 03-02-2017.
 */
public class MerchantGuideE2E extends Specification {
    private InputProcessorImpl inputProcessor;

    def setup() {
        inputProcessor = new InputProcessorImpl();
    }

    def "Merchant's guide to the galaxy"() {
        when:
        inputProcessor.process("src/test/resources/input.txt");

        then:
        List<String> lines = Files.readAllLines(Paths.get("src/test/resources/output.txt"));
        assert lines.size() == inputProcessor.getGalaxyInputQuestionCreditMap().size();
        for (String line : lines) {
            assert inputProcessor.getGalaxyInputQuestionCreditMap().containsValue(line);
        }
    }
}
