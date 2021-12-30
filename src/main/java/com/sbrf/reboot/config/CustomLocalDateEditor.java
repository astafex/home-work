package com.sbrf.reboot.config;

import java.beans.PropertyEditorSupport;
import java.time.LocalDate;

public class CustomLocalDateEditor extends PropertyEditorSupport {

    public CustomLocalDateEditor() {
    }

    private LocalDate parseText(String text) {
        LocalDate localDate;
        localDate = LocalDate.parse(text);
        return localDate;
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        setValue(parseText(text));
    }

    @Override
    public String getAsText() {
        LocalDate value = parseText(String.valueOf(getValue()));
        return (value != null ? value.toString() : "");
    }

}