package com.sbrf.reboot.utils;

import com.sbrf.reboot.dto.Account;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AccountUtilsTest {

    @Test
    void sortedById() {
        List<Account> accounts = new ArrayList<>();
        Collections.addAll(
                accounts,
                Account.builder().clientId(3L).createDate(LocalDate.now()).balance(BigDecimal.TEN).build(),
                Account.builder().clientId(1L).createDate(LocalDate.now()).balance(BigDecimal.TEN).build(),
                Account.builder().clientId(3L).createDate(LocalDate.now()).balance(BigDecimal.TEN).build(),
                Account.builder().clientId(2L).createDate(LocalDate.now()).balance(BigDecimal.TEN).build()
        );
        AccountUtils.sortedById(accounts);

        assertEquals(1L, accounts.get(0).getClientId());
        assertEquals(2L, accounts.get(1).getClientId());
        assertEquals(3L, accounts.get(2).getClientId());
        assertEquals(3L, accounts.get(3).getClientId());

    }

    @Test
    void sortedByIdDate() {
        List<Account> accounts = new ArrayList<>();
        Collections.addAll(
                accounts,
                Account.builder().clientId(1L).createDate(LocalDate.now().minusDays(4)).balance(BigDecimal.TEN).build(),
                Account.builder().clientId(3L).createDate(LocalDate.now().minusDays(3)).balance(BigDecimal.TEN).build(),
                Account.builder().clientId(3L).createDate(LocalDate.now().minusDays(1)).balance(BigDecimal.TEN).build(),
                Account.builder().clientId(2L).createDate(LocalDate.now()).balance(BigDecimal.TEN).build()
        );

        AccountUtils.sortedByIdDate(accounts);

        assertEquals(1L, accounts.get(0).getClientId());
        assertEquals(2L, accounts.get(1).getClientId());
        assertEquals(LocalDate.now().minusDays(3), accounts.get(2).getCreateDate());
        assertEquals(LocalDate.now().minusDays(1), accounts.get(3).getCreateDate());
    }

    @Test
    void sortedByIdDateBalance() {
        List<Account> accounts = new ArrayList<>();
        Collections.addAll(accounts,
                Account.builder().clientId(4L).createDate(LocalDate.now().minusDays(4)).balance(BigDecimal.TEN.multiply(new BigDecimal(2))).build(),
                Account.builder().clientId(3L).createDate(LocalDate.now().minusDays(8)).balance(BigDecimal.TEN.multiply(new BigDecimal(4))).build(),
                Account.builder().clientId(6L).createDate(LocalDate.now()).balance(BigDecimal.TEN).build(),
                Account.builder().clientId(3L).createDate(LocalDate.now().minusDays(8)).balance(BigDecimal.TEN.multiply(new BigDecimal(3))).build(),
                Account.builder().clientId(3L).createDate(LocalDate.now().minusDays(10)).balance(BigDecimal.TEN.multiply(new BigDecimal(5))).build(),
                Account.builder().clientId(1L).createDate(LocalDate.now().minusDays(14)).balance(BigDecimal.TEN.multiply(new BigDecimal(7))).build(),
                Account.builder().clientId(2L).createDate(LocalDate.now().minusDays(12)).balance(BigDecimal.TEN.multiply(new BigDecimal(6))).build(),
                Account.builder().clientId(5L).createDate(LocalDate.now().minusDays(2)).balance(BigDecimal.TEN.multiply(new BigDecimal(1))).build()
        );

        AccountUtils.sortedByIdDateBalance(accounts);

        // проверка сортировки по 'clientId'
        assertEquals(1L, accounts.get(0).getClientId());
        assertEquals(4L, accounts.get(5).getClientId());
        assertEquals(6L, accounts.get(7).getClientId());
        // проверка сортировки по 'createDate'
        assertEquals(LocalDate.now().minusDays(10), accounts.get(2).getCreateDate());
        assertEquals(LocalDate.now().minusDays(4), accounts.get(5).getCreateDate());
        assertEquals(LocalDate.now(), accounts.get(7).getCreateDate());
        // проверка сортировки по 'balance'
        assertEquals(BigDecimal.TEN.multiply(new BigDecimal(7)), accounts.get(0).getBalance());
        assertEquals(BigDecimal.TEN.multiply(new BigDecimal(4)), accounts.get(3).getBalance());
        assertEquals(BigDecimal.TEN.multiply(new BigDecimal(3)), accounts.get(4).getBalance());
    }
}