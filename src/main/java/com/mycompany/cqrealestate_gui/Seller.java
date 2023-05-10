package com.mycompany.cqrealestate_gui;

public class Seller extends Client {

    // Seller attributes
    private int ClientID;
    private String clientType = "Seller";

    // Seller constructor
    public Seller(int ClientID, String firstName, String lastName, String address, String phoneNumber) {
        super(ClientID, firstName, lastName, address, phoneNumber);
        this.clientType = clientType;
        this.ClientID = ClientID;
    }

    // Setters
    public void setClientID(int ClientID) {this.ClientID = ClientID;}
    public void setClientType(String clientType) {this.clientType = clientType;}

    // Getters
    public int getClientID() {return ClientID;}
    public String getClientType() {return this.clientType;}

    @Override
    public String toString() {
        return "Client type: " + clientType
                + "\n"
                + super.toString();
    }
}