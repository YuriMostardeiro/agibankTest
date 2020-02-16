package com.nt;

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
        String sampleLine = "003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çDiego";
        saleService.getSaleData(sampleLine);

        assertAll(
                () -> assertEquals(saleService.sale.getCode(), "003"),
                () -> assertEquals(saleService.sale.getSaleId(), "10"),
                () -> assertEquals(saleService.sale.getSalesmanName(),"Diego")
        );
    }

    @Test
    public void saleItemSuccessTest() {
        String sampleLine = "003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çDiego";
        List<String> data = saleService.getDataFromLine(sampleLine);

        saleService.getSaleData(sampleLine);
        assertAll(
                () -> assertEquals(data.get(2), "[1-10-100,2-30-2.50,3-40-3.10]"),
                () -> assertEquals(saleService.sale.getSalesTotalPrice(data.get(2)), Double.parseDouble("1199"))
        );
    }

    @Test
    public void saleFailDelimiterTest() {
        String sampleLine = "003ç10ç[1-10-100ç,2-30-2.50,3-40-3.10]çDiego";
        saleService.getSaleData(sampleLine);
        assertTrue(saleService.sale.getSalesList().isEmpty());
    }

    @Test
    public void saleFailLineFormatTest() {
        String sampleLine = "003ç10ç[1-10-100";
        saleService.getSaleData(sampleLine);
        assertTrue(saleService.sale.getSalesList().isEmpty());
    }

    @Test
    public void saleFailLineEmptyTest() {
        String sampleLine = "";
        saleService.getSaleData(sampleLine);
        assertTrue(saleService.sale.getSalesList().isEmpty());
    }

}
