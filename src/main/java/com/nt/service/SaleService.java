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
        verifyDataAndAddSale(dataInput, data);
    }

    private void verifyDataAndAddSale(DataInput dataInput, List<String> data) {
        if (verifyData(data)) {
            addSaleToList(dataInput, data);
        }
    }

    private boolean verifyData(List<String> data) {
        if (data == null || data.isEmpty()) {
            LOGGER.error("Error to split row for sale.");
            return false;
        }
        return true;
    }

    private void addSaleToList(DataInput dataInput, List<String> data) {
        dataInput.getSaleList().add(setSaleData(data));
    }

    private Sale setSaleData(List<String> data) {
        Sale sale = new Sale();

        sale.setCode(data.get(0));
        sale.setSaleId(data.get(1));
        sale.setSalePrice(sale.getSalesTotalPrice(data.get(2)));
        sale.setSalesmanName(data.get(3));

        LOGGER.info(sale.getResult());
        return sale;
    }
}
