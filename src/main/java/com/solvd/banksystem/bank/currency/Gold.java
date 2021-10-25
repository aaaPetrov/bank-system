package com.solvd.banksystem.bank.currency;

import com.solvd.banksystem.exception.InvalidCurrencyTypeException;
import java.util.Objects;

public class Gold extends Value {

    private double weight;
    private Currency pricePerGram;
    private int content;

    public Gold(double weight, Currency pricePerGram, int content) {
            this.weight = weight;
            this.pricePerGram = pricePerGram;
            this.content = content;
    }

    public Gold() {
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Currency getPricePerGram() {
        return pricePerGram;
    }

    public void setPricePerGram(Currency pricePerGram) {
            this.pricePerGram = pricePerGram;
    }

    public int getContent() {
        return content;
    }

    public void setContent(int content) {
        this.content = content;
    }

    @Override
    public Currency getValue() {
        return new Currency(weight * pricePerGram.getAmount(), pricePerGram.getCurrencyType());
    }

    @Override
    public void setValue(Currency currency) {
        if (currency.getCurrencyType().equals(this.pricePerGram.getCurrencyType())) {
            this.weight = currency.getAmount() / this.pricePerGram.getAmount();
        } else {
            throw new InvalidCurrencyTypeException("Runtime Exception: Invalid Currency Type.");
        }
    }

    @Override
    public void copy(Value value) {
        if(value instanceof Gold) {
            Gold gold = (Gold) value;
            this.weight = gold.getWeight();
            this.pricePerGram = gold.getPricePerGram();
            this.content = gold.getContent();
        }
    }

    @Override
    public void print() {
        System.out.println("Gold " + content + " content.");
        System.out.println("Weight " + weight + " gram, " + pricePerGram.getAmount()
                + " " + pricePerGram.getCurrencyType() + " per gram.");
        System.out.println("Total cost: " + getValue().getAmount() + " " + getValue().getCurrencyType());
    }

    @Override
    public String toString() {
        return "Class Gold [weight = " + weight + ", pricePerGram = " +
                pricePerGram + ", content = " + content + "]";
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (object == null || object.getClass() != this.getClass()) {
            return false;
        }
        Gold gold = (Gold) object;
        return weight == gold.getWeight() && pricePerGram.equals(gold.getPricePerGram()) && content == gold.getContent();
    }

    @Override
    public int hashCode() {
        return Objects.hash(weight, pricePerGram, content);
    }

}
