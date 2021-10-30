package com.solvd.banksystem.bank.currency;

import com.solvd.banksystem.print.Printable;

public abstract class Value implements Printable {

    public abstract Currency getValue();

    public abstract void setValue(Currency currency);

    public abstract void copy(Value value);

}
