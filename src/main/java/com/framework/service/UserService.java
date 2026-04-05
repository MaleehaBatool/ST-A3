package com.framework.service;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * User management service with registration, authentication, and role management.
 */
public class UserService {

    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    private static final int MIN_PASSWORD_LENGTH = 8;

    private final Map<String, String> userPasswords = new HashMap<>();
    private final Map<String, String> userRoles = new HashMap<>();
    private final Map<String, Boolean> userActive = new HashMap<>();

    public void register(String email, String password) {
        if (email == null || !EMAIL_PATTERN.matcher(email).matches())
            throw new IllegalArgumentException("Invalid email address");
        if (password == null || password.length() < MIN_PASSWORD_LENGTH)
            throw new IllegalArgumentException("Password must be at least 8 characters");
        if (userPasswords.containsKey(email))
            throw new IllegalStateException("User already exists: " + email);
        userPasswords.put(email, password);
        userRoles.put(email, "USER");
        userActive.put(email, true);
    }

    public boolean login(String email, String password) {
        if (!userPasswords.containsKey(email)) return false;
        if (!userActive.getOrDefault(email, false)) return false;
        return userPasswords.get(email).equals(password);
    }

    public void assignRole(String email, String role) {
        if (!userPasswords.containsKey(email))
            throw new IllegalArgumentException("User not found: " + email);
        if (role == null || role.isBlank())
            throw new IllegalArgumentException("Role cannot be blank");
        userRoles.put(email, role);
    }

    public String getRole(String email) {
        if (!userPasswords.containsKey(email))
            throw new IllegalArgumentException("User not found: " + email);
        return userRoles.get(email);
    }

    public void deactivateUser(String email) {
        if (!userPasswords.containsKey(email))
            throw new IllegalArgumentException("User not found: " + email);
        userActive.put(email, false);
    }

    public boolean isActive(String email) {
        return userActive.getOrDefault(email, false);
    }

    public void changePassword(String email, String oldPassword, String newPassword) {
        if (!login(email, oldPassword)) throw new IllegalArgumentException("Current password is incorrect");
        if (newPassword.length() < MIN_PASSWORD_LENGTH)
            throw new IllegalArgumentException("New password too short");
        userPasswords.put(email, newPassword);
    }

    public int getUserCount() { return userPasswords.size(); }
}
