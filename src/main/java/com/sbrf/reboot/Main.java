package com.sbrf.reboot;

import com.sbrf.reboot.dto.Account;
import com.sbrf.reboot.utils.AccountUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Account> accounts = new ArrayList<Account>() {{
            add(Account.builder().clientId(1L).createDate(LocalDate.now().minusDays(14)).balance(BigDecimal.TEN.multiply(new BigDecimal(7))).build());
            add(Account.builder().clientId(2L).createDate(LocalDate.now().minusDays(12)).balance(BigDecimal.TEN.multiply(new BigDecimal(6))).build());
            add(Account.builder().clientId(3L).createDate(LocalDate.now().minusDays(10)).balance(BigDecimal.TEN.multiply(new BigDecimal(5))).build());
            add(Account.builder().clientId(3L).createDate(LocalDate.now().minusDays(8)).balance(BigDecimal.TEN.multiply(new BigDecimal(4))).build());
            add(Account.builder().clientId(3L).createDate(LocalDate.now().minusDays(8)).balance(BigDecimal.TEN.multiply(new BigDecimal(3))).build());
            add(Account.builder().clientId(4L).createDate(LocalDate.now().minusDays(4)).balance(BigDecimal.TEN.multiply(new BigDecimal(2))).build());
            add(Account.builder().clientId(5L).createDate(LocalDate.now().minusDays(2)).balance(BigDecimal.TEN.multiply(new BigDecimal(1))).build());
            add(Account.builder().clientId(6L).createDate(LocalDate.now()).balance(BigDecimal.TEN).build());
        }};

        AccountUtils.sortedByIdDateBalance(accounts);

        accounts.forEach(System.out::println);
    }
}
