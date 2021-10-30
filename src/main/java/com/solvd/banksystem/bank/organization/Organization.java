package com.solvd.banksystem.bank.organization;

import com.solvd.banksystem.address.Address;
import com.solvd.banksystem.print.Printable;
import java.time.LocalDateTime;
import java.util.Objects;

public abstract class Organization implements TaxPayable, Printable {

    private String name;
    private Address address;
    private LocalDateTime foundedAt;

    public Organization(String name, Address address, LocalDateTime foundedAt) {
        this.name = name;
        this.address = address;
        this.foundedAt = foundedAt;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Address getAddress() {
        return address;
    }

    public void setFoundedAt(LocalDateTime foundedAt) {
        this.foundedAt = foundedAt;
    }

    public LocalDateTime getFoundedAt() {
        return foundedAt;
    }

    @Override
    public void print() {
        System.out.println("Organization name: " + name);
        System.out.println("Located on " + address.getCity() +
                ", " + address.getStreet() + " " + address.getHouseNumber() + " street.");
        System.out.println("Founded at " +
                foundedAt.getDayOfMonth() + "." + foundedAt.getMonth() + "." + foundedAt.getYear());
    }

    @Override
    public String toString() {
        return "Class Organization [name = " + name + ", address = " + address + ", foundedAt = "
                + foundedAt.getDayOfMonth() + "." + foundedAt.getMonth() + "."
                + foundedAt.getYear() + "]";
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (object == null || object.getClass() != this.getClass()) {
            return false;
        }
        Organization organization = (Organization) object;
        return address.equals(organization.getAddress()) && foundedAt.equals(organization.getFoundedAt())
                && (name != null && name.equals(organization.getName()));
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, address, foundedAt);
    }
}
