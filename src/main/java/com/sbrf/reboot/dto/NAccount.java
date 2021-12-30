package com.sbrf.reboot.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class NAccount {
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

