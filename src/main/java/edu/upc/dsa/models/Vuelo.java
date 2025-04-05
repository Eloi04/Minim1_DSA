package edu.upc.dsa.models;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Vuelo {

    private List<Maleta> maletas;

    private int idVuelo;
    private String origen;
    private String destino;
    private int salida;
    private int llegada;
    private Avion avion;


    public Vuelo(int id, String origen, String destino, int salida, int llegada, Avion avion) {

        this.idVuelo = id;
        this.origen = origen;
        this.destino = destino;
        this.salida = salida;
        this.llegada = llegada;
        this.avion = avion;

        this.maletas = new LinkedList<>();
    }

    public void facturarMaleta(Maleta maleta) {
        // Asignar un ID único a la maleta
        maleta.setIdMaleta(this.maletas.size() + 1);  // Asignamos un ID secuencial basado en el tamaño de la lista
        maletas.add(0, maleta);  // Añadir la maleta al principio (desde el fondo de la bodega)
    }

    public void addMaleta(Maleta maleta) {
        maletas.add(maleta);
    }

    public List<Maleta> maletas() {
        return maletas;

    }


    public List<Maleta> getMaletas() {
        return maletas;
    }

    public int getIdVuelo() {
        return idVuelo;
    }

    public void setIdVuelo(int idVuelo) {
        this.idVuelo = idVuelo;
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

    public Avion getAvion() {
        return avion;
    }

    public void setAvion(Avion avion) {
        this.avion = avion;
    }

    public List<Maleta> getMaletasFacturadas() {return maletas;}

    public void setMaletasFacturadas(List<Maleta> maletas) {
        this.maletas = maletas;
    }
}
