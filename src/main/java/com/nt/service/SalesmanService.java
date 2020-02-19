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
        verifyDataAndAddSalesman(dataInput, data);
    }

    private void verifyDataAndAddSalesman(DataInput dataInput, List<String> data) {
        if (verifyData(data)) {
            addSalesmanToList(dataInput, data);
        }
    }

    private boolean verifyData(List<String> data) {
        if (data == null || data.isEmpty()) {
            LOGGER.error("Error to split row for sale.");
            return false;
        }
        return true;
    }

    private void addSalesmanToList(DataInput dataInput, List<String> data) {
        dataInput.getSalesmanList().add(setSalesmanData(data));
    }

    private Salesman setSalesmanData(List<String> data){
        Salesman salesman = new Salesman();
        salesman.setCode(data.get(0));
        salesman.setCpf(data.get(1));
        salesman.setName(data.get(2));
        salesman.setSalary(Double.parseDouble(data.get(3)));

        LOGGER.info(salesman.getResult());
        return salesman;
    }
}