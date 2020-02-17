package com.nt;

import com.nt.domain.DataInput;
import com.nt.service.SalesmanService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import static org.junit.jupiter.api.Assertions.*;

public class SalesmanServiceTest {

    @InjectMocks
    private SalesmanService salesmanService = new SalesmanService();

    @Test
    public void salesmanSucessTest() {
        DataInput dataInput = new DataInput();
        String sampleLine = "001ç1234567891234çPedroç50000";
        salesmanService.getSalesmanData(sampleLine, dataInput);

        assertAll(
                () -> assertEquals(dataInput.getSalesmanList().get(0).getCode(), "001"),
                () -> assertEquals(dataInput.getSalesmanList().get(0).getCpf(), "1234567891234"),
                () -> assertEquals(dataInput.getSalesmanList().get(0).getName(), "Pedro"),
                () -> assertEquals(dataInput.getSalesmanList().get(0).getSalary(), Double.parseDouble("50000"))
        );
    }

    @Test
    public void salesmanFailDelimiterTest() {
        DataInput dataInput = new DataInput();
        String sampleLine = "001ç1234567891234çPedro Assunçaoç50000";
        salesmanService.getSalesmanData(sampleLine, dataInput);

        assertEquals(dataInput.getSalesmanList().get(0).getName(), "Pedro Assunçao");
    }

    @Test
    public void salesmanFailLineFormatTest() {
        DataInput dataInput = new DataInput();
        String sampleLine = "001ç1250000";
        salesmanService.getSalesmanData(sampleLine, dataInput);
        assertTrue(dataInput.getSalesmanList().isEmpty());
    }

    @Test
    public void salesmanFailLineEmptyTest() {
        DataInput dataInput = new DataInput();
        String sampleLine = "";
        salesmanService.getSalesmanData(sampleLine, dataInput);
        assertTrue(dataInput.getSalesmanList().isEmpty());
    }

    @Test
    public void salesmanFailSalaryTest() {
        DataInput dataInput = new DataInput();
        String sampleLine = "001ç1234567891234çPedroçfivedollars";
        salesmanService.getSalesmanData(sampleLine, dataInput);
        assertTrue(dataInput.getSalesmanList().isEmpty());
    }
}
