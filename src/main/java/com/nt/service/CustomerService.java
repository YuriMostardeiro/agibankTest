package com.nt.service;

import com.nt.domain.Customer;
import org.slf4j.LoggerFactory;

import java.util.List;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService extends BaseService {

    private static final Logger logger = LoggerFactory.getLogger(FileService.class);
    public static final Customer customer = new Customer();

    static void getCustomerData(String line) {
        List<String> data = getDataFromLine(line);

        if (data.isEmpty()) {
            logger.error("Error when try to split line for customer.");
            return;
        }

        customer.setCode(data.get(0));
        customer.setCompanyNumber(data.get(1));
        customer.setName(data.get(2));
        customer.setBusinessArea(data.get(3));

        customer.getCustomerList().add(customer);
        logger.info(customer.getResult());
    }
}
