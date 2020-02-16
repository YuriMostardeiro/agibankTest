package com.nt.domain;

public class OutputFile {
    private int totalOfCustomer;
    private int totalOfSalesman;
    private String mostExpensiveSale;
    private String worstSalesman;

    public int getTotalOfClient() {
        return totalOfCustomer;
    }

    public void setTotalOfCustomer(int totalOfCustomer) {
        this.totalOfCustomer = totalOfCustomer;
    }

    public int getTotalOfSalesman() {
        return totalOfSalesman;
    }

    public void setTotalOfSalesman(int totalOfSalesman) {
        this.totalOfSalesman = totalOfSalesman;
    }

    public String getMostExpensiveSale() {
        return mostExpensiveSale;
    }

    public void setMostExpensiveSale(String mostExpensiveSale) {
        this.mostExpensiveSale = mostExpensiveSale;
    }

    public String getWorstSalesman() {
        return worstSalesman;
    }

    public void setWorstSalesman(String worstSalesman) {
        this.worstSalesman = worstSalesman;
    }

    public String getOutputData() {
        return "Amount of clients in the input file: " + totalOfCustomer +
                "\r\nAmount of salesman in the input file:" + totalOfSalesman +
                "\r\nId of the most expensive sale: " + mostExpensiveSale +
                "\r\nWorst salesman ever: " + worstSalesman;
    }
}
