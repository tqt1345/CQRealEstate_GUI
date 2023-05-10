package com.mycompany.cqrealestate_gui;

public class Seller extends Client {

    // Seller attributes
    private String clientType = "Seller";

    // Seller constructor
    public Seller(String firstName, String lastName, String address, String phoneNumber) {
        super(firstName, lastName, address, phoneNumber);
        this.clientType = clientType;
    }

    // Getters and setters
    public String getClientType() {return this.clientType;}
    public void setClientType(String clientType) {this.clientType = clientType;}

    @Override
    public String toString() {
        return "Client type: " + clientType
                + "\n"
                + super.toString();
    }
}