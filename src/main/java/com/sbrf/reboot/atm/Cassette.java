package com.sbrf.reboot.atm;

import lombok.AllArgsConstructor;

import java.util.ArrayList;

/**
 * Представляет сущность кассеты с банкнотами
 *
 * @param <T> Может содержать банкноты разного номинала
 */
@AllArgsConstructor
public class Cassette<T extends Banknote> {
    private ArrayList<T> banknotes;

    /**
     * @return количество банкнот в кассете одного номинала
     */
    public int getCountBanknotes() {
        return banknotes.size();
    }
}
