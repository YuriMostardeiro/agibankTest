package com.nt.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

abstract class BaseService {

    private final int SPLITSIZE = 4;
    private final String DELIMITER = "ç";

    public List<String> getDataFromLine(String line) {
        List<String> resultLine = Arrays.asList(line.split(DELIMITER));
        if (resultLine.size() == SPLITSIZE) {
            return resultLine;
        } else if (resultLine.size() < SPLITSIZE) {
            return null;
        }

        List<String> returningData = splitFieldWithDelimiterCharacter(resultLine);
        if (returningData.size() != SPLITSIZE)
            return null;

        return returningData;
    }

    private List<String> splitFieldWithDelimiterCharacter(List<String> resultLine) {
        List<String> returningData = new ArrayList<>();
        List<String> comparedData = new ArrayList<>();

        for (String splitRow : resultLine) {
            if (isNumeric(splitRow)) {
                if (!comparedData.isEmpty()) {
                    addDataToReturningList(comparedData, returningData);
                    comparedData = new ArrayList<>();
                }
                returningData.add(splitRow);
            } else {
                comparedData = characterVerifier(returningData, comparedData, splitRow);
                addDataToComparedList(splitRow, comparedData);
            }
        }
        if (!comparedData.isEmpty())
            addDataToReturningList(comparedData, returningData);

        return returningData;
    }

    private List<String> characterVerifier(List<String> returningData, List<String> comparedData, String splitRow) {
        if (Character.isUpperCase(splitRow.charAt(0)) && !comparedData.isEmpty()) {
            returningData.add(String.join(DELIMITER, comparedData));
            comparedData = new ArrayList<>();
        }
        return comparedData;
    }

    private void addDataToComparedList(String data, List<String> comparedData) {
        comparedData.add(data);
    }

    private void addDataToReturningList( List<String> comparedData, List<String> returningData) {
        returningData.add(String.join(DELIMITER, comparedData));
    }

    protected boolean isNumeric(String value) {
        return value.trim().matches("([0-9-.]+)");
    }
}
