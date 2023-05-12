/*
Programmer: Tarique Turnbull
StudentID: 12177936
Course: COIT11134 Object-Oriented Programming
Assessment: Practical Assessment 2

This class controls information for Buyer objects
 */

package com.mycompany.cqrealestate_gui;

public class Buyer extends Client {

    // Buyer attributes
    private int clientID;
    private String clientType = "Buyer";

    // Buyer constructor
    public Buyer(int clientID, String firstName, String lastName, String address, String phoneNumber) {
        super(clientID,firstName, lastName,address, phoneNumber);
        this.clientType = clientType;
        this.clientID = clientID;
    }

    // Setters
    public void setClientID(int clientID) {this.clientID = clientID;}
    public void setClientType(String clientType) {this.clientType = clientType;}

    // Getters
    public int getClientID() {return clientID;}
    public String getClientType() {return this.clientType;}

    @Override
    public String toString() {
        return "Client type: " + clientType
                + "\n"
                + super.toString();
    }
}