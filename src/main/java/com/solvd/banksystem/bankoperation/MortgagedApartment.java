package com.solvd.banksystem.bankoperation;

import com.solvd.banksystem.address.Address;
import com.solvd.banksystem.bank.currency.Currency;
import java.util.Objects;

public class MortgagedApartment extends Address {

    private int roomNumber;
    private Currency price;

    public MortgagedApartment(Address address, int roomNumber, Currency price) {
        super(address.getCity(), address.getStreet(), address.getHouseNumber());
        this.roomNumber = roomNumber;
        this.price = price;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setPrice(Currency price) {
        this.price = price;
    }

    public Currency getPrice() {
        return price;
    }

    @Override
    public void print() {
        System.out.println("Apartment address: " + super.getCity() + ", " + super.getStreet()
                + " " + super.getHouseNumber() + ", ap." + roomNumber);
        System.out.println("Collateral value of an apartment: "
                + (price.getAmount() * 30 / 100) + " " + price.getCurrencyType() + ".");
    }

    @Override
    public String toString() {
        return "Class MortgagedApartment [city = " + getCity() + ", street = "
                + getStreet() + ", houseNumber = " + getHouseNumber()
                + ", roomNumber = " + roomNumber + ", price = " + price + "]";
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (object == null || object.getClass() != this.getClass()) {
            return false;
        }
        MortgagedApartment mortgagedApartment = (MortgagedApartment) object;
        return getHouseNumber() == mortgagedApartment.getHouseNumber()
                && (getCity() != null && getCity().equals(mortgagedApartment.getCity()))
                && (getStreet() != null && getStreet().equals(mortgagedApartment.getStreet()))
                && roomNumber == mortgagedApartment.getRoomNumber() && price.equals(mortgagedApartment.getPrice());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCity(), getStreet(), getHouseNumber(), roomNumber, price);
    }
}
