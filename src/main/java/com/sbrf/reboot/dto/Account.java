package com.sbrf.reboot.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class Account {
    /**
     * Баланс счета
     */
    private BigDecimal balance;
    /**
     * Вид валюты
     */
    private Currency currency;
    /**
     * Дата создания счета
     */
    private LocalDate createDate;
}
