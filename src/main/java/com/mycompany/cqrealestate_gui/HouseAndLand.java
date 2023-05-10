package com.mycompany.cqrealestate_gui;

import java.io.Serializable;

public class HouseAndLand extends Land {

    // HouseAndLand attributes
    private int propertyId;
    private String type = "HouseAndLand";
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
        this.type = type;
    }

    // Getters
    public double getConstructedArea() {return constructedArea;}
    public int getBedrooms() {return bedrooms;}
    public int getToilets() {return toilets;}
    public String getType() {return this.type;}

    // Setters
    public void setConstructedArea(double constructedArea) {this.constructedArea = constructedArea;}
    public void setBedrooms(int bedrooms) {this.bedrooms = bedrooms;}
    public void setToilets(int toilets) {this.toilets = toilets;}
    public void setType(String type) {this.type = type;}

    @Override
    public String toString(){
        // Note: super.toString() is not called due to a difference in property type
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