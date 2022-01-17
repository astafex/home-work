package com.sbrf.reboot.config;

import com.sbrf.reboot.dto.Customer;
import com.sbrf.reboot.dto.Account;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SpringConfigurationTest {

    @Test
    void getAppContextFromXml() {
        ApplicationContext context = SpringConfiguration.getAppContextFromXml();
        Account account = context.getBean("beanAccountFromXml1", Account.class);
        Customer customer = context.getBean("beanCustomerFromXml", Customer.class);

        assertEquals(2, customer.getAccounts().count());
        assertEquals("Artem", customer.getName());
        assertTrue(customer.getAccounts().anyMatch(a -> a.equals(account)));
    }

    @Test
    void getAppContextFromAnnotation() {
        ApplicationContext context = SpringConfiguration.getAppContextFromAnnotation();
        Account account = context.getBean("beanAccountFromAnnotation", Account.class);
        Customer customer = context.getBean("beanCustomerFromAnnotation", Customer.class);

        assertEquals(1, customer.getAccounts().count());
        assertEquals("Anakin", customer.getName());
        assertTrue(customer.getAccounts().anyMatch(a -> a.equals(account)));
    }
}