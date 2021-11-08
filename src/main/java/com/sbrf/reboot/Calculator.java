package com.sbrf.reboot;

public class Calculator {
    public static long getAddition(long a, long b) {
        return Math.addExact(a, b);
    }

    public static long getSubtraction(long a, long b) {
        return Math.subtractExact(a, b);
    }

    public static long getMultiplication(long a, long b) {
        return Math.multiplyExact(a, b);
    }

    public static double getDivision(double a, double b) {
        if (b == 0.0) {
            throw new ArithmeticException("divided by zero");
        }
        return a / b;
    }

    public static double getMin(double a, double b) {
        return Math.min(a, b);
    }

    public static double getMax(double a, double b) {
        return Math.max(a, b);
    }

    public static long getAbs(long a) {
        if (a == Long.MIN_VALUE) {
            throw new ArithmeticException("Math.abs(Long.MIN_VALUE)");
        }
        return Math.abs(a);
    }
}
