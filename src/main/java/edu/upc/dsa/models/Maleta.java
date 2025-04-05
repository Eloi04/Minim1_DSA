package edu.upc.dsa.models;


import java.util.List;
import java.util.ArrayList;

public class Maleta {

    private String id;
    private String vueloId;
    private List<String> items;


    public Maleta(String id, String vueloId) {
        this.id = id;
        this.vueloId = vueloId;
        this.items = new ArrayList<>();


    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVueloId() {
        return vueloId;
    }

    public void setVueloId(String vueloId) {
        this.vueloId = vueloId;
    }




    public void addLV(int quantity, String productId) {
        items.add(quantity + "x " );

    }
}





