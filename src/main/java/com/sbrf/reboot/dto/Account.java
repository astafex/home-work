package com.sbrf.reboot.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@EqualsAndHashCode
@Builder
@Getter
@Setter
@AllArgsConstructor
public class Account {
    /**
     * Уникальный идентификатор клиента
     */
    @Setter(AccessLevel.NONE)
    private long clientId;
    /**
     * Номер счета клиента
     */
    private String number;
    /**
     * Остаток на счете
     */
    private BigDecimal balance;
    /**
     * Дата создания счета
     */
    @Setter(AccessLevel.NONE)
    private LocalDate createDate = LocalDate.now();

    public Account(String number) {
        this.number = number;
    }

    public Account(long clientId, String number) {
        this.clientId = clientId;
        this.number = number;
    }
}





