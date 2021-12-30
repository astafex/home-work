package com.sbrf.reboot.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Stream;

@Getter
@Setter
@ToString
@Component("beanCustomerFromAnnotation")
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

    public Customer(int age, String name, Set<NAccount> nAccounts) {
        this.age = age;
        this.name = name;
        this.nAccounts = nAccounts;
    }
}
