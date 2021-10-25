package com.solvd.banksystem.bank;

import com.solvd.banksystem.address.Address;
import com.solvd.banksystem.bank.currency.Currency;
import com.solvd.banksystem.bankoperation.BankOperation;
import com.solvd.banksystem.bankoperation.Mortgage;
import com.solvd.banksystem.human.Human;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MortgageBank extends Bank {

    private List<Mortgage> mortgages;

    public MortgageBank(String name, Address address, LocalDateTime foundedAt) {
        super(name, address, foundedAt);
    }

    public MortgageBank(String nameOfBank, Address address, LocalDateTime foundedAt, Currency usd, Currency eur, Currency rub, Currency byn) {
        super(nameOfBank, address, foundedAt, usd, eur, rub, byn);
    }

    public void setMortgages(List<Mortgage> mortgages) {
        this.mortgages = mortgages;
    }

    public List<Mortgage> getMortgages() {
        return mortgages;
    }

    @Override
    public void add(BankOperation operation) {
        if (operation == null) {
            return;
        }
        if (this.mortgages != null) {
            if (!this.mortgages.contains(operation)) {
                this.mortgages.add((Mortgage) operation);
            } else {
                System.out.println("Credit already exist.");
            }
        } else {
            this.mortgages = new ArrayList<>();
            this.mortgages.add((Mortgage) operation);
        }
    }

    @Override
    public boolean remove(BankOperation operation) {
        if (operation == null || this.mortgages == null || this.mortgages.size() == 0) {
            return false;
        }
        return this.mortgages.remove((Mortgage) operation);
    }

    @Override
    public List<BankOperation> find(Human human) {
        List<BankOperation> result = null;
        if (human == null) {
            return null;
        }
        if (this.mortgages != null && this.mortgages.size() > 0) {
            result = new ArrayList<>();
            for (BankOperation element : this.mortgages) {
                if (element.getClient().getFirstName().equals(human.getFirstName()) &&
                        element.getClient().getLastName().equals(human.getLastName())) {
                    result.add(element);
                }
            }
        }
        return result;
    }

    @Override
    public void payTax() {
        System.out.printf("%-60s%s%s", "\n", "MORTGAGE BANK TAX:", "\n");
        System.out.println(super.getName() + " bank: ");
        if (super.getUsd().getAmount() > 0) {
            double tax = super.getUsd().getAmount() * 13 / 100;
            super.getUsd().setAmount(super.getUsd().getAmount() - tax);
            System.out.println("USD tax paid: " + tax);
        }
        if (super.getUsd().getAmount() > 0) {
            double tax = super.getEur().getAmount() * 13 / 100;
            super.getEur().setAmount(super.getEur().getAmount() - tax);
            System.out.println("EURO tax paid: " + tax);
        }
        if (super.getUsd().getAmount() > 0) {
            double tax = super.getUsd().getAmount() * 13 / 100;
            super.getUsd().setAmount(super.getUsd().getAmount() - tax);
            System.out.println("RUB tax paid: " + tax);
        }
        if (super.getUsd().getAmount() > 0) {
            double tax = super.getUsd().getAmount() * 13 / 100;
            super.getUsd().setAmount(super.getUsd().getAmount() - tax);
            System.out.println("BYN tax paid: " + tax);
        }
    }

    @Override
    public void print() {
        System.out.printf("%-60s%s%s", "\n", "CREDIT BANK ININFORMATION:", "\n");
        System.out.println(super.getName() + ", located on " + super.getAddress().getCity() +
                ", " + super.getAddress().getStreet() + " " + super.getAddress().getHouseNumber() + " street, founded in " +
                super.getFoundedAt().getDayOfMonth() + "." + super.getFoundedAt().getMonth() + "." + super.getFoundedAt().getYear());
        System.out.println("Number of mortgage in bank: " + mortgages.size());
        System.out.println("Bank capital:");
        System.out.println(super.getUsd().getAmount() + " " + Currency.USD);
        System.out.println(super.getEur().getAmount() + " " + Currency.EURO);
        System.out.println(super.getRub().getAmount() + " " + Currency.RUB);
        System.out.println(super.getByn().getAmount() + " " + Currency.BYN);
    }

    @Override
    public String toString() {
        return "Class MortgageBank [name = " + getName() + ", address = " + getAddress() + ", foundedAt = "
                + getFoundedAt().getDayOfMonth() + "." + getFoundedAt().getMonth() + "."
                + getFoundedAt().getYear() + ", count = " + count + ", usd = " + getUsd() + ", eur = "
                + getEur() + ", rub = " + getRub() + ", byn = " + getByn() + ", employees = " + getEmployees()
                + ", mortgages = " + mortgages + "]";
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (object == null || object.getClass() != this.getClass()) {
            return false;
        }
        MortgageBank mortgageBank = (MortgageBank) object;
        return getUsd().equals(mortgageBank.getUsd()) && getEur().equals(mortgageBank.getEur()) && getRub().equals(mortgageBank.getRub())
                && getByn().equals(mortgageBank.getByn()) && getEmployees().equals(mortgageBank.getEmployees())
                && (getName() != null && getName().equals(mortgageBank.getName()))
                && getAddress().equals(mortgageBank.getAddress()) && getFoundedAt().equals(mortgageBank.getFoundedAt())
                && mortgages.equals(mortgageBank.getMortgages());
    }

    @Override
    public int hashCode() {
        return Objects.hash(count, getUsd(), getEur(), getRub(), getByn(), getEmployees(), getName(), getAddress(), getFoundedAt(), mortgages);
    }
}
