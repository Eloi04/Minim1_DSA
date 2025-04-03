package edu.upc.dsa.models;

import java.util.ArrayList;
import java.util.List;

public class Maleta {
    private String dni;
    private List<Vuelos> vuelos;
    private int id; //Identificador de la maleta

    public Maleta(String dni) {

    this.dni = dni;
        this.vuelos = new ArrayList<>();
    }
    public void addOrder(Vuelos vuelos) {
        this.vuelos.add(vuelos);
    }
    public List<Vuelos> orders() {
        return vuelos;

    }
    public String getDni() {
        return dni;
    }
}
