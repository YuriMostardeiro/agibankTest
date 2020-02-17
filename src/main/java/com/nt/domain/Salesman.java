package com.nt.domain;

public class Salesman {
	private String code;
	private String cpf;
	private String name;
	private double salary;

	public Salesman() {
		
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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

	public String getResult() {
		return "Customer{" +
				"code='" + code + '\'' +
				", cpf='" + cpf + '\'' +
				", name='" + name + '\'' +
				", salary='" + salary + '\'' +
				'}';
	}
}