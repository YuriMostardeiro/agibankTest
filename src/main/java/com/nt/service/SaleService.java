package com.nt.service;

import com.nt.domain.DataInput;
import com.nt.domain.Sale;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleService extends BaseService {

    private final Logger logger = LoggerFactory.getLogger(FileService.class);
    public final Sale sale = new Sale();

    public void getSaleData(String row, DataInput dataInput) {
        List<String> data = getDataFromLine(row);

        if (data == null || data.isEmpty()) {
            logger.error("Error when try to split row for sale.");
            return;
        }

        double salesprice = 0;
        try{
            salesprice = sale.getSalesTotalPrice(data.get(2));
        }
        catch(Exception e){
            logger.error("Error when try to get sale total price.", e);
            return;
        }

        sale.setCode(data.get(0));
        sale.setSaleId(data.get(1));
        sale.setSalePrice(salesprice);
        sale.setSalesmanName(data.get(3));

        dataInput.getSaleList().add(new Sale(sale.getCode(), sale.getSaleId(), sale.getSalePrice(), sale.getSalesmanName()));
        logger.info(sale.getResult());
    }
}
