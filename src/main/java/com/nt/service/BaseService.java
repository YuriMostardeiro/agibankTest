package com.nt.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

abstract class BaseService {

    private final int SPLITSIZE = 4;
    private final String DELIMITER = "ç";

    public List<String> getDataFromLine(String line) {
        List<String> result = Arrays.asList(line.split(DELIMITER));
        if (result.size() == SPLITSIZE) {
            return result;
        }
        else if (result.size() < SPLITSIZE) {
            return null;
        }

        List<String> returningData = splitFieldWithDelimiterCharacter(result, DELIMITER);
        if (returningData.size() != SPLITSIZE)
            return null;

        return returningData;
    }

    private List<String> splitFieldWithDelimiterCharacter(List<String> result, String delimiter) {
        List<String> returningData = new ArrayList<>();
        List<String> comparedData = new ArrayList<>();

        for (String split : result) {
            if (isNumeric(split)) {
                if (!comparedData.isEmpty()) {
                    returningData.add(String.join(delimiter, comparedData));
                    comparedData = new ArrayList<>();
                }
                returningData.add(split);
            } else {
                if (Character.isUpperCase(split.charAt(0)) && !comparedData.isEmpty()) {
                    returningData.add(String.join(delimiter, comparedData));
                    comparedData = new ArrayList<>();
                }
                comparedData.add(split);
            }
        }
        if (!comparedData.isEmpty())
            returningData.add(String.join(delimiter, comparedData));

        return returningData;
    }

    private boolean isNumeric(String value) {
        return value.trim().matches("([0-9-.]+)");
    }
}
