package com.sbrf.reboot.repository;

import com.sbrf.reboot.dto.Account;
import com.sbrf.reboot.service.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class AccountRepositoryImplTest {
    AccountRepository accountRepository;
    Set<Account> allAccountsByClientId;

    @BeforeEach
    void setUp() throws FileNotFoundException {
        accountRepository = new AccountRepositoryImpl("src/main/resources/Accounts.txt");
        allAccountsByClientId = accountRepository.getAllAccountsByClientId(1);
    }

    @Test
    void onlyPersonalAccounts() {
        ArrayList<String> strings = new ArrayList<String>() {{
            add("2-ACCNUM");
            add("1-ACCNUM");
            add("4-ACC1NUM");
        }};

        allAccountsByClientId.forEach(e -> assertTrue(strings.contains(e.getNumber())));
    }

    @Test
    void successGetAllAccountsByClientId() {
        assertEquals(1, (int) allAccountsByClientId.stream().filter(e -> e.getNumber().equals("4-ACC1NUM")).count());
    }

    @Test
    void failGetAllAccountsByClientId() {
        accountRepository = new AccountRepositoryImpl("somePath");
        assertThrows(FileNotFoundException.class, () -> {
            accountRepository.getAllAccountsByClientId(1L);
        });
    }


}