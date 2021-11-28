package com.sbrf.reboot.atm.cassettes;

import com.sbrf.reboot.atm.Banknote;
import com.sbrf.reboot.atm.Cassette;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;

class CassetteTest {

    static class OneHundred extends Banknote {
    }

    static class OneThousand extends Banknote {
    }

    @Test
    void getCountBanknotes() {
        OneHundred oneHundred = new OneHundred();

        ArrayList<OneHundred> banknotes = new ArrayList<>();
        Collections.addAll(banknotes,
                oneHundred
//              new OneThousand(),   //it  will not compile
//              new Banknote()      //it will not compile
        );
        Cassette<OneHundred> cassette = new Cassette<>(banknotes);

        Assertions.assertEquals(1, cassette.getCountBanknotes());
    }
}