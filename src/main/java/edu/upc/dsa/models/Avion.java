package edu.upc.dsa.models;

public class Avion {
    private String idAvion;
    private String model;
    private String company;

    public Avion() {}

    public Avion(String idAvion, String model, String company) {
        this.idAvion = idAvion;
        this.model = model;
        this.company = company;
    }


    public String getIdAvion() {
        return idAvion;
    }

    public void setIdAvion(String idAvion) {
        this.idAvion = idAvion;
    }

    public void setCompany(String company) {
        this.company = company;
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




}
