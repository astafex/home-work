package com.sbrf.reboot.calculator;

public class Calculator {
    public static double getAddition(double a, double b) {
        return a + b;
    }

    public static double getSubtraction(double a, double b) {
        return a - b;
    }

    public static double getMultiplication(double a, double b) {
        return a * b;
    }

    public static double getDivision(double a, double b) {
        return a / b;
    }

    public static long getMin(long a, long b) {
        return Math.min(a, b);
    }

    public static long getMax(long a, long b) {
        return Math.max(a, b);
    }

    public static long getAbs(long a) {
        return (a < 0) ? -a : a;
    }
}
