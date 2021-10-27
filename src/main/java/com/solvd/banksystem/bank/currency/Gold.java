package com.solvd.banksystem.bank.currency;

import com.solvd.banksystem.exception.InvalidCurrencyTypeException;
import java.util.Objects;

public class Gold extends Value {

    private double weight;
    private Content content;

    public Gold(double weight, Content content) {
            this.weight = weight;
            this.content = content;
    }

    public Gold() {
    }

    public enum Content {
        C999(127.51),
        C958(122.15),
        C950(121.13),
        C916(116.80),
        C875(114.76),
        C750(95.63),
        C585(74.59);

        private final Currency pricePerGram;

        Content(double moneyAmount) {
            pricePerGram = new Currency(moneyAmount, Currency.CurrencyType.BYN);
        }

        public Currency getPricePerGram() {
            return pricePerGram;
        }

    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Currency getPricePerGram() {
        return this.content.getPricePerGram();
    }

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    @Override
    public Currency getValue() {
        return new Currency(weight * this.content.getPricePerGram().getAmount(),
                this.content.getPricePerGram().getCurrencyType());
    }

    @Override
    public void setValue(Currency currency) {
        if (currency.getCurrencyType().equals( this.content.getPricePerGram().getCurrencyType())) {
            this.weight = currency.getAmount() / this.content.getPricePerGram().getAmount();
        } else {
            throw new InvalidCurrencyTypeException("Runtime Exception: Invalid Currency Type.");
        }
    }

    @Override
    public void copy(Value value) {
        if(value instanceof Gold) {
            Gold gold = (Gold) value;
            this.weight = gold.getWeight();
            this.content = gold.getContent();
        }
    }

    @Override
    public void print() {
        System.out.println("Gold " + content.name() + " content.");
        System.out.println("Weight " + weight + " gram, " + this.content.getPricePerGram().getAmount()
                + " " + this.content.getPricePerGram().getCurrencyType() + " per gram.");
        System.out.println("Total cost: " + getValue().getAmount() + " " + getValue().getCurrencyType());
    }

    @Override
    public String toString() {
        return "Class Gold [weight = " + weight + ", pricePerGram = " +
                this.content.getPricePerGram() + " " + this.content.getPricePerGram().getCurrencyType() + ", content = " + content.name() + "]";
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
        return weight == gold.getWeight() && content.name().equals(gold.getContent().name());
    }

    @Override
    public int hashCode() {
        return Objects.hash(weight, content);
    }

}
