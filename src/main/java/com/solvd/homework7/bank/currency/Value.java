package com.solvd.homework7.bank.currency;

import print.Printable;

public abstract class Value implements Printable {

    public abstract Currency getValue();

    public abstract void setValue(Currency currency);

    public abstract void copy(Value value);

}
