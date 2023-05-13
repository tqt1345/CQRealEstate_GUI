/*
Programmer: Tarique Turnbull
StudentID: 12177936
Course: COIT11134 Object-Oriented Programming
Assessment: Practical Assessment 2

This class controls information for seller objects
 */

package com.mycompany.cqrealestate_gui;

public class Seller extends Client {

    // Seller attributes
    private int ClientID;
    private final static String type = "Seller";

    // Seller constructor
    public Seller(int ClientID, String firstName, String lastName, String address, String phoneNumber) {
        super(ClientID, firstName, lastName, address, phoneNumber);
        this.ClientID = ClientID;
    }

    // Setters
    public void setClientID(int ClientID) {this.ClientID = ClientID;}

    // Getters
    public int getId() {return ClientID;}
    public String getType() {return type;}

    @Override
    public String toString() {
        return "Client type: " + type
                + "\n"
                + super.toString();
    }
}