package com.sbrf.reboot.repository;

import com.sbrf.reboot.dto.Account;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class AccountRepositoryJsonTest {
    AccountRepository accountRepository;
    Set<Account> allAccountsByClientId;

    @BeforeEach
    void setUp() throws IOException {
        accountRepository = new JsonAccountRepository("src/main/resources/Accounts.txt");
        allAccountsByClientId = accountRepository.getAllAccountsByClientId(1);
    }

    @Test
    void onlyPersonalAccounts() {
        ArrayList<String> strings = new ArrayList<>();
        Collections.addAll(strings, "2-ACCNUM", "1-ACCNUM", "4-ACC1NUM");

        allAccountsByClientId.forEach(e -> assertTrue(strings.contains(e.getNumber())));
    }

    @Test
    void successGetAllAccountsByClientId() {
        assertEquals(1, (int) allAccountsByClientId.stream().filter(e -> e.getNumber().equals("4-ACC1NUM")).count());
    }

    @Test
    void failGetAllAccountsByClientId() {
        accountRepository = new JsonAccountRepository("somePath");
        assertThrows(FileNotFoundException.class, () -> {
            accountRepository.getAllAccountsByClientId(1L);
        });
    }

    @Test
    void successUpdateNumberByClientId() throws IOException {
        JsonAccountRepository repository = new JsonAccountRepository("src/main/resources/Accounts.txt");
        assertTrue(repository.updateClientNumber(1, "1-ACCNUM", "X-XXXXXXX"));
    }
}