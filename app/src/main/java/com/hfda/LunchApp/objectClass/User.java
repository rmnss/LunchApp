package com.hfda.LunchApp.objectClass;

public class User {
    String email;
    String password;
    Integer coffeeNumber;

    public User(String email, String password, Integer coffeeNumber) {
        this.email = email;
        this.password = password;
        this.coffeeNumber = coffeeNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Integer getCoffeeNumber() {
        return coffeeNumber;
    }
}
