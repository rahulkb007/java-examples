package com.bhartiya.business;

import java.io.IOException;
import java.util.Map;

/**
 * Created by Rahul on 06-02-2017.
 */
public interface InputProcessor {
    Map<String, String> process(String fileNameWithPath) throws IOException;
}
