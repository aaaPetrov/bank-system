package com.solvd.banksystem.address;

import com.solvd.banksystem.print.Printable;
import java.util.Objects;

public class Address implements Printable {

    private String city;
    private String street;
    private int houseNumber;

    public Address(String city, String street, int houseNumber) {
        this.houseNumber = houseNumber;
        this.street = street;
        this.city = city;
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

    public int getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }

    @Override
    public void print() {
        System.out.println("Address: " + city + ", " + street + " " + houseNumber);
    }

    @Override
    public String toString() {
        return "Class Address [city = " + city + ", street = " +
                street + ", houseNumber = " + houseNumber + "]";
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (object == null || object.getClass() != this.getClass()) {
            return false;
        }
        Address address = (Address) object;
        return houseNumber == address.getHouseNumber() &&
                (city != null && city.equals(address.getCity()))
                && (street != null && street.equals(address.getStreet()));
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, street, houseNumber);
    }
}
