package com.nt.service;

import java.util.Arrays;
import java.util.List;

public class BaseService {

    public BaseService() {

    }

    public static List<String> getDataFromLine(String line) {
        List<String> result = Arrays.asList(line.split("[^\\p{ASCII}]"));
        if (result.size() != 4) {
            return null;
        }

        return result;
    }
}
