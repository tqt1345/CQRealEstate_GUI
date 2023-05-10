package com.mycompany.cqrealestate_gui;

public abstract class Client {

    // Client attributes
    private int clientID;
    private static int lastClientID = 1;
    private String firstName;
    private String lastName;
    private String address;
    private String phoneNumber;

    // Client constructor
    public Client(String firstName, String lastName, String address, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.clientID = lastClientID;
        lastClientID++;
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