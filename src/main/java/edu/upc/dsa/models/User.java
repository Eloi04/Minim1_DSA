package models;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String dni;
    private List<Order> orders;

    public User(String dni) {
        this.dni = dni;
        this.orders = new ArrayList<>();
    }
    public void addOrder(Order order) {
        orders.add(order);
    }
    public List<Order> orders() {
        return orders;

    }
    public String getDni() {
        return dni;
    }
}
