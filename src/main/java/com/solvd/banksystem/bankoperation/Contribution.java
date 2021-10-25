package com.solvd.banksystem.bankoperation;

import com.solvd.banksystem.bank.currency.Currency;
import com.solvd.banksystem.bank.currency.Diamond;
import com.solvd.banksystem.bank.currency.Gold;
import com.solvd.banksystem.bank.currency.Value;
import com.solvd.banksystem.print.Printable;
import java.util.Objects;

public class Contribution<T extends Value> implements Printable {

    private final T invested;
    private final double yearPercent = 0.12;
    private final int termInYears = 2;
    private T returned;

    public Contribution(T invested) {
        this.invested = invested;
        if (invested instanceof Gold) {
            this.returned = (T) new Gold();
            this.returned.copy(invested);
        } else if (invested instanceof Diamond) {
            this.returned = (T) new Diamond();
            this.returned.copy(invested);
        } else if (invested instanceof Currency) {
            this.returned = (T) new Currency();
            this.returned.copy(invested);
        }
        double returnedAmount = invested.getValue().getAmount() + (invested.getValue().getAmount() * yearPercent * termInYears);
        this.returned.setValue(new Currency(returnedAmount, invested.getValue().getCurrencyType()));
    }

    public T getInvested() {
        return invested;
    }

    public double getYearPercent() {
        return yearPercent;
    }

    public int getTermInYears() {
        return termInYears;
    }

    public T getReturned() {
        return returned;
    }

    @Override
    public void print() {
        System.out.println("FUNDS INVESTED:");
        invested.print();
        System.out.println("\nTerm of contribution: " + termInYears + " years.");
        System.out.println("Year percent: " + (yearPercent * 100) + "%.");
        System.out.println("\nFUNDS WILL BE RETURNED:");
        returned.print();
    }

    @Override
    public String toString() {
        return "Class Contribution [invested = " + invested + ", yearPercent = " +
                yearPercent + ", termInYears = " + termInYears + ", returned" + returned + "]";
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (object == null || object.getClass() != this.getClass()) {
            return false;
        }
        Contribution<T> contribution = (Contribution<T>) object;
        return this.invested.equals(contribution.getInvested()) && this.returned.equals(contribution.getInvested());
    }

    @Override
    public int hashCode() {
        return Objects.hash(invested, returned, yearPercent, termInYears);
    }

}
