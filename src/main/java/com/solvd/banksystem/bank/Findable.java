package com.solvd.banksystem.bank;

import com.solvd.banksystem.bankoperation.BankOperation;
import com.solvd.banksystem.human.Human;
import java.util.List;

@FunctionalInterface
public interface Findable {

    List<BankOperation> find(Human human);

}
