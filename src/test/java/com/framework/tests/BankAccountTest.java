package com.framework.tests;

import com.framework.model.BankAccount;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Comprehensive unit tests for BankAccount class.
 * Follows AAA pattern and should_expectedBehavior_when_condition naming.
 */
@DisplayName("BankAccount Tests")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Tag("fast")
class BankAccountTest {

    private BankAccount account;
    private static final double INITIAL_BALANCE = 1000.0;

    @BeforeEach
    void setUp() {
        account = new BankAccount("ACC001", "Alice", INITIAL_BALANCE);
    }

    // ---- POSITIVE TESTS ----

    @Test
    @Order(1)
    @DisplayName("should_createAccountSuccessfully_when_validParametersProvided")
    void should_createAccountSuccessfully_when_validParametersProvided() {
        // Arrange
        String id = "ACC002", owner = "Bob";
        double balance = 500.0;
        // Act
        BankAccount acc = new BankAccount(id, owner, balance);
        // Assert
        assertEquals(id, acc.getAccountId());
        assertEquals(owner, acc.getOwner());
        assertEquals(balance, acc.getBalance());
        assertFalse(acc.isFrozen());
    }

    @Test
    @Order(2)
    @DisplayName("should_increaseBalance_when_depositIsMade")
    void should_increaseBalance_when_depositIsMade() {
        // Arrange
        double depositAmount = 200.0;
        // Act
        account.deposit(depositAmount);
        // Assert
        assertEquals(INITIAL_BALANCE + depositAmount, account.getBalance(), 0.001);
    }

    @Test
    @Order(3)
    @DisplayName("should_decreaseBalance_when_withdrawalIsMade")
    void should_decreaseBalance_when_withdrawalIsMade() {
        // Arrange
        double withdrawAmount = 300.0;
        // Act
        account.withdraw(withdrawAmount);
        // Assert
        assertEquals(INITIAL_BALANCE - withdrawAmount, account.getBalance(), 0.001);
    }

    @Test
    @Order(4)
    @DisplayName("should_transferFundsSuccessfully_when_bothAccountsAreValid")
    void should_transferFundsSuccessfully_when_bothAccountsAreValid() {
        // Arrange
        BankAccount target = new BankAccount("ACC003", "Charlie", 0.0);
        double transferAmount = 400.0;
        // Act
        account.transfer(target, transferAmount);
        // Assert
        assertEquals(INITIAL_BALANCE - transferAmount, account.getBalance(), 0.001);
        assertEquals(transferAmount, target.getBalance(), 0.001);
    }

    @Test
    @Order(5)
    @DisplayName("should_freezeAccount_when_freezeIsCalled")
    void should_freezeAccount_when_freezeIsCalled() {
        // Act
        account.freeze();
        // Assert
        assertTrue(account.isFrozen());
    }

    @Test
    @Order(6)
    @DisplayName("should_unfreezeAccount_when_unfreezeIsCalled")
    void should_unfreezeAccount_when_unfreezeIsCalled() {
        // Arrange
        account.freeze();
        // Act
        account.unfreeze();
        // Assert
        assertFalse(account.isFrozen());
    }

    // ---- NEGATIVE TESTS ----

    @Test
    @Order(7)
    @DisplayName("should_throwException_when_depositAmountIsNegative")
    void should_throwException_when_depositAmountIsNegative() {
        // Act & Assert
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> account.deposit(-100));
        assertEquals("Deposit amount must be positive", ex.getMessage());
    }

    @Test
    @Order(8)
    @DisplayName("should_throwException_when_withdrawalExceedsBalance")
    void should_throwException_when_withdrawalExceedsBalance() {
        // Act & Assert
        assertThrows(IllegalStateException.class,
                () -> account.withdraw(INITIAL_BALANCE + 1));
    }

    @Test
    @Order(9)
    @DisplayName("should_throwException_when_depositOnFrozenAccount")
    void should_throwException_when_depositOnFrozenAccount() {
        // Arrange
        account.freeze();
        // Act & Assert
        assertThrows(IllegalStateException.class, () -> account.deposit(100));
    }

    @Test
    @Order(10)
    @DisplayName("should_throwException_when_withdrawalOnFrozenAccount")
    void should_throwException_when_withdrawalOnFrozenAccount() {
        // Arrange
        account.freeze();
        // Act & Assert
        assertThrows(IllegalStateException.class, () -> account.withdraw(100));
    }

    @Test
    @Order(11)
    @DisplayName("should_throwException_when_initialBalanceIsNegative")
    void should_throwException_when_initialBalanceIsNegative() {
        assertThrows(IllegalArgumentException.class,
                () -> new BankAccount("ACC004", "Dave", -500));
    }

    // ---- BOUNDARY TESTS ----

    @Test
    @Order(12)
    @DisplayName("should_allowZeroInitialBalance_when_zeroProvided")
    void should_allowZeroInitialBalance_when_zeroProvided() {
        BankAccount acc = new BankAccount("ACC005", "Eve", 0.0);
        assertEquals(0.0, acc.getBalance());
    }

    @ParameterizedTest
    @CsvSource({"100.0, 900.0", "500.0, 500.0", "1000.0, 0.0"})
    @DisplayName("should_calculateCorrectBalance_when_variousAmountsWithdrawn")
    @Order(13)
    void should_calculateCorrectBalance_when_variousAmountsWithdrawn(double withdraw, double expected) {
        account.withdraw(withdraw);
        assertEquals(expected, account.getBalance(), 0.001);
    }
}
