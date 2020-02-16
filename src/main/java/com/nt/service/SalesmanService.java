package com.nt.service;

import com.nt.domain.Salesman;
import org.slf4j.LoggerFactory;

import java.util.List;
import org.slf4j.Logger;

public class SalesmanService extends BaseService {

    private static final Logger logger = LoggerFactory.getLogger(FileService.class);
    public static final Salesman salesman = new Salesman();

    static void getSalesmanData(String line) {
        List<String> data = getDataFromLine(line);

        if (data.isEmpty()) {
            logger.error("Error when try to split line for salesman.");
            return;
        }

        salesman.setCode(data.get(0));
        salesman.setCpf(data.get(1));
        salesman.setName(data.get(2));
        salesman.setSalary(Double.parseDouble(data.get(3)));

        salesman.getSalesmanList().add(salesman);
        logger.info(salesman.getResult());
    }
}
