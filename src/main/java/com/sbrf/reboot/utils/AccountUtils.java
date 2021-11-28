package com.sbrf.reboot.utils;

import com.sbrf.reboot.dto.Account;

import java.util.Comparator;
import java.util.List;

public class AccountUtils {

    /**
     * Метод производит сортировку списков объектов 'Account' в порядке возрастания по идентификатору
     *
     * @param accounts сортируемый список содержащий объекты типа 'Account' и наследников
     */
    public static void sortedById(List<? extends Account> accounts) {
        Comparator<Account> accountClientIdComparator = Comparator.comparing(Account::getClientId);
        accounts.sort(accountClientIdComparator);
    }

    /**
     * Метод производит сортировку списков объектов 'Account' в порядке возрастания по идентификатору и даты создания (от старых к новым)
     *
     * @param accounts сортируемый список содержащий объекты типа 'Account' и наследников
     */
    public static void sortedByIdDate(List<? extends Account> accounts) {
        accounts.sort((Comparator<Account>) (a1, a2) -> {
            if (a1 == null || a2 == null) {
                throw new NullPointerException();
            }
            if (a1.getClientId() != a2.getClientId()) {
                return Long.compare(a1.getClientId(), a2.getClientId());
            }
            return a1.getCreateDate().compareTo(a2.getCreateDate());
        });
    }

    /**
     * Метод производит сортировку списков объектов 'Account' в порядке возрастания по идентификатору, даты создания (от старых к новым)
     * и балансу счета
     *
     * @param accounts сортируемый список содержащий объекты типа 'Account' и наследников
     */
    public static void sortedByIdDateBalance(List<? extends Account> accounts) {
        accounts.sort((Comparator<Account>) (a1, a2) -> {
            if (a1 == null || a2 == null) {
                throw new NullPointerException();
            }
            if (a1.getClientId() != a2.getClientId()) {
                return Long.compare(a1.getClientId(), a2.getClientId());
            }
            if (!(a1.getCreateDate().equals(a2.getCreateDate()))) {
                return a1.getCreateDate().compareTo(a2.getCreateDate());
            }
            return a2.getBalance().compareTo(a1.getBalance());
        });
    }
}

