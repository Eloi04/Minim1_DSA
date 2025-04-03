package edu.upc.dsa.models;

public class Avión {
    private String id;
    private String model;
    private String company;

    public Avión() {}

    public Avión(String id, String model, String company) {
        this.id = id;
        this.model = model;
        this.company=company;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
