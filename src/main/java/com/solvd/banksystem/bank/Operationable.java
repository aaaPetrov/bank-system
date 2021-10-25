package com.solvd.banksystem.bank;

import com.solvd.banksystem.bankoperation.BankOperation;

public interface Operationable {

    void add(BankOperation operation);

    boolean remove(BankOperation operation);

}
