package com.solvd.banksystem.bank;

import com.solvd.banksystem.address.Address;
import com.solvd.banksystem.bank.currency.Currency.CurrencyType;
import com.solvd.banksystem.bank.employee.Employee;
import com.solvd.banksystem.bank.organization.Organization;
import com.solvd.banksystem.bank.organization.TaxPayable;
import com.solvd.banksystem.bankoperation.BankOperation;
import com.solvd.banksystem.bankoperation.Credit;
import com.solvd.banksystem.bankoperation.CreditType;
import com.solvd.banksystem.bankoperation.Mortgage;
import com.solvd.banksystem.bankoperation.client.Client;
import com.solvd.banksystem.human.Human;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BankSystem extends Organization implements Findable {

    private static BankSystem instance;

    private List<Bank> banks;

    private BankSystem(String name, Address address, LocalDateTime foundedAt) {
        super(name, address, foundedAt);
    }

    public static BankSystem getInstance(String name, Address address, LocalDateTime foundedAt) {
        if(instance == null) {
            instance = new BankSystem(name, address, foundedAt);
            return instance;
        }
        return instance;
    }

    public void setBanks(List<Bank> banks) {
        this.banks = banks;
    }

    public List<Bank> getBanks() {
        return banks;
    }

    public void addBank(Bank bank) {
        if (bank == null) {
            return;
        }
        if (this.banks == null) {
            this.banks = new ArrayList<>();
        }
        if (!this.banks.contains(bank)) {
            this.banks.add(bank);
        } else {
            System.out.println("Bank already exist.");
        }
    }

    public boolean removeBank(Bank bank) {
        if (bank == null || banks == null || banks.isEmpty()) {
            return false;
        }
        return this.banks.remove(bank);
    }

    @Override
    public List<BankOperation> find(Human human) {
        List<BankOperation> result = null;
        if (human == null) {
            return null;
        }
        if (this.banks != null && this.banks.size() > 0) {
            result = new ArrayList<>();
            for (Bank element : this.banks) {
                result.addAll(element.find(human));
            }
        }
        return result;
    }

    public void searchForCreditType(CurrencyType currencyType) {
        System.out.printf("%-60s%s%s", "\n", "CREDIT SEARCH RESULT:", "\n");
        int flag = 0;
        int otherBanks = 0;
        for (Bank element : this.banks) {
            if (element instanceof CreditBank) {
                CreditBank creditBank = (CreditBank) element;
                List<CreditType> creditTypes = creditBank.findCreditType(currencyType);
                if (creditTypes != null && !creditTypes.isEmpty()) {
                    for (CreditType creditTypeElement : creditTypes) {
                        System.out.print("Bank \"" + element.getName() + "\" : ");
                        creditTypeElement.print();
                    }
                } else {
                    flag++;
                }
            } else {
                otherBanks++;
            }
        }
        if (flag == this.banks.size() - otherBanks) {
            System.out.println("No credits found for your request.");
        }
    }

    public void searchForCreditType(CurrencyType currencyType, double moneyAmount) {
        System.out.printf("%-60s%s%s", "\n", "CREDIT SEARCH RESULT:", "\n");
        int flag = 0;
        int otherBanks = 0;
        for (Bank element : this.banks) {
            if (element instanceof CreditBank) {
                CreditBank creditBank = (CreditBank) element;
                List<CreditType> creditTypes = creditBank.findCreditType(currencyType, moneyAmount);
                if (creditTypes != null && !creditTypes.isEmpty()) {
                    for (CreditType creditTypeElement : creditTypes) {
                        System.out.print("Bank \"" + element.getName() + "\" : ");
                        creditTypeElement.print();
                    }
                } else {
                    flag++;
                }
            } else {
                otherBanks++;
            }
        }
        if (flag == this.banks.size() - otherBanks) {
            System.out.println("No credits found for your request.");
        }
    }

    public static void exchangeRates() {
        System.out.printf("%-60s%s%s", "\n", "EXCHANGE RATES:", "\n");
        System.out.printf("%15s %8s%n", "BUY", "SELL");
        System.out.printf("%-12s%-8s%-6s%s%n", "1 USD", Exchangable.USD_BUY, Exchangable.USD_SELL, "BYN");
        System.out.printf("%-12s%-8s%-6s%s%n", "1 EURO", Exchangable.EURO_BUY, Exchangable.EURO_SELL, "BYN");
        System.out.printf("%-12s%-8s%-6s%s%n", "1 RUB", Exchangable.RUB_BUY, Exchangable.RUB_SELL, "BYN");
    }

    @Override
    public void payTax() {
        System.out.printf("%-60s%s%s", "\n", "BANK SYSTEM TAX:", "\n");
        System.out.println(super.getName() + " bank system: ");
        double usdTax = 0;
        double eurTax = 0;
        double rubTax = 0;
        double bynTax = 0;
        for (Bank element : this.banks) {
            if (element.getUsd().getAmount() > 0) {
                usdTax += element.getUsd().getAmount() * TaxPayable.taxPercent;
                element.getUsd().setAmount(element.getUsd().getAmount() - usdTax);
            }
            if (element.getEur().getAmount() > 0) {
                eurTax += element.getEur().getAmount() * TaxPayable.taxPercent;
                element.getEur().setAmount(element.getEur().getAmount() - eurTax);
            }
            if (element.getRub().getAmount() > 0) {
                rubTax += element.getRub().getAmount() * TaxPayable.taxPercent;
                element.getRub().setAmount(element.getRub().getAmount() - rubTax);
            }
            if (element.getByn().getAmount() > 0) {
                bynTax += element.getByn().getAmount() * TaxPayable.taxPercent;
                element.getByn().setAmount(element.getByn().getAmount() - bynTax);
            }
        }
        System.out.println("USD tax paid: " + usdTax);
        System.out.println("EURO tax paid: " + eurTax);
        System.out.println("RUB tax paid: " + rubTax);
        System.out.println("BYN tax paid: " + bynTax);
    }

    @Override
    public void print() {
        System.out.printf("%-60s%s%s", "\n", "BANK SYSTEM INFORMATION:", "\n");
        super.print();
        System.out.println("Number of banks in system: " + Bank.count);
        System.out.println("Number of employees: " + Employee.count);
        System.out.println("Credits issued: " + Credit.count);
        System.out.println("Mortgage issued: " + Mortgage.count);
        System.out.println("Number types of credits in banks: " + CreditType.count);
        System.out.println("Number of bank clients: " + Client.count);
    }

    @Override
    public String toString() {
        return "Class BankSystem [name = " + getName() + ", address = " + getAddress() + ", foundedAt = "
                + getFoundedAt().getDayOfMonth() + "." + getFoundedAt().getMonth() + "."
                + getFoundedAt().getYear() + ", USDBUY = " + Exchangable.USD_BUY + ", USDSELL = " + Exchangable.USD_SELL + ", EUROBUY = " +
                Exchangable.EURO_BUY + ", EUROSELL = " + Exchangable.EURO_SELL + ", RUBBUY = " + Exchangable.RUB_BUY +
                ", RUBSELL = " + Exchangable.RUB_SELL + ", banks = " + banks + "]";
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (object == null || object.getClass() != this.getClass()) {
            return false;
        }
        BankSystem bankSystem = (BankSystem) object;
        return banks.equals(bankSystem.getBanks())
                && getAddress().equals(bankSystem.getAddress())
                && getFoundedAt().equals(bankSystem.getFoundedAt())
                && (getName() != null && getName().equals(bankSystem.getName()));
    }

    @Override
    public int hashCode() {
        return Objects.hash(Exchangable.USD_BUY, Exchangable.USD_SELL, Exchangable.EURO_SELL, Exchangable.EURO_BUY,
                Exchangable.RUB_BUY, Exchangable.RUB_SELL, banks, getName(),
                getFoundedAt(), getAddress());
    }
}
