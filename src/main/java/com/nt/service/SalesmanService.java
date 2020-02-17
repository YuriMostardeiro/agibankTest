package com.nt.service;

import java.util.List;

import com.nt.domain.DataInput;
import com.nt.domain.Salesman;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SalesmanService extends BaseService {

    private final Logger LOGGER = LoggerFactory.getLogger(SalesmanService.class);

    public void getSalesmanData(String row, DataInput dataInput) {
        List<String> data = getDataFromLine(row);

        if (data == null || data.isEmpty()) {
            LOGGER.error("Error when try to split row for salesman.");
            return;
        }

        Salesman salesman = new Salesman();
        salesman.setCode(data.get(0));
        salesman.setCpf(data.get(1));
        salesman.setName(data.get(2));

        try {
            salesman.setSalary(Double.parseDouble(data.get(3)));
        } catch (NumberFormatException e) {
            LOGGER.error("Error when parse salary to double.", e);
            return;
        }

        dataInput.getSalesmanList().add(salesman);
        LOGGER.info(salesman.getResult());
    }
}