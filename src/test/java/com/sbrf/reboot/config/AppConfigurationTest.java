package com.sbrf.reboot.config;

import com.sbrf.reboot.dto.Customer;
import com.sbrf.reboot.dto.NAccount;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AppConfigurationTest {

    @Test
    void getAppContextFromXml() {
        ApplicationContext context = AppConfiguration.getAppContextFromXml();
        NAccount nAccount = context.getBean("beanNAccountFromXml1", NAccount.class);
        Customer customer = context.getBean("beanCustomerFromXml", Customer.class);

        assertEquals(2, customer.getNAccounts().count());
        assertEquals("Artem", customer.getName());
        assertTrue(customer.getNAccounts().anyMatch(a -> a.equals(nAccount)));
    }

    @Test
    void getAppContextFromAnnotation() {
        ApplicationContext context = AppConfiguration.getAppContextFromAnnotation();
        NAccount nAccount = context.getBean("beanNAccountFromAnnotation", NAccount.class);
        Customer customer = context.getBean("beanCustomerFromAnnotation", Customer.class);

        assertEquals(1, customer.getNAccounts().count());
        assertEquals("Anakin", customer.getName());
        assertTrue(customer.getNAccounts().anyMatch(a -> a.equals(nAccount)));
    }
}