package com.nt;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.nt.domain.DataInput;
import com.nt.domain.OutputFile;
import com.nt.service.CustomerService;
import com.nt.service.ProcessService;
import com.nt.service.SaleService;
import com.nt.service.SalesmanService;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

public class ProcessServiceTest {

    @InjectMocks
    private CustomerService customerService = new CustomerService();

    @InjectMocks
    private SalesmanService salesmanService = new SalesmanService();

    @InjectMocks
    private SaleService saleService = new SaleService();

    private ProcessService processService = new ProcessService(salesmanService, customerService, saleService);

    @Test
    public void outputTest() {
        DataInput dataInput = new DataInput();

        salesmanService.getSalesmanData("001ç1234567891234çPedro Mendonçaç50000", dataInput);
        salesmanService.getSalesmanData("001ç3245678865434çPaulo Marçaisç40000.99", dataInput);

        customerService.getCustomerData("002ç2345675434544345çJose da ÇilvaçRural", dataInput);
        customerService.getCustomerData("002ç2345675433444345çEduardo PereiraçRural", dataInput);

        saleService.getSaleData("003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çPedro Mendonça", dataInput);
        saleService.getSaleData("003ç08ç[1-34-150,2-33-50.50,3-40-10.10]çPaulo Marçais", dataInput);


        OutputFile outputFile = processService.getOutputData(dataInput);

        assertAll(
                () -> assertEquals(outputFile.getTotalOfClient(),2),
                () -> assertEquals(outputFile.getTotalOfSalesman(),2),
                () -> assertEquals(outputFile.getMostExpensiveSale(),"08"),
                () -> assertEquals(outputFile.getWorstSalesman(),"Pedro Mendonça")
        );
    }
}