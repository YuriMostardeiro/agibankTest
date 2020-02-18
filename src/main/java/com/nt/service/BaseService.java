package com.nt.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

abstract class BaseService {

    private final int SPLITSIZE = 4;
    private final String DELIMITER = "ç";
    private final Logger LOGGER = LoggerFactory.getLogger(BaseService.class);

    public List<String> getDataFromLine(String line) {
        List<String> result = Arrays.asList(line.split(DELIMITER));
        if (result.size() == SPLITSIZE) {
            return result;
        }
        else if (result.size() < SPLITSIZE) {
            return null;
        }

        List<String> newResult = splitFieldWithDelimiterCharacter(result, DELIMITER);
        if (newResult.size() != SPLITSIZE)
            return null;

        return newResult;
    }

    private List<String> splitFieldWithDelimiterCharacter(List<String> result, String delimiter) {
        List<String> newResult = new ArrayList<>();
        List<String> temp = new ArrayList<>();

        for (String split : result) {
            if (isNumeric(split)) {
                if (!temp.isEmpty()) {
                    newResult.add(String.join(delimiter, temp));
                    temp = new ArrayList<>();
                }
                newResult.add(split);
            } else {
                if (Character.isUpperCase(split.charAt(0)) && !temp.isEmpty()) {
                    newResult.add(String.join(delimiter, temp));
                    temp = new ArrayList<>();
                }
                temp.add(split);
            }
        }
        if (!temp.isEmpty())
            newResult.add(String.join(delimiter, temp));

        return newResult;
    }

    private boolean isNumeric(String value) {
        return value.trim().matches("([0-9-.]+)");
    }
}
