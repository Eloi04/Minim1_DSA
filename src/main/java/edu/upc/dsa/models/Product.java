package edu.upc.dsa.models;

import edu.upc.dsa.util.RandomUtils;

public class Product {
    private String id;
    private String name;
    private double price;
    private int salesCount;

    public Product(String id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.salesCount = 0;
    }

    public double getPrice() {
        return price;
    }

    public String getId() {
        return id;
    }

    public int sales() {
        return salesCount;
    }

    public void addSales(int quantity) {
        this.salesCount += quantity;
    }
}
