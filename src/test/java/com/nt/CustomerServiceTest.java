package com.nt;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;


import com.nt.domain.Customer;
import com.nt.service.CustomerService;
import org.junit.jupiter.api.Test;

public class CustomerServiceTest {

    private String sampleStr1 = "002ç2345675434544345çJoseçRural";

    @Test
    public void customerTest() {
        CustomerService.getCustomerData(sampleStr1);

        assertAll(
                () -> assertEquals(CustomerService.customer.getCode(), "002"),
                () -> assertEquals(CustomerService.customer.getCompanyNumber(), "2345675434544345"),
                () -> assertEquals(CustomerService.customer.getName(), "Jose"),
                () -> assertEquals(CustomerService.customer.getBusinessArea(), "Rural")
        );
    }
}
