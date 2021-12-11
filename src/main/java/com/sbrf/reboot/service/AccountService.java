package com.sbrf.reboot.service;

import com.sbrf.reboot.dto.Account;
import com.sbrf.reboot.repository.AccountRepository;
import lombok.AllArgsConstructor;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

@AllArgsConstructor
public class AccountService {
    private AccountRepository accountRepository;

    /**
     * Общий метод для класса, позволяет получить из репозитория множество аккаунтов
     * клиента по его уникальному идентификатору
     * (у одного клиента может быть несколько счетов)
     *
     * @param clientId уникальный идентификатор клиента
     * @return множество аккаунтов по указанному идентификатору клиента
     * @throws IOException при любых проблемах получения(вывода) множества из репозитория
     */
    private Set<Account> getAccounts(long clientId) throws IOException {
        return accountRepository.getAllAccountsByClientId(clientId);
    }

    /**
     * Метод проверяет есть ли у клиента с указанным идентификатором передаваемый аккаунт
     *
     * @param clientId уникальный идентификатор клиента
     * @param account  аккаунт клиента
     * @return булевое значение; <b>true</b> - если у клиента с указанным идентификатором
     * есть аккаунт. Иначе <b>false</b>
     * @throws IOException при любых проблемах получения(вывода) множества из репозитория
     */
    public boolean isAccountExist(long clientId, Account account) throws IOException {
        Set<Account> accounts = getAccounts(clientId);
        return accounts.contains(account);
    }

    /**
     * Метод возвращает аккаунт с наибольшим остатком на счету клиента
     *
     * @param clientId уникальный идентификатор клиента
     * @return аккаунт с максимальным остатком на счете
     * @throws IOException при любых проблемах получения(вывода) множества из репозитория
     */
    public Optional<Account> getMaxAccountBalance(long clientId) throws IOException {
        Set<Account> accounts = getAccounts(clientId);
        return accounts.stream().max(Comparator.comparing(Account::getBalance));
    }

    /**
     * Метод возвращает поток содержащий множество аккаунтов клиента, которые были созданы после указанной даты
     * @param clientId уникальный идентификатор клиента
     * @param localDate дата создания
     * @return поток содержащий множество аккаунтов
     * @throws IOException при любых проблемах получения(вывода) множества из репозитория
     */
    public Stream<Account> getAllAccountsByDateMoreThen(long clientId, LocalDate localDate) throws IOException {
        Set<Account> accounts = getAccounts(clientId);
        return accounts.stream().filter(acc -> localDate.isBefore(acc.getCreateDate()));
    }

    /**
     * Метод возвращает сумму всех остатков на счетах клиента
     *
     * @param clientId уникальный идентификатор клиента
     * @return сумму всех остатков на балансах аккаунтов клиента
     * @throws IOException при любых проблемах получения(вывода) множества из репозитория
     */
    public BigDecimal getSumOfBalancesAccountsByClientId(long clientId) throws IOException {
        Set<Account> accounts = getAccounts(clientId);
        return accounts.stream()
                .map(Account::getBalance)
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.valueOf(0));
    }

}
