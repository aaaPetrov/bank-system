package com.solvd.banksystem.bank.currency;

import com.solvd.banksystem.exception.InvalidCurrencyTypeException;
import java.util.Objects;

public class Diamond extends Value {

    private double carats;
    private Currency pricePerCarat;
    private String color;
    private char clarity;

    public Diamond(double carats, Currency pricePerCarat, String color, char clarity) {
        if (pricePerCarat.getType().equals("USD") || pricePerCarat.getType().equals("EUR")
                || pricePerCarat.getType().equals("RUB") || pricePerCarat.getType().equals("BYN")) {
            this.carats = carats;
            this.pricePerCarat = pricePerCarat;
            this.color = color;
            this.clarity = clarity;
        } else {
            throw new InvalidCurrencyTypeException("Runtime Exception: Invalid Currency Type.");
        }
    }

    public Diamond() {
    }

    public double getCarats() {
        return carats;
    }

    public void setCarats(double carat) {
        this.carats = carat;
    }

    public Currency getPricePerCarat() {
        return pricePerCarat;
    }

    public void setPricePerCarat(Currency pricePerCarat) {
        if (pricePerCarat.getType().equals("USD") || pricePerCarat.getType().equals("EUR")
                || pricePerCarat.getType().equals("RUB") || pricePerCarat.getType().equals("BYN")) {
            this.pricePerCarat = pricePerCarat;
        } else {
            throw new InvalidCurrencyTypeException("Runtime Exception: Invalid Currency Type.");
        }
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public char getClarity() {
        return clarity;
    }

    public void setClarity(char clarity) {
        this.clarity = clarity;
    }

    @Override
    public Currency getValue() {
        double clarityCoefficient;
        if (this.clarity == 'A') {
            clarityCoefficient = 1.5;
        } else if (this.clarity == 'B') {
            clarityCoefficient = 1.3;
        } else if (this.clarity == 'C') {
            clarityCoefficient = 1.1;
        } else {
            clarityCoefficient = 1;
        }
        return new Currency(carats * pricePerCarat.getAmount() * clarityCoefficient, pricePerCarat.getType());
    }

    @Override
    public void setValue(Currency currency) {
        double clarityCoefficient;
        if (this.clarity == 'A') {
            clarityCoefficient = 1.5;
        } else if (this.clarity == 'B') {
            clarityCoefficient = 1.3;
        } else if (this.clarity == 'C') {
            clarityCoefficient = 1.1;
        } else {
            clarityCoefficient = 1;
        }
        if (currency.getType().equals(pricePerCarat.getType())) {
            this.carats = currency.getAmount() / pricePerCarat.getAmount() / clarityCoefficient;
        } else {
            throw new InvalidCurrencyTypeException("Runtime Exception: Invalid Currency Type.");
        }
    }

    @Override
    public void copy(Value value) {
        if(value instanceof Diamond) {
            Diamond diamond = (Diamond) value;
            this.carats = diamond.getCarats();
            this.pricePerCarat = diamond.getPricePerCarat();
            this.color = diamond.getColor();
            this.clarity = diamond.getClarity();
        }
    }

    @Override
    public void print() {
        double clarityCoefficient;
        if (this.clarity == 'A') {
            clarityCoefficient = 1.5;
        } else if (this.clarity == 'B') {
            clarityCoefficient = 1.3;
        } else if (this.clarity == 'C') {
            clarityCoefficient = 1.1;
        } else {
            clarityCoefficient = 1;
        }
        System.out.println(color + " color diamond \"" + clarity + "\" clarity.");
        System.out.println(carats + " carats, " + pricePerCarat.getAmount() * clarityCoefficient + " "
                + pricePerCarat.getType() + " per carat.");
        System.out.println("Total cost: " + getValue().getAmount() + " " + getValue().getType());
    }

    @Override
    public String toString() {
        return "Class Diamond [carats = " + carats + ", pricePerCarat = " +
                pricePerCarat + ", color = " + color + ", clarity = " + clarity + "]";
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (object == null || object.getClass() != this.getClass()) {
            return false;
        }
        Diamond diamond = (Diamond) object;
        return carats == diamond.getCarats() && pricePerCarat.equals(diamond.getPricePerCarat())
                && (color != null && color.equals(diamond.getColor())) && clarity == diamond.getClarity();
    }

    @Override
    public int hashCode() {
        return Objects.hash(carats, pricePerCarat, clarity, color);
    }
}
