package com.sbrf.reboot.service;

import com.sbrf.reboot.dto.Account;
import com.sbrf.reboot.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class AccountServiceTest {

    AccountRepository accountRepository;
    AccountService accountService;
    Set<Account> accounts;

    @BeforeEach
    void setUp() {
        accountRepository = Mockito.mock(AccountRepository.class);
        accountService = new AccountService(accountRepository);
    }

    @Test
    void bookExist() throws IOException {
        Account account = new Account("ACC1234NUM");
        accounts = new HashSet<>();
        accounts.add(account);

        when(accountRepository.getAllAccountsByClientId(1L)).thenReturn(accounts);
        assertTrue(accountService.isAccountExist(1L, account));
    }

    @Test
    void bookNotExist() throws IOException {
        Set<Account> accounts = new HashSet<>();
        accounts.add(new Account("ACC1234NUM"));

        when(accountRepository.getAllAccountsByClientId(1L)).thenReturn(accounts);
        assertFalse(accountService.isAccountExist(1L, new Account("ACC456NUM")));
    }

    @Test
    void getMaxAccountBalanceTest() throws IOException {
        accounts = new HashSet<>();
        Account account = Account.builder().clientId(1L).balance(new BigDecimal(10000)).build();

        Collections.addAll(accounts,
                Account.builder().clientId(1L).balance(new BigDecimal(1000)).build(),
                Account.builder().clientId(1L).balance(new BigDecimal(100)).build(),
                account,
                Account.builder().clientId(1L).balance(new BigDecimal(10)).build()
        );

        when(accountRepository.getAllAccountsByClientId(1L)).thenReturn(accounts);
        assertEquals(account, accountService.getMaxAccountBalance(1L).get());
    }

    @Test
    void getAllAccountsByDateMoreThenTest() throws IOException {
        accounts = new HashSet<>();
        Set<Account> accountsByDateMoreThenTest = new HashSet<>();
        Account account1 = Account.builder().clientId(1L).createDate(LocalDate.of(2021, 12, 15)).build();
        Account account2 = Account.builder().clientId(1L).createDate(LocalDate.of(2021, 12, 20)).build();

        Collections.addAll(accountsByDateMoreThenTest,
                account1, account2);

        Collections.addAll(accounts,
                account1, account2,
                Account.builder().clientId(1L).createDate(LocalDate.of(2021, 11, 1)).build());

        when(accountRepository.getAllAccountsByClientId(1L)).thenReturn(accounts);
        assertEquals(accountsByDateMoreThenTest,
                accountService.getAllAccountsByDateMoreThen(1L, LocalDate.of(2021, 12, 10)).collect(Collectors.toSet())
        );
    }

    @Test
    void getSumOfBalancesAccountsByClientId() throws IOException {
        accounts = new HashSet<>();

        Collections.addAll(accounts,
                Account.builder().clientId(1L).balance(new BigDecimal(100)).build(),
                Account.builder().clientId(1L).balance(new BigDecimal(10)).build(),
                Account.builder().clientId(1L).balance(new BigDecimal(1)).build()
        );

        when(accountRepository.getAllAccountsByClientId(1L)).thenReturn(accounts);
        assertEquals(new BigDecimal(111), accountService.getSumOfBalancesAccountsByClientId(1L));
    }
}