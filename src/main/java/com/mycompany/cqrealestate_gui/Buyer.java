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
    private final static String type = "Buyer";

    // Buyer constructor
    public Buyer(int clientID, String firstName, String lastName, String address, String phoneNumber) {
        super(clientID,firstName, lastName,address, phoneNumber);
        this.clientID = clientID;
    }

    // Setters
    public void setClientID(int clientID) {this.clientID = clientID;}

    // Getters
    public int getId() {return clientID;}
    public String getClientType() {return type;}

    @Override
    public String toString() {
        return "Client type: " + type
                + "\n"
                + super.toString();
    }
}