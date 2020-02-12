package com.nt.domain;

import java.util.ArrayList;
import java.util.List;


public class Customer {
	private String cnpj;
	private String name;
	private String businessArea;
	private List<com.nt.domain.Customer> customers = new ArrayList<com.nt.domain.Customer>();

	public Customer(String cnpj, String name, String businessarea) {
		this.cnpj = cnpj;
		this.name = name;
		this.businessArea = businessarea;
	}

	public Customer() {
		
	}

	public void getCustomersFromList(List<String> listFormated) {
		for (String customer : listFormated) {
			if (customer.startsWith("002;")) {
				String aux[] = customer.split(";");
				customers.add(new Customer(aux[1], aux[2], aux[3]));
			}
		}
	}

	public int amountOfClients() {
		return customers.size();
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBusinessArea() {
		return businessArea;
	}

	public void setBusinessArea(String businessArea) {
		this.businessArea = businessArea;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}
}