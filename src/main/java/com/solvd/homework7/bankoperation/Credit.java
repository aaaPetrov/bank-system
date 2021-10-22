package com.solvd.homework7.bankoperation;

import com.solvd.homework7.bankoperation.client.Client;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Objects;

public class Credit extends BankOperation {

    public static int count = 0;

    private final CreditType creditType;
    private final double givenMoneyAmount;
    private final double backMoneyAmount;
    private final double monthlyPayment;
    private final String moneyType;
    private double moneyPaid;

    public Credit(Client client, CreditType creditType, double moneyAmount) {
        super(client);
        this.creditType = creditType;
        super.getExpired().setYear(super.getExpired().getYear() + creditType.getTermInYears());
        this.givenMoneyAmount = moneyAmount;
        this.backMoneyAmount = this.givenMoneyAmount + (this.givenMoneyAmount * creditType.getTermInYears() * (creditType.getYearPercent() / 100));
        this.monthlyPayment = this.backMoneyAmount / (this.creditType.getTermInYears() * 12);
        this.moneyType = creditType.getMoneyType();
        this.count++;
    }

    public CreditType getCreditType() {
        return creditType;
    }

    public double getGivenMoneyAmount() {
        return givenMoneyAmount;
    }

    public double getBackMoneyAmount() {
        return backMoneyAmount;
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
        System.out.printf("%-60s%s%s", "\n", "CREDIT:", "\n");
        System.out.println("1. CLIENT INFORMATION.");
        super.getClient().print();
        System.out.println("\n2. CREDIT TYPE.");
        creditType.print();
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        System.out.println("\n3. CREDIT.");
        System.out.print("Bank paid to the client: " + givenMoneyAmount
                + " " + moneyType + " at " + creditType.getYearPercent()
                + "% per annum for a priod of " + creditType.getTermInYears() + " years (");
        System.out.print(dateFormat.format(super.getIssued()));
        System.out.print(" - ");
        System.out.print(dateFormat.format(super.getExpired()));
        System.out.print(").\n");
        System.out.println("\nTo be paid to the bank: " + backMoneyAmount + " " + moneyType);
        System.out.println("Monthly payment: " + monthlyPayment);
        System.out.println("Money paid: " + moneyPaid);
    }

    @Override
    public String toString() {
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        return "Class Credit [count = " + count + ", client = " + getClient() + ", issued = "
                + dateFormat.format(getIssued()) + ", expired = " + dateFormat.format(getExpired())
                + ", creditType = " + creditType + ", givenMoneyAmount = " + givenMoneyAmount
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
        Credit credit = (Credit) object;
        return creditType.equals(credit.getCreditType())
                && (getIssued().getDate() == credit.getIssued().getDate())
                && (getIssued().getMonth() == credit.getIssued().getMonth())
                && (getIssued().getYear() == credit.getIssued().getYear())
                && (getIssued().getTime() == credit.getIssued().getTime())
                && (getExpired().getDate() == credit.getExpired().getDate())
                && (getExpired().getMonth() == credit.getExpired().getMonth())
                && (getExpired().getYear() == credit.getExpired().getYear())
                && (getExpired().getTime() == credit.getExpired().getTime())
                && givenMoneyAmount == credit.getGivenMoneyAmount()
                && backMoneyAmount == credit.getBackMoneyAmount()
                && monthlyPayment == credit.getMonthlyPayment()
                && moneyPaid == credit.getMoneyPaid()
                && (moneyType != null && moneyType.equals(credit.getMoneyType()));
    }

    @Override
    public int hashCode() {
        return Objects.hash(count, creditType, givenMoneyAmount, backMoneyAmount, monthlyPayment, moneyPaid, moneyType,
                getClient(), getIssued(), getExpired());
    }
}
