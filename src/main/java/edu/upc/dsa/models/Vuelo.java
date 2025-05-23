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
    public Vuelo() {
        this.maletas = new LinkedList<>();
    }



    public void facturarMaleta(Maleta maleta) {
        // Asignar un ID único a la maleta
        maleta.setIdMaleta(this.maletas.size() + 1);
        maletas.add(0, maleta);
    }

    public void addMaleta(Maleta maleta) {
        maletas.add(maleta);
    }


    public void setMaletas(List<Maleta> maletas) {
        this.maletas = maletas;
    }

    public void setIdVuelo(int idVuelo) {
        this.idVuelo = idVuelo;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public void setSalida(int salida) {
        this.salida = salida;
    }

    public void setLlegada(int llegada) {
        this.llegada = llegada;
    }

    public void setAvion(Avion avion) {
        this.avion = avion;
    }

    public List<Maleta> getMaletas() {
        return maletas;
    }

    public int getIdVuelo() {
        return idVuelo;
    }

    public String getOrigen() {
        return origen;
    }



    public String getDestino() {
        return destino;
    }



    public int getSalida() {
        return salida;
    }



    public int getLlegada() {
        return llegada;
    }




    public Avion getAvion() {
        return avion;
    }


}
