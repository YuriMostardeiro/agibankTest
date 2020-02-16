package com.nt.domain;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class Sale {
	private String code;
	private String saleId;
	private double salePrice;
	private String salesmanName;
	private List<Sale> sales = new ArrayList<Sale>();

	public Sale(String code, String saleid, double saleprice, String salemanName) {
		this.code = code;
		this.saleId = saleid;
		this.salePrice = saleprice;
		this.salesmanName = salemanName;
	}

	public Sale() {
		
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int amoutOfSales() {
		return sales.size();
	}

	public double getSalesTotalPrice(String sale) {
		double price = 0;
		String splitincoma[] = sale.split(",");
		for (int i = 0; i < splitincoma.length; i++) {
			String splitinindent[] = splitincoma[i].split("-");
			if (splitinindent.length > 1){
				price = price + Double.parseDouble(splitinindent[1].replace("[", ""))
						* Double.parseDouble(splitinindent[2].replace("]", ""));
			}
		}
		return price;
	}

	public String getIdOfTheMostExpensiveSale() {
		sales.sort(Comparator.comparing(Sale::getSalePrice).reversed());

		if (sales.stream().count() > 0){
			return sales.get(0).getSaleId();
		}
		else{
			return "no sales";
		}
	}

	public String getWorstSalesmanEver() {
		sales.sort(Comparator.comparing(Sale::getSalePrice));

		if (sales.stream().count() > 0){
			return sales.get(0).getSalesmanName();
		}
		else{
			return "no salesman";
		}
	}

	public String getSaleId() {
		return saleId;
	}

	public void setSaleId(String saleId) {
		this.saleId = saleId;
	}

	public double getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(double salePrice) {
		this.salePrice = salePrice;
	}

	public String getSalesmanName() {
		return salesmanName;
	}

	public void setSalesmanName(String salesmanName) {
		this.salesmanName = salesmanName;
	}

	public List<Sale> getSalesList() {
		return sales;
	}

	public String getResult() {
		return "Customer{" +
				"code='" + code + '\'' +
				", saleId='" + saleId + '\'' +
				", salePrice='" + salePrice + '\'' +
				", salesman='" + salesmanName + '\'' +
				'}';
	}
}