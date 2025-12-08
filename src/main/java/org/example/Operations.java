package org.example;

public class Operations {

    public static long factorial (int number) {
        if (number < 0) {
            throw new IllegalArgumentException("Отрицательный факториал!");
        }
        long result = 1;
        for (int i = 2; i <= number; i++) {
            result *= i;
        }
        return result;
    }

    public static double trArea (double a, double b, double c) {
        double s = (a + b + c) / 2;
        return Math.sqrt(s * (s - a) * (s - b) * (s - c));
    }

    public static int add (int a, int b) {
        return a + b;
    }

    public static int subtract (int a, int b) {
        return a - b;
    }

    public static int multiply (int a, int b) {
        return a * b;
    }

    public static double divide (int a, int b) {
        if (b == 0) {
            throw new ArithmeticException("Деление на 0!");
        }
        return (double) a / b;
    }

    public static boolean greater (int a, int b) {
        return a > b;
    }

    public static boolean lesser (int a, int b) {
        return a < b;
    }

    public static boolean equal (int a, int b) {
        return a == b;
    }
}