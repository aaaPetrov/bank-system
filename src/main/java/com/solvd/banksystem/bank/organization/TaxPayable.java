package com.solvd.banksystem.bank.organization;

@FunctionalInterface
public interface TaxPayable {

    double taxPercent = 0.13;

    void payTax();

}
