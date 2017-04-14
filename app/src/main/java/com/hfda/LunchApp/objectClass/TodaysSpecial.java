package com.hfda.LunchApp.objectClass;


import java.util.ArrayList;

public class TodaysSpecial {

    private String id;
    private String name;
    private String servingTime;
    private String price;
    private String ServeDay;
    private ArrayList<String> allergies = new ArrayList<>();



    public TodaysSpecial(String id, String name, String servingTime, String price, String serveDay) {
        this.id = id;
        this.name = name;
        this.servingTime = servingTime;
        this.price = price;
        ServeDay = serveDay;
    }



    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getServingTime() {
        return servingTime;
    }

    public String getPrice() {
        return price;
    }

    public String getServeDay() {
        return ServeDay;
    }

    public ArrayList<String> getAllergies() {
        return allergies;
    }




    public void addAllergy(String allergy) {
        this.allergies.add(allergy);
    }



}
