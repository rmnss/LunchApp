package com.hfda.LunchApp;

import java.util.ArrayList;

public class Today {
    String name;
    Integer price;
    ArrayList<String> allergy = new ArrayList<>();

    public Today(String name, Integer price, ArrayList<String> allergy) {
        this.name = name;
        this.price = price;
        this.allergy = allergy;
    }

    public String getName() {
        return name;
    }

    public Integer getPrice() {
        return price;
    }

    public ArrayList<String> getAllergy() {
        return allergy;
    }
}
