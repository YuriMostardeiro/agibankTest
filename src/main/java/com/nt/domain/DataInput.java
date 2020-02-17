package com.nt.domain;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class DataInput {

    private List<Customer> customerList = new ArrayList<Customer>();
    private List<Salesman> salesmanList = new ArrayList<Salesman>();
    private List<Sale> saleList = new ArrayList<Sale>();

    public List<Customer> getCustomerList() {
        return customerList;
    }

    public List<Salesman> getSalesmanList() {
        return salesmanList;
    }

    public List<Sale> getSaleList() {
        return saleList;
    }

    public String getIdOfTheMostExpensiveSale() {
        saleList.sort(Comparator.comparing(Sale::getSalePrice).reversed());

        if (saleList.stream().count() > 0){
            return saleList.get(0).getSaleId();
        }
        else{
            return "no sales";
        }
    }

    public String getWorstSalesmanEver() {
        saleList.sort(Comparator.comparing(Sale::getSalePrice));

        if (saleList.stream().count() > 0){
            return saleList.get(0).getSalesmanName();
        }
        else{
            return "no salesman";
        }
    }
}