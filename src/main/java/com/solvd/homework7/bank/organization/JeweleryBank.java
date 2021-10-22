package com.solvd.homework7.bank.organization;

import com.solvd.homework7.address.Address;
import com.solvd.homework7.bank.Exchangable;
import com.solvd.homework7.bank.currency.Currency;
import com.solvd.homework7.bank.currency.Value;
import com.solvd.homework7.bankoperation.Contribution;
import com.solvd.homework7.bankoperation.client.Client;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class JeweleryBank<T extends Value> extends Organization {

    private Map<Client, Contribution<? extends Value>> contributions;

    public JeweleryBank(String nameOfBank, Address address, LocalDateTime foundedAt) {
        super(nameOfBank, address, foundedAt);
    }

    public Map<Client, Contribution<? extends Value>> getContributions() {
        return contributions;
    }

    public void setContributions(Map<Client, Contribution<? extends Value>> contributions) {
        this.contributions = contributions;
    }

    public void addContribution(Client client, Contribution<? extends Value> contribution) {
        if(contributions == null) {
            this.contributions = new HashMap<>();
            this.contributions.put(client, contribution);
        } else {
            this.contributions.put(client, contribution);
        }

    }

    public Contribution<? extends Value> findContribution(Client client) {
        return this.contributions.get(client);
    }

    public Contribution<? extends Value> removeContribution(Client client) {
        return this.contributions.remove(client);
    }

    public double amountOfDeposits() {
        double resultAmount = 0;
        for (Map.Entry<Client,Contribution<? extends Value>> entry : this.contributions.entrySet()) {
            if(entry.getValue().getInvested().getValue().getType().equals(Currency.USD)) {
                resultAmount += entry.getValue().getInvested().getValue().getAmount();
            } else if(entry.getValue().getInvested().getValue().getType().equals(Currency.EURO)) {
                double converted =  entry.getValue().getInvested().getValue().getAmount() * Exchangable.EURO_TO_USD;
                resultAmount += converted;
            } else if(entry.getValue().getInvested().getValue().getType().equals(Currency.RUB)) {
                double converted =  entry.getValue().getInvested().getValue().getAmount() * Exchangable.RUB_TO_USD;
                resultAmount += converted;
            } else if(entry.getValue().getInvested().getValue().getType().equals(Currency.BYN)) {
                double converted =  entry.getValue().getInvested().getValue().getAmount() / Exchangable.USD_BUY;
                resultAmount += converted;
            }
        }
        return resultAmount;
    }

    @Override
    public void payTax() {
        double tax = amountOfDeposits() * TaxPayable.taxPercent;
        System.out.println("Tax paid: " + tax + " USD.");
    }

    @Override
    public void print() {
        super.print();
    }

}
