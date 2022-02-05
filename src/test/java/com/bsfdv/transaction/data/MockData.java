package com.bsfdv.transaction.data;

import com.bsfdv.transaction.model.Account;

import java.util.Collections;
import java.util.List;

public class MockData {

    public static Account zeroBalanceAccount = new Account(1L, "Hassan", "Egypt", 0L, Collections.emptyList(), Collections.emptyList());
    public static Account oneThousandBalanceAccount = new Account(2L, "Hassan", "Egypt", 1000L, Collections.emptyList(), Collections.emptyList());
    public static Account tenThousandsBalanceAccount = new Account(3L, "Hassan", "Egypt", 10000L, Collections.emptyList(), Collections.emptyList());
    public static List<Account> accountsData = List.of(zeroBalanceAccount, oneThousandBalanceAccount, tenThousandsBalanceAccount);

}
