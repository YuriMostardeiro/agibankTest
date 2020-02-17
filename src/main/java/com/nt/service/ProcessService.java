package com.nt.service;

import com.nt.domain.DataInput;
import com.nt.domain.OutputFile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProcessService {

    private SalesmanService salesmanService;
    private CustomerService customerService;
    private SaleService saleService;

    @Autowired
    public ProcessService(SalesmanService salesmanService, CustomerService customerService, SaleService saleService) {
        this.salesmanService = salesmanService;
        this.customerService = customerService;
        this.saleService = saleService;
    }

    public void processRow(String row, DataInput dataInput) {
        switch (row.substring(0, 3)) {
            case "001":
                salesmanService.getSalesmanData(row, dataInput);
                break;
            case "002":
                customerService.getCustomerData(row, dataInput);
                break;
            case "003":
                saleService.getSaleData(row, dataInput);
                break;
            default:
                break;
        }
    }

    public OutputFile getOutputData(DataInput dataInput) {
        OutputFile outputFile = new OutputFile();
        outputFile.setTotalOfCustomer(dataInput.getCustomerList().size());
        outputFile.setTotalOfSalesman(dataInput.getSalesmanList().size());
        outputFile.setMostExpensiveSale(dataInput.getIdOfTheMostExpensiveSale());
        outputFile.setWorstSalesman(dataInput.getWorstSalesmanEver());

        return outputFile;
    }
}