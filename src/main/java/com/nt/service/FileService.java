package com.nt.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.nt.domain.OutputFile;
import com.nt.util.FileUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class FileService {

	private final Logger logger = LoggerFactory.getLogger(FileService.class);
	private final CustomerService customerService = new CustomerService();
	private final SalesmanService salesmanService = new SalesmanService();
	private final SaleService saleService = new SaleService();

	public void processFile(String file) throws IOException {
		List<String> listFormatted = getFileRows(file);
		OutputFile outputFile = fillOutputFile(listFormatted);
		createOutputFile(outputFile);
	}

	private List<String> getFileRows(String file) throws IOException {
		List<String> listFileRow = new ArrayList<>();
		BufferedReader buffered = new BufferedReader(new FileReader(file));
		String row = "";

		while ((row = buffered.readLine()) != null) {
			listFileRow.add(row);
		}
		buffered.close();

		return listFileRow;
	}

	public OutputFile fillOutputFile(List<String> listFormatted) {

		OutputFile outputFile = new OutputFile();
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

		return outputFile;
	}

	private void createOutputFile(OutputFile outputFile) {
		if (!FileUtil.createOutputFile(outputFile)) {
			this.logger.error("Failed to generate output file");
		}
	}
}