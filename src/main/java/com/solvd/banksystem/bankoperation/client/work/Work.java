package com.solvd.banksystem.bankoperation.client.work;

import com.solvd.banksystem.print.Printable;
import java.util.Objects;
import com.solvd.banksystem.bank.currency.Currency.CurrencyType;

public class Work implements Printable {

    private String companyName;
    private String positionName;
    private int salary;
    private String moneyType;

    public Work(String companyName, String positionName, int salary, CurrencyType currencyType) {
        this.companyName = companyName;
        this.positionName = positionName;
        if (salary > 0) {
            this.salary = salary;
        }
        this.moneyType = currencyType.getType();
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setSalary(int salary) {
        if (salary > 0) {
            this.salary = salary;
        }
    }

    public int getSalary() {
        return salary;
    }

    public void setMoneyType(String moneyType) {
        this.moneyType = moneyType;
    }

    public String getMoneyType() {
        return moneyType;
    }

    @Override
    public void print() {
        System.out.println("Work: ");
        System.out.printf("%7s%-12s%s%n", "", "Company: ", companyName);
        System.out.printf("%7s%-12s%s%n", "", "Position: ", positionName);
        System.out.printf("%7s%-12s%s%4s%n", "", "Salary: ", salary, moneyType);
    }

    @Override
    public String toString() {
        return "Class Address [companyName = " + companyName + ", positionName = " +
                positionName + ", salary = " + salary + ", moneyType = " + moneyType + "]";
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (object == null || object.getClass() != this.getClass()) {
            return false;
        }
        Work work = (Work) object;
        return salary == work.getSalary() &&
                (companyName != null && companyName.equals(work.getCompanyName()))
                && (positionName != null && positionName.equals(work.getPositionName()))
                && (moneyType != null && moneyType.equals(work.getMoneyType()));
    }

    @Override
    public int hashCode() {
        return Objects.hash(salary, companyName, positionName, moneyType);
    }
}
