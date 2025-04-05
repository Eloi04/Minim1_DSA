package edu.upc.dsa.models;

import java.util.ArrayList;
import java.util.List;

public class Vuelo {

    private List<Maleta> maletas;

    private int id; //Identificador de la maleta
    private String origen;
    private String destino;
    private int salida;
    private int llegada;
    private String avionID;

    public Vuelo(int id, String origen, String destino, int salida, int llegada, String avionID) {

        this.id = id;
        this.origen = origen;
        this.destino = destino;
        this.salida = salida;
        this.llegada = llegada;
        this.avionID = avionID;

        this.maletas = new ArrayList<>();
    }
//Añadinos maleta al vuelo
    public void addMaleta(Maleta maleta) {
        this.maletas.add(maleta);
    }
//Obtener la lista de maletas facturadas en el vuelo
    public List<Maleta> getMaletas() {
        return maletas;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public int getSalida() {
        return salida;
    }

    public void setSalida(int salida) {
        this.salida = salida;
    }

    public int getLlegada() {
        return llegada;
    }

    public void setLlegada(int llegada) {
        this.llegada = llegada;
    }

    public void setAvionID(String avionID) {
        this.avionID = avionID;
    }

    public String getAvionID() {
        return avionID;
    }

}
