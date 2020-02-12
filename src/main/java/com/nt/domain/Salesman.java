package com.nt.domain;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


public class Salesman {
	private String cpf;
	private String name;
	private double salary;
	private List<Salesman> salesmans = new ArrayList<Salesman>();
    
	public Salesman(String cpf, String name, double salary) {
		this.cpf = cpf;
		this.name = name;
		this.salary = salary;
	}

	public Salesman() {
		
	}
	
	public void getSalesmansFromList(List<String> listFormated) {
		for (String salesman : listFormated) {
			if (salesman.startsWith("001;")) {
				String aux[] = salesman.split(";");
				salesmans.add(new Salesman(aux[1], aux[2], Double.parseDouble(aux[3])));
			}
		}
	}

	public int amountOfSalesman() {
		return salesmans.size();
	}

	public List<Salesman> getSalesmans() {
		return salesmans;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public void setSalesmans(List<Salesman> salesmans) {
		this.salesmans = salesmans;
	}
}