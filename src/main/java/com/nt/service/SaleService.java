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

        Sale sale = setSaleData(data);

        addSaleToList(dataInput, sale);
    }

    private Sale setSaleData(List<String> data) {
        Sale sale = new Sale();

        sale.setCode(data.get(0));
        sale.setSaleId(data.get(1));
        sale.setSalePrice(getCalculatedSalePrice(sale, data));
        sale.setSalesmanName(data.get(3));

        LOGGER.info(sale.getResult());
        return sale;
    }

    private double getCalculatedSalePrice(Sale sale, List<String> data) {
        double salesPrice = 0;
        salesPrice = sale.getSalesTotalPrice(data.get(2));
        return salesPrice;
    }

    private void addSaleToList(DataInput dataInput, Sale sale) {
        if (sale != null) {
            dataInput.getSaleList().add(sale);
        }
    }
}
