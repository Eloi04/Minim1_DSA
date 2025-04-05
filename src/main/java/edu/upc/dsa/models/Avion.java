package edu.upc.dsa.models;

public class Avion {
    private String idAvio;
    private String model;
    private String company;

    public Avion() {}

    public Avion(String idAvio, String model, String company) {
        this.idAvio = idAvio;
        this.model = model;
        this.company = company;
    }


    public String getIdAvio() {
        return idAvio;
    }

    public void setIdAvio(String idAvio) {
        this.idAvio = idAvio;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
