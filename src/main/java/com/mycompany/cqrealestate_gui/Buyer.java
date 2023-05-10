package com.mycompany.cqrealestate_gui;

public class Buyer extends Client {

    // Buyer attributes
    private String clientType = "Buyer";

    // Buyer constructor
    public Buyer(String firstName, String lastName, String address, String phoneNumber) {
        super(firstName, lastName,address, phoneNumber);
        this.clientType = clientType;
    }

    // clientType getter and setter
    public String getClientType() {return this.clientType;}
    public void setClientType(String clientType) {this.clientType = clientType;}

    @Override
    public String toString() {
        return "Client type: " + clientType
                + "\n"
                + super.toString();
    }
}