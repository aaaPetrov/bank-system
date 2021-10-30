package com.solvd.banksystem.bank.currency;

import com.solvd.banksystem.print.Printable;
import java.util.Objects;

public class Currency extends Value implements Printable {

    private CurrencyType currencyType;
    private double amount;

    public Currency() {}

    public Currency(double moneyAmount, CurrencyType currencyType) {
            this.amount = moneyAmount;
            this.currencyType = currencyType;
    }

    public enum  CurrencyType {

        USD("USD"), EURO("EUR"), RUB("RUB"), BYN("BYN");

        private final String type;

        CurrencyType(String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }

    }

    public void setAmount(double moneyAmount) {
        this.amount = moneyAmount;
    }

    public double getAmount() {
        return amount;
    }

    public void setCurrencyType(CurrencyType currencyType) {
            this.currencyType = currencyType;
    }

    public CurrencyType getCurrencyType() {
        return currencyType;
    }

    @Override
    public Currency getValue() {
        return new Currency(amount, currencyType);
    }

    @Override
    public void setValue(Currency currency) {
        this.amount = currency.getAmount();
        this.currencyType = currency.getCurrencyType();
    }

    @Override
    public void copy(Value value) {
        if(value instanceof Currency) {
            Currency currency = (Currency) value;
            amount = currency.getAmount();
            currencyType = currency.getCurrencyType();
        }
    }

    @Override
    public void print() {
        System.out.println("Money amount: " + amount + " " + currencyType);
    }

    @Override
    public String toString() {
        return "Class Currency [type = " + currencyType + ", amount = " + amount + "]";
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (object == null || object.getClass() != this.getClass()) {
            return false;
        }
        Currency currency = (Currency) object;
        return amount == currency.getAmount() && (currencyType != null && currencyType.equals(currency.getCurrencyType()));
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, currencyType);
    }
}
