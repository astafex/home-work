package com.sbrf.reboot.calculator;

import com.sbrf.reboot.Calculator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CalculatorTest {
    @Test
    void getAddition() {
        assertEquals(9, Calculator.getAddition(4, 5));
        assertThrows(ArithmeticException.class, () -> Calculator.getAddition(Long.MAX_VALUE, 1));
    }

    @Test
    void getSubtraction() {
        assertEquals(-1, Calculator.getSubtraction(4, 5));
        assertThrows(ArithmeticException.class, () -> Calculator.getSubtraction(Long.MIN_VALUE, 1));

    }

    @Test
    void getMultiplication() {
        assertEquals(20, Calculator.getMultiplication(4, 5));
        assertThrows(ArithmeticException.class, () -> Calculator.getMultiplication(Long.MAX_VALUE, 2));
    }

    @Test
    void getDivision() {
        assertEquals(3, Calculator.getDivision(9, 3));
        assertThrows(ArithmeticException.class, () -> Calculator.getDivision(5, 0));
    }

    @Test
    void getMin() {
        assertEquals(2, Calculator.getMin(10, 2));
        assertEquals(5, Calculator.getMin(5, 51));
    }

    @Test
    void getMax() {
        assertEquals(15, Calculator.getMax(15, 14));
        assertEquals(1, Calculator.getMax(1, 0));
    }

    @Test
    void getAbs() {
        assertEquals(12, Calculator.getAbs(12));
        assertEquals(5, Calculator.getAbs(-5));
        assertThrows(ArithmeticException.class, () -> Calculator.getAbs(Long.MIN_VALUE));
    }

    @Test
    void classHasSevenMethods() {
        assertEquals(7, Calculator.class.getMethods().length - Object.class.getMethods().length);
    }
}