package com.example.bean;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;

public class Customer {

	private static final String DEFAULT_DATE_PATTERN = "dd/MM/yyyy";

	private int id;
	private String name;
	@ApiModelProperty(example = DEFAULT_DATE_PATTERN, value = "Data no formato '" + DEFAULT_DATE_PATTERN + "'. Ex: 31/01/1991")
	@JsonFormat(pattern = DEFAULT_DATE_PATTERN)
	private LocalDate birthday;
	private boolean enable;
	private Double salary;
	private Address address;
	private List<Phone> phones;

	public Customer() {

	}

	public Customer(int id, String name, LocalDate birthday, boolean enable, Double salary, Address address,
			List<Phone> phones) {
		this.id = id;
		this.name = name;
		this.birthday = birthday;
		this.enable = enable;
		this.salary = salary;
		this.address = address;
		this.phones = phones;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<Phone> getPhones() {
		return phones;
	}

	public void setPhones(List<Phone> phones) {
		this.phones = phones;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getBirthday() {
		return birthday;
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", birthday=" + birthday + ", enable=" + enable + ", salary="
				+ salary + ", address=" + address + ", phones=" + phones + "]";
	}

}
