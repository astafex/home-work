package com.sbrf.reboot.service;

import com.sbrf.reboot.dto.Account;
import lombok.AllArgsConstructor;

import java.io.FileNotFoundException;
import java.util.Set;

@AllArgsConstructor
public class AccountService {
    private AccountRepository accountRepository;

    public boolean isAccountExist(long clientId, Account account) throws FileNotFoundException {
        Set<Account> accounts = accountRepository.getAllAccountsByClientId(clientId);
        return accounts.contains(account);
    }
}
