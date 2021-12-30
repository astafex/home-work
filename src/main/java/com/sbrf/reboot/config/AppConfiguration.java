package com.sbrf.reboot.config;

import com.sbrf.reboot.dto.Currency;
import com.sbrf.reboot.dto.NAccount;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;
import java.time.LocalDate;

@Configuration
@ComponentScan("com.sbrf.reboot.dto")
public class AppConfiguration {
    static void main(String[] args) {
    }

    @Bean
    public static int getBeenAge() {
        return 25;
    }

    @Bean
    public static String getBeenName() {
        return "Anakin";
    }

    @Bean("beanNAccountFromAnnotation")
    public static NAccount getBeenNAccount() {
        return new NAccount(BigDecimal.TEN, Currency.EUR, LocalDate.of(2021, 12, 1));
    }

    public static ApplicationContext getAppContextFromXml() {
        return new ClassPathXmlApplicationContext("applicationContext.xml");
    }

    public static ApplicationContext getAppContextFromAnnotation() {
        return new AnnotationConfigApplicationContext(AppConfiguration.class);
    }
}