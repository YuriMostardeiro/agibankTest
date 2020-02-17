package com.nt.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

abstract class BaseService {

    private final int splitSize = 4;
    private final String delimiter = "ç";

    public List<String> getDataFromLine(String line) {
        List<String> result = Arrays.asList(line.split(delimiter));
        if (result.size() == splitSize) {
            return result;
        }
        else if (result.size() < splitSize) {
            return null;
        }

        List<String> newResult = splitFieldWithDelimiterCharacter(result, delimiter);
        if (newResult.size() != splitSize)
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
