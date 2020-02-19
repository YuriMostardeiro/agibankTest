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
        if (!dataVerifier(data)) return;
        addSalesmanToList(dataInput, data);
    }

    private Salesman setSalesmanData(List<String> data){

        Salesman salesman = new Salesman();
        salesman.setCode(data.get(0));
        salesman.setCpf(data.get(1));
        salesman.setName(data.get(2));
        if (setSalary(data, salesman)) return null;

        LOGGER.info(salesman.getResult());
        return salesman;
    }

    private void addSalesmanToList(DataInput dataInput, List<String> data) {
        Salesman salesman = setSalesmanData(data);
        if (salesman != null) {
            dataInput.getSalesmanList().add(salesman);
        }
    }

    private boolean setSalary(List<String> data, Salesman salesman) {
        try {
            salesman.setSalary(Double.parseDouble(data.get(3)));
        } catch (NumberFormatException e) {
            LOGGER.error("Error when parse salary to double.", e);
            return true;
        }
        return false;
    }

    private boolean dataVerifier(List<String> data) {
        if (data == null || data.isEmpty()) {
            LOGGER.error("Error to split row for salesman.");
            return false;
        }
        return true;
    }
}