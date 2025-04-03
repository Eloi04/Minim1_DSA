package edu.upc.dsa.models;


import java.util.List;
import java.util.ArrayList;

public class Vuelos {

    private String userDNI;
    private List<String> items;

    public Vuelos(String dni) {
        this.userDNI = dni;
        this.items = new ArrayList<>();

    }

    public void addLP(int quantity, String productId) {
        items.add(quantity + "x " + productId);

    }

    public List<String> getItems() {
        return items;
    }


    public String getUser() {

        return userDNI;

    }
}
