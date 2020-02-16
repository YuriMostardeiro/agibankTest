package com.nt.service;

import com.nt.domain.Customer;
import org.slf4j.LoggerFactory;

import java.util.List;
import org.slf4j.Logger;

public class CustomerService extends BaseService {

    private final Logger logger = LoggerFactory.getLogger(FileService.class);

    public Customer getCustomerData(String line) {
        List<String> data = this.getDataFromLine(line);

        if (data.isEmpty()) {
            this.logger.error("Error when try to split line.");
            return null;
        }

        Customer customer = new Customer(data.get(0)
                                        ,data.get(1)
                                        ,data.get(2)
                                        ,data.get(3));

        this.logger.info(customer.getResult());
        return customer;
    }
}
