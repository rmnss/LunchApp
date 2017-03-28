package com.hfda.LunchApp;

/**
 * Created by remi_ on 28.03.2017.
 */

public class Bruker {
    String epost;
    String passord;
    Integer kaffeKlipp;

    public Bruker(String epost, String passord, Integer kaffeKlipp) {
        this.epost = epost;
        this.passord = passord;
        this.kaffeKlipp = kaffeKlipp;
    }

    public String getEpost() {
        return epost;
    }

    public String getPassord() {
        return passord;
    }

    public Integer getKaffeKlipp() {
        return kaffeKlipp;
    }
}
