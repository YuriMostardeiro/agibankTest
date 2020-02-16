package com.nt;

import com.nt.service.CustomerService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CustomerServiceTest {

    @Test
    public void customerSucessTest() {
        String sampleLine = "002ç2345675434544345çJoseçRural";
        CustomerService.getCustomerData(sampleLine);

        assertAll(
                () -> assertEquals(CustomerService.customer.getCode(), "002"),
                () -> assertEquals(CustomerService.customer.getCompanyNumber(), "2345675434544345"),
                () -> assertEquals(CustomerService.customer.getName(), "Jose"),
                () -> assertEquals(CustomerService.customer.getBusinessArea(), "Rural")
        );
    }

    @Test
    public void customerFailDelimiterTest() {
        String sampleLine = "002ç2345675434544345çJose AssunçãoçRural";
        CustomerService.getCustomerData(sampleLine);
        assertTrue(CustomerService.customer.getCustomerList().isEmpty());
    }

    @Test
    public void customerFailLineFormatTest() {
        String sampleLine = "002ççJose";
        CustomerService.getCustomerData(sampleLine);
        assertTrue(CustomerService.customer.getCustomerList().isEmpty());
    }

    @Test
    public void customerFailLineEmptyTest() {
        String sampleLine = "";
        CustomerService.getCustomerData(sampleLine);
        assertTrue(CustomerService.customer.getCustomerList().isEmpty());
    }
}
