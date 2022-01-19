package com.sbrf.reboot.utils;

import com.sbrf.reboot.dto.Account;
import com.sbrf.reboot.dto.Currency;
import com.sbrf.reboot.dto.Customer;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainReport {

    private final static Predicate<Customer> FILTER_BY_AGE = (customer) -> customer.getAge() >= 18 && customer.getAge() <= 30;
    private final static Predicate<Account> FILTER_BY_CREATE_DATE = (account) -> account.getCreateDate().isAfter(LocalDate.of(2021, 7, 1))
            && account.getCreateDate().isBefore(LocalDate.of(2021, 8, 1));
    private final static Predicate<Account> FILTER_BY_CURRENCY = (account) -> account.getCurrency().equals(Currency.RUB);

    /**
     * Метод возвращает сумму остатков на счетах всех клиентов, c использованием {@link CompletableFuture}.
     * Расчет по каждому клиенту происходит в отдельном потоке.
     * <p> Критерии фильтрации счетов:</p>
     * <p>  - Возраст клиента от 18 до 30 ({@link MainReport#FILTER_BY_AGE})</p>
     * <p>  - Счета с рублевой валютой ({@link MainReport#FILTER_BY_CURRENCY})</p>
     * <p>  - Счета созданы в период между 01/07/2021 и 01/08/2021 ({@link MainReport#FILTER_BY_CREATE_DATE})</p>
     *
     * @param streamCustomers поток клиентов, Customer
     * @return сумма рублевых балансов клиентов в формате BigDecimal
     */
    public static CompletableFuture<BigDecimal> getTotalsWithCompletableFuture(Stream<Customer> streamCustomers) {
        List<CompletableFuture<BigDecimal>> futuresBigDecimalList = streamCustomers
                .filter(FILTER_BY_AGE)
                .map(customer -> CompletableFuture.supplyAsync(() -> customer
                        .getAccounts()
                        .filter(FILTER_BY_CREATE_DATE)
                        .filter(FILTER_BY_CURRENCY)
                        .map(Account::getBalance)
                        .reduce(BigDecimal::add)
                        .orElse(BigDecimal.ZERO)))
                .collect(Collectors.toList());

        CompletableFuture<Void> allFutures = CompletableFuture.allOf(futuresBigDecimalList.toArray(new CompletableFuture[0]));

        return allFutures.thenApply(v -> futuresBigDecimalList
                .stream()
                .map(CompletableFuture::join)
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO));
    }


    /**
     * Метод возвращает сумму остатков на счетах всех клиентов, c использованием {@link Mono}.
     * Расчет по каждому клиенту происходит в отдельном потоке.
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
                .flatMap(customer -> Flux.fromStream(customer.getAccounts())
                        .filter(FILTER_BY_CREATE_DATE)
                        .filter(FILTER_BY_CURRENCY)
                        .map(Account::getBalance))
                .reduce(BigDecimal::add);
    }
}


