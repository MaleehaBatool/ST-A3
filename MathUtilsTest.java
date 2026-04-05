package com.framework.tests;

import com.framework.utils.MathUtils;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for MathUtils utility class.
 */
@DisplayName("MathUtils Tests")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Tag("fast")
class MathUtilsTest {

    // ---- POSITIVE TESTS ----

    @Test
    @Order(1)
    @DisplayName("should_returnCorrectSum_when_twoPositiveNumbersAdded")
    void should_returnCorrectSum_when_twoPositiveNumbersAdded() {
        assertEquals(7, MathUtils.add(3, 4));
    }

    @Test
    @Order(2)
    @DisplayName("should_returnCorrectDifference_when_numberSubtracted")
    void should_returnCorrectDifference_when_numberSubtracted() {
        assertEquals(5, MathUtils.subtract(10, 5));
    }

    @Test
    @Order(3)
    @DisplayName("should_returnCorrectProduct_when_twoNumbersMultiplied")
    void should_returnCorrectProduct_when_twoNumbersMultiplied() {
        assertEquals(12, MathUtils.multiply(3, 4));
    }

    @Test
    @Order(4)
    @DisplayName("should_returnCorrectQuotient_when_divisionIsValid")
    void should_returnCorrectQuotient_when_divisionIsValid() {
        assertEquals(2.5, MathUtils.divide(5, 2), 0.001);
    }

    @Test
    @Order(5)
    @DisplayName("should_returnTrue_when_numberIsPrime")
    void should_returnTrue_when_numberIsPrime() {
        assertTrue(MathUtils.isPrime(7));
        assertTrue(MathUtils.isPrime(13));
        assertTrue(MathUtils.isPrime(97));
    }

    @Test
    @Order(6)
    @DisplayName("should_returnCorrectFactorial_when_validNonNegativeInput")
    void should_returnCorrectFactorial_when_validNonNegativeInput() {
        assertEquals(120, MathUtils.factorial(5));
        assertEquals(1, MathUtils.factorial(0));
        assertEquals(1, MathUtils.factorial(1));
    }

    @Test
    @Order(7)
    @DisplayName("should_returnCorrectGCD_when_twoPositiveNumbersProvided")
    void should_returnCorrectGCD_when_twoPositiveNumbersProvided() {
        assertEquals(6, MathUtils.gcd(12, 18));
        assertEquals(1, MathUtils.gcd(7, 13));
    }

    // ---- NEGATIVE TESTS ----

    @Test
    @Order(8)
    @DisplayName("should_throwArithmeticException_when_dividingByZero")
    void should_throwArithmeticException_when_dividingByZero() {
        ArithmeticException ex = assertThrows(ArithmeticException.class,
                () -> MathUtils.divide(10, 0));
        assertEquals("Division by zero", ex.getMessage());
    }

    @Test
    @Order(9)
    @DisplayName("should_throwException_when_factorialOfNegativeNumber")
    void should_throwException_when_factorialOfNegativeNumber() {
        assertThrows(IllegalArgumentException.class, () -> MathUtils.factorial(-1));
    }

    @Test
    @Order(10)
    @DisplayName("should_returnFalse_when_numberIsNotPrime")
    void should_returnFalse_when_numberIsNotPrime() {
        assertFalse(MathUtils.isPrime(1));
        assertFalse(MathUtils.isPrime(0));
        assertFalse(MathUtils.isPrime(9));
    }

    // ---- BOUNDARY TESTS ----

    @Test
    @Order(11)
    @DisplayName("should_clampValue_when_valueExceedsBounds")
    void should_clampValue_when_valueExceedsBounds() {
        assertEquals(10, MathUtils.clamp(50, 0, 10));
        assertEquals(0, MathUtils.clamp(-5, 0, 10));
        assertEquals(5, MathUtils.clamp(5, 0, 10));
    }

    @ParameterizedTest(name = "add({0}, {1}) = {2}")
    @CsvFileSource(resources = "/data/math_test_data.csv", numLinesToSkip = 1)
    @DisplayName("should_returnCorrectSum_when_csvDataProvided")
    @Order(12)
    void should_returnCorrectSum_when_csvDataProvided(int a, int b, int expected) {
        assertEquals(expected, MathUtils.add(a, b));
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 3, 5, 7, 11, 13})
    @DisplayName("should_returnTrue_when_primesProvidedAsValues")
    @Order(13)
    void should_returnTrue_when_primesProvidedAsValues(int n) {
        assertTrue(MathUtils.isPrime(n));
    }
}
