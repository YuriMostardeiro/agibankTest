package com.nt.service;

import java.util.List;
import com.nt.domain.OutputFile;
import com.nt.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class FileService {

	private final Logger logger = LoggerFactory.getLogger(FileService.class);
	private final OutputFile outputFile = new OutputFile();
	private final CustomerService customerService = new CustomerService();
	private final SalesmanService salesmanService = new SalesmanService();
	private final SaleService saleService = new SaleService();

	public void createOutputFile(List<String> listFormatted) {

		for (String line : listFormatted) {

			switch (line.substring(0, 3)) {
				case "001":
					salesmanService.getSalesmanData(line);
					break;
				case "002":
					customerService.getCustomerData(line);
					break;
				case "003":
					saleService.getSaleData(line);
					break;
				default:
					break;
			}
		}

		outputFile.setTotalOfCustomer(customerService.customer.getCustomerList().size());
		outputFile.setTotalOfSalesman(salesmanService.salesman.getSalesmanList().size());
		outputFile.setMostExpensiveSale(saleService.sale.getIdOfTheMostExpensiveSale());
		outputFile.setWorstSalesman(saleService.sale.getWorstSalesmanEver());

		if (!FileUtil.createOutputFile(outputFile)) {
			this.logger.error("Failed to generate output file");
		}
	}
}