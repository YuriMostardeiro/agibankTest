package com.nt.service;

import com.nt.domain.Salesman;
import org.slf4j.LoggerFactory;

import java.util.List;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class SalesmanService extends BaseService {

    private final Logger logger = LoggerFactory.getLogger(FileService.class);
    public final Salesman salesman = new Salesman();

    public void getSalesmanData(String line) {
        List<String> data = getDataFromLine(line);

        if (data == null || data.isEmpty()) {
            logger.error("Error when try to split row for salesman.");
            return;
        }

        salesman.setCode(data.get(0));
        salesman.setCpf(data.get(1));
        salesman.setName(data.get(2));


        try {
            salesman.setSalary(Double.parseDouble(data.get(3)));
        }
        catch (NumberFormatException e) {
            logger.error("Error when parse salary to double.", e);
            return;
        }

        salesman.getSalesmanList().add(salesman);
        logger.info(salesman.getResult());
    }
}
