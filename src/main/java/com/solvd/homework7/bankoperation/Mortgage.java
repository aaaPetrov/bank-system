package com.solvd.homework7.bankoperation;

import com.solvd.homework7.bankoperation.client.Client;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Objects;

public class Mortgage extends BankOperation {

    public static int count = 0;

    private final MortgagedApartment apartment;
    private final int termInYears = 5;
    private final int yearPercent = 19;
    private final double givenMoneyAmount;
    private final double backMoneyAmount;
    private final double monthlyPayment;
    private final String moneyType;
    private double moneyPaid;

    public Mortgage(Client client, MortgagedApartment apartment) {
        super(client);
        this.apartment = apartment;
        super.getExpired().setYear(super.getExpired().getYear() + termInYears);
        this.givenMoneyAmount = apartment.getPrice().getAmount();
        this.backMoneyAmount = this.givenMoneyAmount + (this.givenMoneyAmount * this.termInYears * this.yearPercent);
        this.monthlyPayment = this.backMoneyAmount / (this.termInYears * 12);
        this.moneyType = apartment.getPrice().getType();
        this.count++;
    }

    public MortgagedApartment getApartment() {
        return apartment;
    }

    public double getGivenMoneyAmount() {
        return givenMoneyAmount;
    }

    public double getBackMoneyAmount() {
        return backMoneyAmount;
    }

    public int getTermInYears() {
        return termInYears;
    }

    public int getYearPercent() {
        return yearPercent;
    }

    public double getMonthlyPayment() {
        return monthlyPayment;
    }

    public String getMoneyType() {
        return moneyType;
    }

    public void setMoneyPaid(double moneyPaid) {
        if (moneyPaid > 0) {
            this.moneyPaid = moneyPaid;
        }
    }

    public double getMoneyPaid() {
        return moneyPaid;
    }

    @Override
    public void print() {
        System.out.printf("%-60s%s%s", "\n", "MORTGAGE:", "\n");
        System.out.println("1. CLIENT INFORMATION.");
        super.getClient().print();
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        System.out.println("\n2. MORTGAGE.");
        System.out.print("Bank paid to the client: " + givenMoneyAmount
                + " " + moneyType + " at " + yearPercent + "% per annum for a priod of " + termInYears + " years (");
        System.out.print(dateFormat.format(super.getIssued()));
        System.out.print(" - ");
        System.out.print(dateFormat.format(super.getExpired()));
        System.out.print(").");
        System.out.println("\nTo be paid to the bank: " + backMoneyAmount + " " + moneyType);
        System.out.println("Monthly payment: " + monthlyPayment);
        System.out.println("Money paid: " + moneyPaid);
        System.out.println("\n3. MORTGAGED PROPERTY.");
        apartment.print();
    }

    @Override
    public String toString() {
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        return "Class Mortgage [count = " + count + " ,client = " + getClient() + ", issued = "
                + dateFormat.format(getIssued()) + ", expired = " + dateFormat.format(getExpired())
                + ", apartment = " + apartment + ", givenMoneyAmount = " + givenMoneyAmount
                + ", termInYears = " + termInYears + ", yearPercent = " + yearPercent
                + ", backMoneyAmount = " + backMoneyAmount + ", monthlyPayment = " + monthlyPayment
                + ", moneyPaid = " + moneyPaid + ", moneyType = " + moneyType + "]";
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (object == null || object.getClass() != this.getClass()) {
            return false;
        }
        Mortgage mortgage = (Mortgage) object;
        return apartment.equals(mortgage.getApartment())
                && (getIssued().getDate() == mortgage.getIssued().getDate())
                && (getIssued().getMonth() == mortgage.getIssued().getMonth())
                && (getIssued().getYear() == mortgage.getIssued().getYear())
                && (getIssued().getTime() == mortgage.getIssued().getTime())
                && (getExpired().getDate() == mortgage.getExpired().getDate())
                && (getExpired().getMonth() == mortgage.getExpired().getMonth())
                && (getExpired().getYear() == mortgage.getExpired().getYear())
                && (getExpired().getTime() == mortgage.getExpired().getTime())
                && givenMoneyAmount == mortgage.getGivenMoneyAmount()
                && backMoneyAmount == mortgage.getBackMoneyAmount()
                && monthlyPayment == mortgage.getMonthlyPayment()
                && moneyPaid == mortgage.getMoneyPaid()
                && (moneyType != null && moneyType.equals(mortgage.getMoneyType()))
                && termInYears == mortgage.getTermInYears() && yearPercent == mortgage.getYearPercent();
    }

    @Override
    public int hashCode() {
        return Objects.hash(count, apartment, termInYears, yearPercent, givenMoneyAmount, backMoneyAmount, monthlyPayment, moneyPaid, moneyType,
                getClient(), getIssued(), getExpired());
    }
}
