package com.nt.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.nt.domain.DataInput;
import com.nt.domain.OutputFile;
import com.nt.util.FileUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileService {

	private final Logger logger = LoggerFactory.getLogger(FileService.class);

	@Autowired
	private CustomerService customerService;

	@Autowired
	private SalesmanService salesmanService;

	@Autowired
	private SaleService saleService = new SaleService();

	public void processFile(String file) throws IOException {
		List<String> listFormatted = new ArrayList<>();
		listFormatted = getFileRows(file);
		OutputFile outputFile = fillOutputFile(listFormatted);
		FileUtil.createOutputFile(outputFile);
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

		DataInput dataInput = new DataInput();
		OutputFile outputFile = new OutputFile();

		listFormatted.stream().forEach((row) ->{
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
		});

		outputFile.setTotalOfCustomer(dataInput.getCustomerList().size());
		outputFile.setTotalOfSalesman(dataInput.getSalesmanList().size());
		outputFile.setMostExpensiveSale(dataInput.getIdOfTheMostExpensiveSale());
		outputFile.setWorstSalesman(dataInput.getWorstSalesmanEver());

		return outputFile;
	}
}