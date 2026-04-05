package org.example;
public class UserService {
    public boolean login(String username, String password) {
        return username.equals("admin") && password.equals("1234");
    }
}