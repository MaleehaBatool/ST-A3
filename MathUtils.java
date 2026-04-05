package com.framework.utils;

/**
 * Mathematical utility class providing common operations.
 */
public class MathUtils {

    private MathUtils() {}

    public static int add(int a, int b) { return a + b; }
    public static int subtract(int a, int b) { return a - b; }
    public static int multiply(int a, int b) { return a * b; }

    public static double divide(double a, double b) {
        if (b == 0) throw new ArithmeticException("Division by zero");
        return a / b;
    }

    public static long factorial(int n) {
        if (n < 0) throw new IllegalArgumentException("Factorial not defined for negative numbers");
        if (n == 0 || n == 1) return 1;
        long result = 1;
        for (int i = 2; i <= n; i++) result *= i;
        return result;
    }

    public static boolean isPrime(int n) {
        if (n < 2) return false;
        if (n == 2) return true;
        if (n % 2 == 0) return false;
        for (int i = 3; i * i <= n; i += 2) {
            if (n % i == 0) return false;
        }
        return true;
    }

    public static int gcd(int a, int b) {
        if (a < 0 || b < 0) throw new IllegalArgumentException("Arguments must be non-negative");
        while (b != 0) { int t = b; b = a % b; a = t; }
        return a;
    }

    public static double power(double base, int exponent) {
        if (exponent < 0) throw new IllegalArgumentException("Exponent must be non-negative");
        return Math.pow(base, exponent);
    }

    public static double circleArea(double radius) {
        if (radius < 0) throw new IllegalArgumentException("Radius cannot be negative");
        return Math.PI * radius * radius;
    }

    public static double average(int... numbers) {
        if (numbers == null || numbers.length == 0)
            throw new IllegalArgumentException("Cannot compute average of empty array");
        int sum = 0;
        for (int n : numbers) sum += n;
        return (double) sum / numbers.length;
    }

    public static int clamp(int value, int min, int max) {
        if (min > max) throw new IllegalArgumentException("min cannot exceed max");
        return Math.max(min, Math.min(max, value));
    }
}
