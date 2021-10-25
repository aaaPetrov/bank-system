package com.solvd.banksystem.bank.currency;

import com.solvd.banksystem.exception.InvalidCurrencyTypeException;
import java.util.Objects;

public class Diamond extends Value {

    public enum Clarity {

        A(1.5), B(1.3), C(1.1), D(1.0);

        private final double clarityCoefficient;

        Clarity(double clarityCoefficient) {
            this.clarityCoefficient = clarityCoefficient;
        }

        public double getClarityCoefficient() {
            return clarityCoefficient;
        }

    }

    private double carats;
    private Currency pricePerCarat;
    private String color;
    private Clarity clarity;

    public Diamond(double carats, Currency pricePerCarat, String color, Clarity clarity) {
            this.carats = carats;
            this.pricePerCarat = pricePerCarat;
            this.color = color;
            this.clarity = clarity;
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
            this.pricePerCarat = pricePerCarat;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Clarity getClarity() {
        return clarity;
    }

    public void setClarity(Clarity clarity) {
        this.clarity = clarity;
    }

    @Override
    public Currency getValue() {
        return new Currency(carats * pricePerCarat.getAmount() * clarity.getClarityCoefficient(), pricePerCarat.getCurrencyType());
    }

    @Override
    public void setValue(Currency currency) {
        if (currency.getCurrencyType().getType().equals(pricePerCarat.getCurrencyType().getType())) {
            this.carats = currency.getAmount() / pricePerCarat.getAmount() / clarity.getClarityCoefficient();
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
        System.out.println(color + " color diamond \"" + clarity.name() + "\" clarity.");
        System.out.println(carats + " carats, " + pricePerCarat.getAmount() * clarity.getClarityCoefficient() + " "
                + pricePerCarat.getCurrencyType() + " per carat.");
        System.out.println("Total cost: " + getValue().getAmount() + " " + getValue().getCurrencyType());
    }

    @Override
    public String toString() {
        return "Class Diamond [carats = " + carats + ", pricePerCarat = " +
                pricePerCarat + ", color = " + color + ", clarity = " + clarity.name() + "]";
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
                && (color != null && color.equals(diamond.getColor())) && clarity.name().equals(diamond.getClarity().name());
    }

    @Override
    public int hashCode() {
        return Objects.hash(carats, pricePerCarat, clarity, color);
    }
}
