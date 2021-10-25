package com.solvd.banksystem.bank;

import com.solvd.banksystem.address.Address;
import com.solvd.banksystem.bank.currency.Currency;
import com.solvd.banksystem.bank.employee.Employee;
import com.solvd.banksystem.bank.organization.Organization;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public abstract class Bank extends Organization implements Exchangable, Findable, Operationable {

    public static int count = 0;

    private Currency usd;
    private Currency eur;
    private Currency rub;
    private Currency byn;
    private List<Employee> employees;

    public Bank(String name, Address address, LocalDateTime foundedAt) {
        super(name, address, foundedAt);
        this.count++;
    }

    public Bank(String nameOfBank, Address address, LocalDateTime foundedAt, Currency usd, Currency eur, Currency rub, Currency byn) {
        this(nameOfBank, address, foundedAt);
        this.usd = usd;
        this.eur = eur;
        this.rub = rub;
        this.byn = byn;
    }

    public void setUsd(Currency usd) {
        this.usd = usd;
    }

    public Currency getUsd() {
        return usd;
    }

    public void setEur(Currency eur) {
        this.eur = eur;
    }

    public Currency getEur() {
        return eur;
    }

    public void setRub(Currency rub) {
        this.rub = rub;
    }

    public Currency getRub() {
        return rub;
    }

    public void setByn(Currency byn) {
        this.byn = byn;
    }

    public Currency getByn() {
        return byn;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    /*public void addEmployee(Employee employee) {
        if (employee == null) {
            return;
        }
        if (this.employees == null) {
            this.employees = new ArrayList<>();
            this.employees.add(employee);
        } else {
            this.employees.add(employee);
        }
    }*/

    /*public boolean removeEmployee(Employee employee) {
        return this.employees.remove(employee);
    }*/

    public Employee findEmployee(String firstName, String lastName) {
        for (Employee employee : this.employees) {
            if (employee.getFirstName().equals(firstName) && employee.getLastName().equals(lastName)) {
                return employee;
            }
        }
        return null;
    }

    @Override
    public Currency exchangeToUsd(Currency currency) {
        switch (currency.getType()) {
            case Currency.USD:
                return currency;
            case Currency.EURO: {
                double amountOfEuro = currency.getAmount();
                double resultUSD = amountOfEuro * Exchangable.EURO_TO_USD;
                setEur(new Currency((getEur().getAmount() + amountOfEuro), Currency.EURO));
                setUsd(new Currency((getUsd().getAmount() - resultUSD), Currency.USD));
                return new Currency(resultUSD, Currency.USD);
            }
            case Currency.RUB: {
                double amountOfRub = currency.getAmount();
                double resultUSD = amountOfRub * Exchangable.RUB_TO_USD;
                setRub(new Currency((getRub().getAmount() + amountOfRub), Currency.RUB));
                setUsd(new Currency((getUsd().getAmount() - resultUSD), Currency.USD));
                return new Currency(resultUSD, Currency.USD);
            }
            case Currency.BYN: {
                double amountOfByn = currency.getAmount();
                double resultUSD = amountOfByn * Exchangable.USD_SELL;
                setByn(new Currency((getByn().getAmount() + amountOfByn), Currency.BYN));
                setUsd(new Currency((getUsd().getAmount() - resultUSD), Currency.USD));
                return new Currency(resultUSD, Currency.USD);
            }
        }
        return null;
    }

    @Override
    public Currency exchangeToEuro(Currency currency) {
        switch (currency.getType()) {
            case Currency.USD: {
                double amountOfUsd = currency.getAmount();
                double resultEuro = amountOfUsd * Exchangable.USD_TO_EURO;
                setUsd(new Currency((getUsd().getAmount() + amountOfUsd), Currency.USD));
                setEur(new Currency((getEur().getAmount() - resultEuro), Currency.EURO));
                return new Currency(resultEuro, Currency.EURO);
            }
            case Currency.EURO:
                return currency;
            case Currency.RUB: {
                double amountOfRub = currency.getAmount();
                double resultEuro = amountOfRub * Exchangable.RUB_TO_EURO;
                setRub(new Currency((getRub().getAmount() + amountOfRub), Currency.RUB));
                setEur(new Currency((getEur().getAmount() - resultEuro), Currency.EURO));
                return new Currency(resultEuro, Currency.EURO);
            }
            case Currency.BYN: {
                double amountOfByn = currency.getAmount();
                double resultEuro = amountOfByn * Exchangable.EURO_SELL;
                setByn(new Currency((getByn().getAmount() + amountOfByn), Currency.BYN));
                setEur(new Currency((getEur().getAmount() - resultEuro), Currency.EURO));
                return new Currency(resultEuro, Currency.EURO);
            }
        }
        return null;
    }

    @Override
    public Currency exchangeToRub(Currency currency) {
        switch (currency.getType()) {
            case Currency.USD: {
                double amountOfUsd = currency.getAmount();
                double resultRub = amountOfUsd * Exchangable.USD_TO_RUB;
                setUsd(new Currency((getUsd().getAmount() + amountOfUsd), Currency.USD));
                setRub(new Currency((getRub().getAmount() - resultRub), Currency.RUB));
                return new Currency(resultRub, Currency.RUB);
            }
            case Currency.EURO: {
                double amountOfEuro = currency.getAmount();
                double resultRub = amountOfEuro * Exchangable.EURO_TO_RUB;
                setEur(new Currency((getEur().getAmount() + amountOfEuro), Currency.EURO));
                setRub(new Currency((getRub().getAmount() - resultRub), Currency.RUB));
                return new Currency(resultRub, Currency.RUB);
            }
            case Currency.RUB:
                return currency;
            case Currency.BYN: {
                double amountOfByn = currency.getAmount();
                double resultRub = amountOfByn * Exchangable.RUB_SELL;
                setByn(new Currency((getByn().getAmount() + amountOfByn), Currency.BYN));
                setRub(new Currency((getRub().getAmount() - resultRub), Currency.RUB));
                return new Currency(resultRub, Currency.RUB);
            }
        }
        return null;
    }

    @Override
    public Currency exchangeToByn(Currency currency) {
        switch (currency.getType()) {
            case Currency.USD: {
                double amountOfUsd = currency.getAmount();
                double resultByn = amountOfUsd * Exchangable.USD_SELL;
                setUsd(new Currency((getUsd().getAmount() + amountOfUsd), Currency.USD));
                setByn(new Currency((getByn().getAmount() - resultByn), Currency.BYN));
                return new Currency(resultByn, Currency.BYN);
            }
            case Currency.EURO: {
                double amountOfEuro = currency.getAmount();
                double resultByn = amountOfEuro * Exchangable.EURO_SELL;
                setEur(new Currency((getEur().getAmount() + amountOfEuro), Currency.EURO));
                setByn(new Currency((getByn().getAmount() - resultByn), Currency.BYN));
                return new Currency(resultByn, Currency.BYN);
            }
            case Currency.RUB: {
                double amountOfRub = currency.getAmount();
                double resultByn = amountOfRub * Exchangable.RUB_SELL;
                setRub(new Currency((getRub().getAmount() + amountOfRub), Currency.RUB));
                setByn(new Currency((getByn().getAmount() - resultByn), Currency.BYN));
                return new Currency(resultByn, Currency.BYN);
            }
            case Currency.BYN:
                return currency;
        }
        return null;
    }

    @Override
    public String toString() {
        return "Class Bank [name = " + getName() + ", address = " + getAddress() + ", foundedAt = "
                + getFoundedAt().getDayOfMonth() + "." + getFoundedAt().getMonth() + "."
                + getFoundedAt().getYear() + ", count = " + count + ", usd = " + usd + ", eur = " +
                eur + ", rub = " + rub + ", byn = " + byn + ", employees = " + employees + "]";
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (object == null || object.getClass() != this.getClass()) {
            return false;
        }
        Bank bank = (Bank) object;
        return usd.equals(bank.getUsd()) && eur.equals(bank.getEur()) && rub.equals(bank.getRub())
                && byn.equals(bank.getByn()) && employees.equals(bank.getEmployees())
                && (getName() != null && getName().equals(bank.getName()))
                && getAddress().equals(bank.getAddress()) && getFoundedAt().equals(bank.getFoundedAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(count, usd, eur, rub, byn, employees, getName(), getAddress(), getFoundedAt());
    }
}