/*
Programmer: Tarique Turnbull
StudentID: 12177936
Course: COIT11134 Object-Oriented Programming
Assessment: Practical Assessment 2

This class controls information for Client objects
 */

package com.mycompany.cqrealestate_gui;

import java.io.Serializable;

public abstract class Client implements Serializable {

    // Client attributes
    private int clientID;
    private String firstName;
    private String lastName;
    private String address;
    private String phoneNumber;

    // Client constructor
    public Client(int clientID, String firstName, String lastName, String address, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.clientID = clientID;
    }

    // Setters
    public void setClientID(int clientID) {this.clientID = clientID;}
    public void setFirstName(String firstName) {this.firstName = firstName;}
    public void setLastName(String lastName) {this.lastName = lastName;}
    public void setAddress(String address) {this.address = address;}
    public void setPhoneNumber(String phoneNumber) {this.phoneNumber = phoneNumber;}

    // Getters
    public int getClientID() {return clientID;}
    public String getFirstName() {return firstName;}
    public String getLastName() {return lastName;}
    public String getAddress() {return address;}
    public String getPhoneNumber() {return phoneNumber;}

    // toString method to describe object
    @Override
    public String toString() {
        return "Client ID: " + clientID
                + "\nFirst name: " + firstName
                + "\nLast name: " + lastName
                + "\nAddress: " + address
                + "\nPhone number: " + phoneNumber;
    }
}