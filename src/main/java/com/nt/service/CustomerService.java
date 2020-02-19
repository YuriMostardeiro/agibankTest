package com.nt.service;

import java.util.List;

import com.nt.domain.Customer;
import com.nt.domain.DataInput;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CustomerService extends BaseService {

    private final Logger LOGGER = LoggerFactory.getLogger(CustomerService.class);

    public void getCustomerData(String row, DataInput dataInput) {
        List<String> data = getDataFromLine(row);
        verifyDataAndAddCustomer(dataInput, data);
    }

    private void verifyDataAndAddCustomer(DataInput dataInput, List<String> data) {
        if (verifyData(data)) {
            addCustomerToList(dataInput, data);
        }
    }

    private boolean verifyData(List<String> data) {
        if (data == null || data.isEmpty()) {
            LOGGER.error("Error to split row for customer.");
            return false;
        }
        return true;
    }

    private void addCustomerToList(DataInput dataInput, List<String> data) {
        dataInput.getCustomerList().add(setCustomerData(data));
    }

    private Customer setCustomerData(List<String> data)
    {
        Customer customer = new Customer();
        customer.setCode(data.get(0));
        customer.setCompanyNumber(data.get(1));
        customer.setName(data.get(2));
        customer.setBusinessArea(data.get(3));
        LOGGER.info(customer.getResult());

        return customer;
    }
}