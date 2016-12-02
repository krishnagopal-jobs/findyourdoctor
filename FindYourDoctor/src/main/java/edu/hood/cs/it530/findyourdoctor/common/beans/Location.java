package edu.hood.cs.it530.findyourdoctor.common.beans;

public class Location {
	
	private int locationId;
	
	private String state;
	
	private String city;
	
	private String street;
	
	private String suite_number;
	
	private int zipCode;
	
	private int phoneNumber;

	public int getLocationId() {
		return locationId;
	}

	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getSuite_number() {
		return suite_number;
	}

	public void setSuite_number(String suite_number) {
		this.suite_number = suite_number;
	}

	public int getZipCode() {
		return zipCode;
	}

	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}

	public int getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

}
