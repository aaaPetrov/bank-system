package com.solvd.homework7.bankoperation;

import com.solvd.homework7.bankoperation.client.Client;
import print.Printable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public abstract class BankOperation implements Printable {

    private final Client client;
    private final Date issued;
    private final Date expired;

    public BankOperation(Client client) {
        this.client = client;
        this.issued = new Date();
        this.expired = new Date();
        this.expired.setTime(this.issued.getTime());
    }

    public Client getClient() {
        return client;
    }

    public Date getIssued() {
        return issued;
    }

    public Date getExpired() {
        return expired;
    }

    @Override
    public String toString() {
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        return "Class BankOperation [client = " + client + ", issued = "
                + dateFormat.format(issued) + ", expired = " + dateFormat.format(expired) + "]";
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (object == null || object.getClass() != this.getClass()) {
            return false;
        }
        BankOperation bankOperation = (BankOperation) object;
        return this.client.equals(bankOperation.getClient())
                && (this.issued.getDate() == bankOperation.getIssued().getDate())
                && (this.issued.getMonth() == bankOperation.getIssued().getMonth())
                && (this.issued.getYear() == bankOperation.getIssued().getYear())
                && (this.issued.getTime() == bankOperation.getIssued().getTime())
                && (this.expired.getDate() == bankOperation.getExpired().getDate())
                && (this.expired.getMonth() == bankOperation.getExpired().getMonth())
                && (this.expired.getYear() == bankOperation.getExpired().getYear())
                && (this.expired.getTime() == bankOperation.getExpired().getTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(client, issued, expired);
    }
}
