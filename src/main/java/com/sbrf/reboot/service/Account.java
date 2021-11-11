package com.sbrf.reboot.service;

import lombok.Getter;

public class Account {
    @Getter
    private long clientId;
    private String book;

    public Account(String book) {
        this.book = book;
    }
}
