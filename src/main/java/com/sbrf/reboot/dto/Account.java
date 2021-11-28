package com.sbrf.reboot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

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





