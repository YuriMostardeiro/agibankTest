package com.nt;

import com.nt.service.SalesmanService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import static org.junit.jupiter.api.Assertions.*;

public class SalesmanServiceTest {

    @InjectMocks
    private SalesmanService salesmanService = new SalesmanService();

    @Test
    public void salesmanSucessTest() {
        String sampleLine = "001ç1234567891234çPedroç50000";
        salesmanService.getSalesmanData(sampleLine);

        assertAll(
                () -> assertEquals(salesmanService.salesman.getCode(), "001"),
                () -> assertEquals(salesmanService.salesman.getCpf(), "1234567891234"),
                () -> assertEquals(salesmanService.salesman.getName(), "Pedro"),
                () -> assertEquals(salesmanService.salesman.getSalary(), Double.parseDouble("50000"))
        );
    }

    @Test
    public void salesmanFailDelimiterTest() {

        String sampleLine = "001ç1234567891234çPedro Assunçaoç50000";
        salesmanService.getSalesmanData(sampleLine);
        assertTrue(salesmanService.salesman.getSalesmanList().isEmpty());
    }

    @Test
    public void salesmanFailLineFormatTest() {
        String sampleLine = "001ç1250000";
        salesmanService.getSalesmanData(sampleLine);
        assertTrue(salesmanService.salesman.getSalesmanList().isEmpty());
    }

    @Test
    public void salesmanFailLineEmptyTest() {
        String sampleLine = "";
        salesmanService.getSalesmanData(sampleLine);
        assertTrue(salesmanService.salesman.getSalesmanList().isEmpty());
    }

    @Test
    public void salesmanFailSalaryTest() {
        String sampleLine = "001ç1234567891234çPedroçfivedollars";
        salesmanService.getSalesmanData(sampleLine);
        assertTrue(salesmanService.salesman.getSalesmanList().isEmpty());
    }
}
