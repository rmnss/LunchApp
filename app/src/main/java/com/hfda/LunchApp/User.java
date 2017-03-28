package com.hfda.LunchApp;

public class User {
    String email;
    String password;
    Integer coffeNumber;

    public User(String email, String password, Integer coffeNumber) {
        this.email = email;
        this.password = password;
        this.coffeNumber = coffeNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Integer getCoffeNumber() {
        return coffeNumber;
    }
}
