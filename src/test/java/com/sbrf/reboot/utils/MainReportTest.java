package com.sbrf.reboot.utils;

import com.sbrf.reboot.dto.Currency;
import com.sbrf.reboot.dto.Customer;
import com.sbrf.reboot.dto.NAccount;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.time.temporal.TemporalUnit;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MainReportTest {

    Set<NAccount> nAccountsEric;
    Set<NAccount> nAccountsKyle;
    Set<NAccount> nAccountsKenny;
    Set<Customer> customers;

    @BeforeEach
    void setUp() {
        nAccountsEric = new HashSet<>();
        Collections.addAll(nAccountsEric,
                new NAccount(BigDecimal.ONE, Currency.RUB, LocalDate.of(2021, 7, 20)),
                new NAccount(BigDecimal.TEN, Currency.JPY, LocalDate.of(2021, 7, 30)));
        nAccountsKyle = new HashSet<>();
        Collections.addAll(nAccountsKyle,
                new NAccount(BigDecimal.ONE, Currency.RUB, LocalDate.of(2021, 7, 10)),
                new NAccount(BigDecimal.TEN, Currency.USD, LocalDate.of(2021, 10, 1)));
        nAccountsKenny = new HashSet<>();
        Collections.addAll(nAccountsKenny,
                new NAccount(BigDecimal.ONE, Currency.RUB, LocalDate.of(2021, 7, 10)),
                new NAccount(BigDecimal.ONE, Currency.RUB, LocalDate.of(2021, 7, 15)));

        customers = new HashSet<>();
        Collections.addAll(customers,
                new Customer(18, "Eric", nAccountsEric),
                new Customer(20, "Kyle", nAccountsKyle),
                new Customer(30, "Kenny", nAccountsKenny));
    }

    @Test
    @SneakyThrows
    void getTotalsWithCompletableFutureTest() {
        CompletableFuture<BigDecimal> cfSum = MainReport.getTotalsWithCompletableFuture(customers.stream());
        assertEquals(new BigDecimal(4), cfSum.get());
    }

    @Test
    void getTotalsWithReactTest() {
        Mono<BigDecimal> monoSum = MainReport.getTotalsWithReact(customers.stream());
        assertEquals(new BigDecimal(4), monoSum.block(Duration.ofMillis(50)));
    }
}