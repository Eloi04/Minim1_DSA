package edu.upc.dsa.models;

public class Maleta {

    private int idMaleta;  // Cambié el tipo a int para el ID único
    private String pasajero;  // Asumimos que el pasajero es un string (podría ser el nombre o el ID del usuario)

    public Maleta(String pasajero) {
        this.pasajero = pasajero;
    }

    // Getters y setters
    public int getIdMaleta() {
        return idMaleta;
    }

    public void setIdMaleta(int idMaleta) {
        this.idMaleta = idMaleta;
    }

    public String getPasajero() {
        return pasajero;
    }

    public void setPasajero(String pasajero) {
        this.pasajero = pasajero;
    }
}
