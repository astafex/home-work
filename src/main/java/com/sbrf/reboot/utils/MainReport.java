package com.sbrf.reboot.utils;

import com.sbrf.reboot.dto.Currency;
import com.sbrf.reboot.dto.Customer;
import com.sbrf.reboot.dto.NAccount;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class MainReport {

    private final static Predicate<Customer> FILTER_BY_AGE = (customer) -> customer.getAge() >= 18 && customer.getAge() <= 30;
    private final static Predicate<NAccount> FILTER_BY_CREATE_DATE = (nAccount) -> nAccount.getCreateDate().isAfter(LocalDate.of(2021, 7, 1))
            && nAccount.getCreateDate().isBefore(LocalDate.of(2021, 8, 1));
    private final static Predicate<NAccount> FILTER_BY_CURRENCY = (nAccount) -> nAccount.getCurrency().equals(Currency.RUB);

    /**
     * Метод возвращает сумму остатков на счетах всех клиентов, c использованием {@link CompletableFuture}.
     * <p> Критерии фильтрации счетов:</p>
     * <p>  - Возраст клиента от 18 до 30 ({@link MainReport#FILTER_BY_AGE})</p>
     * <p>  - Счета с рублевой валютой ({@link MainReport#FILTER_BY_CURRENCY})</p>
     * <p>  - Счета созданы в период между 01/07/2021 и 01/08/2021 ({@link MainReport#FILTER_BY_CREATE_DATE})</p>
     *
     * @param streamCustomers поток клиентов, Customer
     * @return сумма рублевых балансов клиентов в формате BigDecimal
     */
    public static CompletableFuture<BigDecimal> getTotalsWithCompletableFuture(Stream<Customer> streamCustomers) {
        ExecutorService executorService = Executors.newWorkStealingPool();

        return CompletableFuture.supplyAsync(() -> streamCustomers
                .filter(FILTER_BY_AGE)
                .flatMap(customer -> customer.getNAccounts()
                        .filter(FILTER_BY_CREATE_DATE)
                        .filter(FILTER_BY_CURRENCY)
                        .map(NAccount::getBalance))
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO), executorService);
    }


    /**
     * Метод возвращает сумму остатков на счетах всех клиентов, c использованием {@link Mono}.
     * <p> Критерии фильтрации счетов:</p>
     * <p>  - Возраст клиента от 18 до 30 ({@link MainReport#FILTER_BY_AGE})</p>
     * <p>  - Счета с рублевой валютой ({@link MainReport#FILTER_BY_CURRENCY})</p>
     * <p>  - Счета созданы в период между 01/07/2021 и 01/08/2021 ({@link MainReport#FILTER_BY_CREATE_DATE})</p>
     *
     * @param streamCustomers поток клиентов, Customer
     * @return сумма рублевых балансов клиентов в формате BigDecimal
     */
    public static Mono<BigDecimal> getTotalsWithReact(Stream<Customer> streamCustomers) {

        return Flux
                .fromStream(streamCustomers)
                .publishOn(Schedulers.parallel())
                .filter(FILTER_BY_AGE)
                .flatMap(customer -> Flux
                        .fromStream(customer.getNAccounts())
                        .filter(FILTER_BY_CREATE_DATE)
                        .filter(FILTER_BY_CURRENCY)
                        .map(NAccount::getBalance))
                .reduce(BigDecimal::add);
    }
}

