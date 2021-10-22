package com.solvd.homework7.bank;

import com.solvd.homework7.bankoperation.BankOperation;

public interface Operationable {

    void add(BankOperation operation);

    boolean remove(BankOperation operation);

}
