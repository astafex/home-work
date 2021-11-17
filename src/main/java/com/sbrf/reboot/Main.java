package com.sbrf.reboot;

import com.sbrf.reboot.repository.AccountRepositoryImpl;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        AccountRepositoryImpl accountRepository = new AccountRepositoryImpl("src/main/resources/Accounts.txt");
        accountRepository.updateClientNumber(2, "5-ACC1NUM", "XXXXXXXX");
    }
}
