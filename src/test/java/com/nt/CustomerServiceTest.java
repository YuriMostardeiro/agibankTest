package com.nt;

import com.nt.domain.DataInput;
import com.nt.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import static org.junit.jupiter.api.Assertions.*;

public class CustomerServiceTest {

    @InjectMocks
    private CustomerService customerService = new CustomerService();

    @InjectMocks
    private DataInput dataInput = new DataInput();

    @Test
    public void customerSucessTest() {
        String sampleLine = "002ç2345675434544345çJoseçRural";
        customerService.getCustomerData(sampleLine, dataInput);

        assertAll(
                () -> assertEquals(customerService.customer.getCode(), "002"),
                () -> assertEquals(customerService.customer.getCompanyNumber(), "2345675434544345"),
                () -> assertEquals(customerService.customer.getName(), "Jose"),
                () -> assertEquals(customerService.customer.getBusinessArea(), "Rural")
        );
    }

    @Test
    public void customerFailDelimiterTest() {
        String sampleLine = "002ç2345675434544345çJose AssunçãoçRural";
        customerService.getCustomerData(sampleLine, dataInput);
        assertEquals(customerService.customer.getName(), "Jose Assunção");
    }

    @Test
    public void customerFailLineFormatTest() {
        String sampleLine = "002ççJose";
        customerService.getCustomerData(sampleLine, dataInput);
        assertTrue(dataInput.getCustomerList().isEmpty());
    }

    @Test
    public void customerFailLineEmptyTest() {
        String sampleLine = "";
        customerService.getCustomerData(sampleLine, dataInput);
        assertTrue(dataInput.getCustomerList().isEmpty());
    }
}
