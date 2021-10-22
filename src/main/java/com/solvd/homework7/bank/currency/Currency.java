package com.solvd.homework7.bank.currency;

import com.solvd.homework7.exception.InvalidCurrencyTypeException;
import print.Printable;
import java.util.Objects;

public class Currency extends Value implements Printable {

    public static final String USD = "USD";
    public static final String EURO = "EUR";
    public static final String BYN = "BYN";
    public static final String RUB = "RUB";

    private String type;
    private double amount;

    public Currency() {}

    public Currency(double moneyAmount, String moneyType) {
        if (moneyType.equals("USD") || moneyType.equals("EUR") || moneyType.equals("RUB") || moneyType.equals("BYN")) {
            this.amount = moneyAmount;
            this.type = moneyType;
        } else {
            throw new InvalidCurrencyTypeException("Runtime Exception: Invalid Currency Type.");
        }
    }

    public void setAmount(double moneyAmount) {
        this.amount = moneyAmount;
    }

    public double getAmount() {
        return amount;
    }

    public void setType(String moneyType) {
        if (moneyType.equals("USD") || moneyType.equals("EUR") || moneyType.equals("RUB") || moneyType.equals("BYN")) {
            this.type = moneyType;
        } else {
            throw new InvalidCurrencyTypeException("Runtime Exception: Invalid Currency Type.");
        }
    }

    public String getType() {
        return type;
    }

    @Override
    public Currency getValue() {
        return new Currency(amount, type);
    }

    @Override
    public void setValue(Currency currency) {
        this.amount = currency.getAmount();
        this.type = currency.getType();
    }

    @Override
    public void copy(Value value) {
        if(value instanceof Currency) {
            Currency currency = (Currency) value;
            amount = currency.getAmount();
            type = currency.getType();
        }
    }

    @Override
    public void print() {
        System.out.println("Money amount: " + amount + " " + type);
    }

    @Override
    public String toString() {
        return "Class Currency [USD = " + USD + ", EURO = " +
                EURO + ", BYN = " + BYN + ", RUB = " +
                RUB + ", type = " + type + ", amount = " + amount + "]";
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
        return amount == currency.getAmount() && (type != null && type.equals(currency.getType()));
    }

    @Override
    public int hashCode() {
        return Objects.hash(USD, EURO, BYN, RUB, amount, type);
    }
}
