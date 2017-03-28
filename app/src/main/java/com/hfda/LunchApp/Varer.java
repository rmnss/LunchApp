package com.hfda.LunchApp;

import java.util.ArrayList;

/**
 * Created by remi_ on 28.03.2017.
 */

public class Varer {
    String navn;
    Integer pris;
    ArrayList<String> allergi = new ArrayList<>();

    public Varer(String navn, Integer pris, ArrayList<String> allergi) {
        this.navn = navn;
        this.pris = pris;
        this.allergi = allergi;
    }

    public String getNavn() {
        return navn;
    }

    public Integer getPris() {
        return pris;
    }

    public ArrayList<String> getAllergi() {
        return allergi;
    }
}
