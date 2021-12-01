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
    private long clientId;
    private String number;
    private BigDecimal balance;
    private LocalDate createDate = LocalDate.now();

    public Account(String number) {
        this.number = number;
    }

    public Account(long clientId, String number) {
        this.clientId = clientId;
        this.number = number;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }
}





