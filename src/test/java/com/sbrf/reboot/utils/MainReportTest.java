package com.sbrf.reboot.utils;

import com.sbrf.reboot.dto.Currency;
import com.sbrf.reboot.dto.Customer;
import com.sbrf.reboot.dto.Account;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MainReportTest {

    Set<Account> accountsEric;
    Set<Account> accountsKyle;
    Set<Account> accountsKenny;
    Set<Customer> customers;

    @BeforeEach
    void setUp() {
        accountsEric = new HashSet<>();
        Collections.addAll(accountsEric,
                new Account(BigDecimal.ONE, Currency.RUB, LocalDate.of(2021, 7, 20)),
                new Account(BigDecimal.TEN, Currency.JPY, LocalDate.of(2021, 7, 30)));
        accountsKyle = new HashSet<>();
        Collections.addAll(accountsKyle,
                new Account(BigDecimal.ONE, Currency.RUB, LocalDate.of(2021, 7, 10)),
                new Account(BigDecimal.TEN, Currency.USD, LocalDate.of(2021, 10, 1)));
        accountsKenny = new HashSet<>();
        Collections.addAll(accountsKenny,
                new Account(BigDecimal.ONE, Currency.RUB, LocalDate.of(2021, 7, 10)),
                new Account(BigDecimal.ONE, Currency.RUB, LocalDate.of(2021, 7, 15)));

        customers = new HashSet<>();
        Collections.addAll(customers,
                new Customer(18, "Eric", accountsEric),
                new Customer(20, "Kyle", accountsKyle),
                new Customer(30, "Kenny", accountsKenny));
    }

    @Test
    @SneakyThrows
    void getTotalsWithCompletableFutureTest() {
        CompletableFuture<BigDecimal> cfSum = MainReport.getTotalsWithCompletableFuture(customers.stream());
        assertEquals(new BigDecimal(4), cfSum.get());
    }

//    @Test
//    void getTotalsWithReactTest() {
//        Mono<BigDecimal> monoSum = MainReport.getTotalsWithReact(customers.stream());
//        assertEquals(new BigDecimal(4), monoSum.block(Duration.ofMillis(50)));
//    }
}