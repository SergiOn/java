package com.userfront.service;

import com.userfront.domain.PrimaryAccount;
import com.userfront.domain.SavingsAccount;

import java.security.Principal;

public interface AccountService {

	PrimaryAccount createPrimaryAccount();

    SavingsAccount createSavingsAccount();

    void deposit(String accountType, double amount, Principal principal);

    void withdraw(String accountType, double amount, Principal principal);

}
