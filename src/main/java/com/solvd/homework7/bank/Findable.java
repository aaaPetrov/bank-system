package com.solvd.homework7.bank;

import com.solvd.homework7.bankoperation.BankOperation;
import com.solvd.homework7.human.Human;
import java.util.List;

public interface Findable {

    List<BankOperation> find(Human human);

}
