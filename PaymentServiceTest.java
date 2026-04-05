package org.example;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentServiceTest {

    PaymentService ps = new PaymentService();

    @ParameterizedTest
    @CsvFileSource(resources = "/data.csv", numLinesToSkip = 1)
    void should_test_payment_using_csv(double amount, boolean expected) {

        if (amount <= 0) {
            assertThrows(IllegalArgumentException.class,
                    () -> ps.processPayment(amount));
        } else {
            assertEquals(expected, ps.processPayment(amount));
        }
    }
}