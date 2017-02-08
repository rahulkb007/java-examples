package com.bhartiya;

import com.bhartiya.business.InputProcessor;
import com.bhartiya.business.impl.InputProcessorImpl;
import com.bhartiya.constants.RomanDigitEnum;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Rahul on 05-02-2017.
 */
public class MerchantGuide {
    private static final Logger LOGGER = Logger.getLogger(MerchantGuide.class.getName());

    public static void main(String[] args) {
        InputProcessor inputProcessor = new InputProcessorImpl();
        String dir = "merchant-guide/src/main/resources/";
        try {
            writeOutputToFile(inputProcessor.process(dir + "input.txt"), dir);
            LOGGER.info("Merchant's Guide to the Galaxy");
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Exception", e);
        }
    }

    private static void writeOutputToFile(Map<String, String> questionAnswerMap, String dir) throws IOException {
        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(Paths.get(dir + "output.txt"))) {
            for (String answer : questionAnswerMap.values()) {
                bufferedWriter.write(answer + "\n");
            }

        }
    }
}
