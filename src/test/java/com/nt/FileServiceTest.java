package com.nt;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import com.nt.domain.Customer;
import com.nt.domain.Sale;
import com.nt.domain.Salesman;

import com.nt.util.FileUtil;

import org.junit.jupiter.api.Test;

public class FileServiceTest {

	private Sale sale = new Sale();
	private Salesman salesman = new Salesman();
	private Customer customer = new Customer();
    
    private String sampleStr1 = "001ç1234567891234çPedroç50000";
	private String sampleStr2 = "002ç2345675434544345çJoseçRural";
	private String sampleStr3 = "003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çDiego";	
	
 /*
	@Before
	public void setup() {
		sale = new Sale();
	}*/

	@Test
	public void salesmanTest() {
		sale = new Sale();
		List<String> fileSimulation = new ArrayList<String>();
		fileSimulation.add(FileUtil.replaceAscIIDelimiter(sampleStr1));

		salesman.getSalesmansFromList(fileSimulation);
		
		assertEquals(salesman.getSalesmans().get(0).getCpf() , "1234567891234");
		assertEquals(salesman.getSalesmans().get(0).getName() , "Pedro");
		assertEquals(salesman.getSalesmans().get(0).getSalary(), 50000, 0);
	}

	@Test
	public void customerTest() {
		sale = new Sale();
		List<String> fileSimulation = new ArrayList<String>();
		fileSimulation.add(FileUtil.replaceAscIIDelimiter(sampleStr2));

		customer.getCustomersFromList(fileSimulation);
		
		assertEquals(customer.getCustomers().get(0).getCnpj() , "2345675434544345");
		assertEquals(customer.getCustomers().get(0).getName() , "Jose");
		assertEquals(customer.getCustomers().get(0).getBusinessArea(), "Rural");
	}

	@Test
	public void salesTest() {
		sale = new Sale();
		List<String> fileSimulation = new ArrayList<String>();
		fileSimulation.add(FileUtil.replaceAscIIDelimiter(sampleStr3));

		sale.getSalesFromList(fileSimulation);
		
		assertEquals(sale.getSales().get(0).getSaleId() , "10");
		assertEquals(sale.getSales().get(0).getSalesman(), "Diego");
	}
}
