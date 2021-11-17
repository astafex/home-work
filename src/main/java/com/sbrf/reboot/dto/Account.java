package com.sbrf.reboot.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Account {
    private long clientId;
    private String number;

    public Account(String number) {
        this.number = number;
    }
}
