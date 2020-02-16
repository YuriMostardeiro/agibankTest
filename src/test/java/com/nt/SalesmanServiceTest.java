package com.nt;

import com.nt.service.CustomerService;
import com.nt.service.SalesmanService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SalesmanServiceTest {

    @Test
    public void salesmanSucessTest() {
        String sampleLine = "001ç1234567891234çPedroç50000";
        SalesmanService.getSalesmanData(sampleLine);

        assertAll(
                () -> assertEquals(SalesmanService.salesman.getCode(), "001"),
                () -> assertEquals(SalesmanService.salesman.getCpf(), "1234567891234"),
                () -> assertEquals(SalesmanService.salesman.getName(), "Pedro"),
                () -> assertEquals(SalesmanService.salesman.getSalary(), Double.parseDouble("50000"))
        );
    }

    @Test
    public void salesmanFailDelimiterTest() {
        String sampleLine = "001ç1234567891234çPedro Assunçaoç50000";
        SalesmanService.getSalesmanData(sampleLine);
        assertTrue(SalesmanService.salesman.getSalesmanList().isEmpty());
    }

    @Test
    public void salesmanFailLineFormatTest() {
        String sampleLine = "001ç1250000";
        SalesmanService.getSalesmanData(sampleLine);
        assertTrue(SalesmanService.salesman.getSalesmanList().isEmpty());
    }

    @Test
    public void salesmanFailLineEmptyTest() {
        String sampleLine = "";
        SalesmanService.getSalesmanData(sampleLine);
        assertTrue(SalesmanService.salesman.getSalesmanList().isEmpty());
    }
}
