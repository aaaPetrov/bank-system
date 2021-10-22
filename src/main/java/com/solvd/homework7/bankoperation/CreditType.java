package com.solvd.homework7.bankoperation;

import print.Printable;
import java.util.Objects;

public class CreditType implements Printable {

    public static int count = 0;

    private String creditName;
    private String moneyType;
    private int termInYears;
    private double yearPercent;
    private double minMoneyAmount;
    private double maxMoneyAmount;

    public CreditType(String creditName, String moneyType, int termInYears, double yearPercent, double minMoneyAmount, double maxMoneyAmount) {
        this.creditName = creditName;
        this.moneyType = moneyType;
        this.yearPercent = yearPercent;
        this.termInYears = termInYears;
        this.minMoneyAmount = minMoneyAmount;
        this.maxMoneyAmount = maxMoneyAmount;
        this.count++;
    }

    public void setCreditName(String creditName) {
        this.creditName = creditName;
    }

    public String getCreditName() {
        return creditName;
    }

    public void setMoneyType(String moneyType) {
        this.moneyType = moneyType;
    }

    public String getMoneyType() {
        return moneyType;
    }

    public void setTermInYears(int termInYears) {
        this.termInYears = termInYears;
    }

    public int getTermInYears() {
        return termInYears;
    }

    public void setYearPercent(double yearPercent) {
        this.yearPercent = yearPercent;
    }

    public double getYearPercent() {
        return yearPercent;
    }

    public void setMinMoneyAmount(double minMoneyAmount) {
        this.minMoneyAmount = minMoneyAmount;
    }

    public double getMinMoneyAmount() {
        return minMoneyAmount;
    }

    public void setMaxMoneyAmount(double maxMoneyAmount) {
        this.maxMoneyAmount = maxMoneyAmount;
    }

    public double getMaxMoneyAmount() {
        return maxMoneyAmount;
    }

    @Override
    public void print() {
        System.out.println("Credit " + creditName + " from "
                + minMoneyAmount + " " + moneyType + " to "
                + maxMoneyAmount + " " + moneyType + " for a period of "
                + termInYears + " years at " + yearPercent + "% per year.");
    }

    @Override
    public String toString() {
        return "Class CreditType [count = " + count + ", creditName = " + creditName + ", moneyType = "
                + moneyType + ", termInYears = " + termInYears
                + ", yearPercent = " + yearPercent + ", minMoneyAmount = " + minMoneyAmount
                + ", maxMoneyAmount = " + maxMoneyAmount + "]";
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (object == null || object.getClass() != this.getClass()) {
            return false;
        }
        CreditType creditType = (CreditType) object;
        return (moneyType != null && moneyType.equals(creditType.getMoneyType()))
                && (creditName != null && creditName.equals(creditType.getCreditName()))
                && termInYears == creditType.getTermInYears()
                && yearPercent == creditType.getYearPercent()
                && minMoneyAmount == creditType.getMinMoneyAmount()
                && maxMoneyAmount == creditType.getMaxMoneyAmount();
    }

    @Override
    public int hashCode() {
        return Objects.hash(count, creditName, moneyType, termInYears, yearPercent, minMoneyAmount, maxMoneyAmount);
    }
}
