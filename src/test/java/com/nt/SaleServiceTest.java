package com.nt;

import com.nt.domain.DataInput;
import com.nt.service.SaleService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SaleServiceTest {

    @InjectMocks
    private SaleService saleService = new SaleService();

    @Test
    public void saleSucessTest() {
        DataInput dataInput = new DataInput();
        String sampleLine = "003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çDiego";
        saleService.getSaleData(sampleLine, dataInput);

        assertAll(
                () -> assertEquals(dataInput.getSaleList().get(0).getCode(), "003"),
                () -> assertEquals(dataInput.getSaleList().get(0).getSaleId(), "10"),
                () -> assertEquals(dataInput.getSaleList().get(0).getSalesmanName(),"Diego")
        );
    }

    @Test
    public void saleItemSuccessTest() {
        DataInput dataInput = new DataInput();
        String sampleLine = "003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çDiego";
        List<String> data = saleService.getDataFromLine(sampleLine);

        saleService.getSaleData(sampleLine, dataInput);
        assertAll(
                () -> assertEquals(data.get(2), "[1-10-100,2-30-2.50,3-40-3.10]"),
                () -> assertEquals(dataInput.getSaleList().get(0).getSalesTotalPrice(data.get(2)), Double.parseDouble("1199"))
        );
    }

    @Test
    public void saleFailDelimiterTest() {
        DataInput dataInput = new DataInput();
        String sampleLine = "003ç10ç[1-10-100,ç2-30-2.ç50,3-40-3.10]çDiego";
        saleService.getSaleData(sampleLine, dataInput);
        assertTrue(dataInput.getSaleList().isEmpty());

    }

    @Test
    public void saleFailLineFormatTest() {
        DataInput dataInput = new DataInput();
        String sampleLine = "003ç10ç[1-10-100";
        saleService.getSaleData(sampleLine, dataInput);
        assertTrue(dataInput.getSaleList().isEmpty());
    }

    @Test
    public void saleFailLineEmptyTest() {
        DataInput dataInput = new DataInput();
        String sampleLine = "";
        saleService.getSaleData(sampleLine, dataInput);
        assertTrue(dataInput.getSaleList().isEmpty());
    }

}
