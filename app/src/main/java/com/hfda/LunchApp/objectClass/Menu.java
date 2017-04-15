package com.hfda.LunchApp.objectClass;

public class Menu {

    private String manufacturer;
    private String name;
    private String price; //Storing as string as we're not going to calculate with this number
    private String category;

    public Menu(String manufacturer, String name, String price, String category) {
        this.manufacturer = manufacturer;
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }
}
