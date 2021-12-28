package com.sbrf.reboot.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;
import java.util.stream.Stream;

@Getter
@AllArgsConstructor
public class Customer {
    /**
     * Возраст клиента
     */
    private int age;
    /**
     * Имя клиента
     */
    private String name;
    /**
     * Счета клиента
     */
    @Getter(AccessLevel.NONE)
    private Set<NAccount> nAccounts;

    public Stream<NAccount> getNAccounts() {
        return nAccounts.stream();
    }
}