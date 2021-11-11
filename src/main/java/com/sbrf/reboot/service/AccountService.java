package com.sbrf.reboot.service;

import lombok.AllArgsConstructor;

import java.util.Set;

@AllArgsConstructor
public class AccountService {
    private AccountRepository accountRepository;

    public boolean isAccountExist(long clientId, Account account) {
        Set<Account> accounts = accountRepository.getAllAccountsByClientId(clientId);
        return accounts.contains(account);
    }
}
