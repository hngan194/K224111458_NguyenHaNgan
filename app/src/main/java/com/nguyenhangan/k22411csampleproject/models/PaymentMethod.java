package com.nguyenhangan.k22411csampleproject.models;

public class PaymentMethod {
    private int id;
    private String name;
    private String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public PaymentMethod() {
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public PaymentMethod(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
}
