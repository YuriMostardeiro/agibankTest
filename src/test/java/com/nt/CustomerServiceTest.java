package com.nt;

import com.nt.domain.DataInput;
import com.nt.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import static org.junit.jupiter.api.Assertions.*;

public class CustomerServiceTest {

    @InjectMocks
    private CustomerService customerService = new CustomerService();

    @Test
    public void customerSucessTest() {
        DataInput dataInput = new DataInput();
        String sampleLine = "002ç2345675434544345çJoseçRural";
        customerService.getCustomerData(sampleLine, dataInput);

        assertAll(
                () -> assertEquals(dataInput.getCustomerList().get(0).getCode(), "002"),
                () -> assertEquals(dataInput.getCustomerList().get(0).getCompanyNumber(), "2345675434544345"),
                () -> assertEquals(dataInput.getCustomerList().get(0).getName(), "Jose"),
                () -> assertEquals(dataInput.getCustomerList().get(0).getBusinessArea(), "Rural")
        );
    }

    @Test
    public void customerFailDelimiterTest() {
        DataInput dataInput = new DataInput();
        String sampleLine = "002ç2345675434544345çJose AssunçãoçRural";
        customerService.getCustomerData(sampleLine, dataInput);
        assertEquals(dataInput.getCustomerList().get(0).getName(), "Jose Assunção");
    }

    @Test
    public void customerFailLineFormatTest() {
        DataInput dataInput = new DataInput();
        String sampleLine = "002ççJose";
        customerService.getCustomerData(sampleLine, dataInput);
        assertTrue(dataInput.getCustomerList().isEmpty());
    }

    @Test
    public void customerFailLineEmptyTest() {
        DataInput dataInput = new DataInput();
        String sampleLine = "";
        customerService.getCustomerData(sampleLine, dataInput);
        assertTrue(dataInput.getCustomerList().isEmpty());
    }
}
