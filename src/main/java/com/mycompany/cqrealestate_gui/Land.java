/*
Programmer: Tarique Turnbull
StudentID: 12177936
Course: COIT11134 Object-Oriented Programming
Assessment: Practical Assessment 2

This class controls information for Land objects
 */

package com.mycompany.cqrealestate_gui;

import java.io.Serializable;

public class Land implements Serializable,Identifier {

    // Land attributes
    private int propertyId;
    private String type = "Land";
    private int lotNumber;
    private String address;
    private double landArea;

    // Land constructor
    public Land(int propertyId, int lotNumber, String address, double landArea) {
        this.propertyId = propertyId;
        this.lotNumber = lotNumber;
        this.address = address;
        this.landArea = landArea;
        this.type = type;
    }

    // Getters
    public int getId() {return propertyId;}
    public String getType() {return this.type;}
    public int getLotNumber() {return lotNumber;}
    public String getAddress() {return address;}
    public double getLandArea() {return landArea;}

    // Setters
    public void setLandId(int landId) {this.propertyId = landId;}
    public void setLotNumber(int lotNumber) {this.lotNumber = lotNumber;}
    public void setAddress(String address) {this.address = address;}
    public void setLandArea(double landArea) {this.landArea = landArea;}

    @Override
    public String toString(){
        return "Property type: " + type
                + "\n" + "Land ID: " + propertyId
                + "\nLot number: " + lotNumber
                + "\nAddress: " + address
                + "\nLand area: " + landArea ;
    }
}