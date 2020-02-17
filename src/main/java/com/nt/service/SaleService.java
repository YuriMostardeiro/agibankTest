package com.nt.service;

import java.util.List;

import com.nt.domain.DataInput;
import com.nt.domain.Sale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SaleService extends BaseService {

    private final Logger LOGGER = LoggerFactory.getLogger(SaleService.class);

    public void getSaleData(String row, DataInput dataInput) {
        List<String> data = getDataFromLine(row);

        if (data == null || data.isEmpty()) {
            LOGGER.error("Error when try to split row for sale.");
            return;
        }

        Sale sale = new Sale();

        double salesprice = 0;
        try {
            salesprice = sale.getSalesTotalPrice(data.get(2));
        } catch (Exception e) {
            LOGGER.error("Error when try to get sale total price.", e);
            return;
        }

        sale.setCode(data.get(0));
        sale.setSaleId(data.get(1));
        sale.setSalePrice(salesprice);
        sale.setSalesmanName(data.get(3));

        dataInput.getSaleList().add(sale);
        LOGGER.info(sale.getResult());
    }
}
