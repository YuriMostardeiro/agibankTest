package com.nt.service;

import java.util.Arrays;
import java.util.List;

public abstract class BaseService {

    public BaseService() {

    }

    public List<String> getDataFromLine(String line) {
        List<String> result = Arrays.asList(line.split("[^\\p{ASCII}]"));
        if (result.size() != 4) {
            return null;
        }

        return result;
    }
}
