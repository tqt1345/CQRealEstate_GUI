/*
Programmer: Tarique Turnbull
StudentID: 12177936
Course: COIT11134 Object-Oriented Programming
Assessment: Practical Assessment 2

This class controls information for HouseAndLand objects
 */

package com.mycompany.cqrealestate_gui;

public class HouseAndLand extends Land {

    // HouseAndLand attributes
    private int propertyId;
    private static final String type = "HouseAndLand";
    private double constructedArea;
    private int bedrooms;
    private int toilets;

    // HouseAndLand constructor
    public HouseAndLand(int propertyId, int lotNumber, String address, double landArea, double constructedArea, int bedrooms, int toilets) {
        super(propertyId, lotNumber, address, landArea);
        this.propertyId = propertyId;
        this.constructedArea = constructedArea;
        this.bedrooms = bedrooms;
        this.toilets = toilets;
    }

    // Getters
    public double getConstructedArea() {return constructedArea;}
    public int getBedrooms() {return bedrooms;}
    public int getToilets() {return toilets;}
    public String getType() {return type;}

    // Setters
    public void setConstructedArea(double constructedArea) {this.constructedArea = constructedArea;}
    public void setBedrooms(int bedrooms) {this.bedrooms = bedrooms;}
    public void setToilets(int toilets) {this.toilets = toilets;}

    @Override
    public String toString(){
        return "Property type: " + type
                + "\n" + "HouseAndLand ID: " + propertyId
                + "\nLot number: " + getLotNumber()
                + "\nAddress: " + getAddress()
                + "\nLand area: " + getLandArea()
                + "\nConstructed area: " + constructedArea
                + "\nBedrooms: " + bedrooms
                + "\nToilets: " + toilets;
    }
}