package com.nt.domain;

import java.util.ArrayList;
import java.util.List;

public class Customer {

	private String code;
	private String companyNumber;
	private String name;
	private String businessArea;
	private List<Customer> customerList = new ArrayList<Customer>();

	public Customer() {
		
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCompanyNumber() {
		return companyNumber;
	}

	public void setCompanyNumber(String companyNumber) {
		this.companyNumber = companyNumber;
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

	public List<Customer> getCustomerList() {
		return customerList;
	}

	public String getResult() {
		return "Customer{" +
				"code='" + code + '\'' +
				", companyNumber='" + companyNumber + '\'' +
				", name='" + name + '\'' +
				", businessArea='" + businessArea + '\'' +
				'}';
	}
}