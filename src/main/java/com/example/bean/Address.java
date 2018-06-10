package com.example.bean;

public class Address {

	private String city;
	private String local;
	private String zipCode;

	public Address() {
	}

	public Address(String city, String local, String zipCode) {
		super();
		this.city = city;
		this.local = local;
		this.zipCode = zipCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

}
