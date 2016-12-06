package edu.hood.cs.it530.findyourdoctor.common.beans;

public class Location {

    private int locationId;

    private String state;

    private String city;

    private String street;

    private String suiteNumber;

    private int zipCode;

    private long phoneNumber;

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

    public String getSuiteNumber() {
        return suiteNumber == null ? "" : suiteNumber;
    }

    public void setSuiteNumber(String suiteNumber) {
        this.suiteNumber = suiteNumber;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Location [locationId=");
        builder.append(locationId);
        builder.append(", state=");
        builder.append(state);
        builder.append(", city=");
        builder.append(city);
        builder.append(", street=");
        builder.append(street);
        builder.append(", suite_number=");
        builder.append(suiteNumber);
        builder.append(", zipCode=");
        builder.append(zipCode);
        builder.append(", phoneNumber=");
        builder.append(phoneNumber);
        builder.append("]");
        return builder.toString();
    }

}
