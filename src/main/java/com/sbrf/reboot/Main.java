package com.sbrf.reboot;

import com.sbrf.reboot.repository.JsonAccountRepository;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        JsonAccountRepository accountRepository = new JsonAccountRepository("src/main/resources/Accounts.txt");
        accountRepository.updateClientNumber(2, "5-ACC1NUM", "XXXXXXXX");
    }
}
