package com.framework.tests;

import com.framework.service.UserService;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for UserService.
 */
@DisplayName("UserService Tests")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Tag("integration")
class UserServiceTest {

    private UserService userService;

    @BeforeEach
    void setUp() {
        userService = new UserService();
    }

    // ---- POSITIVE TESTS ----

    @Test
    @Order(1)
    @DisplayName("should_registerUserSuccessfully_when_validEmailAndPasswordProvided")
    void should_registerUserSuccessfully_when_validEmailAndPasswordProvided() {
        userService.register("alice@example.com", "Password123");
        assertEquals(1, userService.getUserCount());
    }

    @Test
    @Order(2)
    @DisplayName("should_returnTrue_when_loginWithCorrectCredentials")
    void should_returnTrue_when_loginWithCorrectCredentials() {
        userService.register("bob@example.com", "SecurePass1");
        assertTrue(userService.login("bob@example.com", "SecurePass1"));
    }

    @Test
    @Order(3)
    @DisplayName("should_assignRoleSuccessfully_when_userExistsAndRoleIsValid")
    void should_assignRoleSuccessfully_when_userExistsAndRoleIsValid() {
        userService.register("charlie@example.com", "Pass12345");
        userService.assignRole("charlie@example.com", "ADMIN");
        assertEquals("ADMIN", userService.getRole("charlie@example.com"));
    }

    @Test
    @Order(4)
    @DisplayName("should_deactivateUser_when_userExists")
    void should_deactivateUser_when_userExists() {
        userService.register("dave@example.com", "Passwrd1");
        userService.deactivateUser("dave@example.com");
        assertFalse(userService.isActive("dave@example.com"));
    }

    @Test
    @Order(5)
    @DisplayName("should_returnFalse_when_loginWithDeactivatedAccount")
    void should_returnFalse_when_loginWithDeactivatedAccount() {
        userService.register("eve@example.com", "Passwrd1!");
        userService.deactivateUser("eve@example.com");
        assertFalse(userService.login("eve@example.com", "Passwrd1!"));
    }

    @Test
    @Order(6)
    @DisplayName("should_changePassword_when_oldPasswordIsCorrect")
    void should_changePassword_when_oldPasswordIsCorrect() {
        userService.register("frank@example.com", "OldPass12");
        userService.changePassword("frank@example.com", "OldPass12", "NewPass99");
        assertTrue(userService.login("frank@example.com", "NewPass99"));
    }

    // ---- NEGATIVE TESTS ----

    @Test
    @Order(7)
    @DisplayName("should_throwException_when_emailIsInvalid")
    void should_throwException_when_emailIsInvalid() {
        assertThrows(IllegalArgumentException.class,
                () -> userService.register("not-an-email", "Password1"));
    }

    @Test
    @Order(8)
    @DisplayName("should_throwException_when_passwordIsTooShort")
    void should_throwException_when_passwordIsTooShort() {
        assertThrows(IllegalArgumentException.class,
                () -> userService.register("user@test.com", "abc"));
    }

    @Test
    @Order(9)
    @DisplayName("should_throwException_when_registeringDuplicateEmail")
    void should_throwException_when_registeringDuplicateEmail() {
        userService.register("dup@example.com", "Password1");
        assertThrows(IllegalStateException.class,
                () -> userService.register("dup@example.com", "Password2"));
    }

    @Test
    @Order(10)
    @DisplayName("should_returnFalse_when_loginWithWrongPassword")
    void should_returnFalse_when_loginWithWrongPassword() {
        userService.register("grace@example.com", "Correct123");
        assertFalse(userService.login("grace@example.com", "Wrong999"));
    }

    @Test
    @Order(11)
    @DisplayName("should_throwException_when_changingPasswordWithWrongOldPassword")
    void should_throwException_when_changingPasswordWithWrongOldPassword() {
        userService.register("henry@example.com", "OrigPass1");
        assertThrows(IllegalArgumentException.class,
                () -> userService.changePassword("henry@example.com", "WrongOld", "NewPass1"));
    }

    // ---- BOUNDARY TESTS ----

    @Test
    @Order(12)
    @DisplayName("should_assignDefaultUserRole_when_userRegistered")
    void should_assignDefaultUserRole_when_userRegistered() {
        userService.register("iris@example.com", "Password1");
        assertEquals("USER", userService.getRole("iris@example.com"));
    }
}
