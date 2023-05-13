/*
Programmer: Tarique Turnbull
StudentID: 12177936
Course: COIT11134 Object-Oriented Programming
Assessment: Practical Assessment 2

This class controls functionality for handling all data in program
 */

package com.mycompany.cqrealestate_gui;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DataHandler {
    // Object array lists
    public static ArrayList<Land> landList = new ArrayList<>();
    public static ArrayList<HouseAndLand> houseAndLandList = new ArrayList<>();
    public static ArrayList<Buyer> buyerList = new ArrayList<>();
    public static ArrayList<Seller> sellerList = new ArrayList<>();
    public static ArrayList<Sale> saleList = new ArrayList<>();


    // Saves all object arrays into a file via serialization
    public static void saveData() {
        try {
            FileOutputStream file = new FileOutputStream("./ObjectData.ser");
            ObjectOutputStream output = new ObjectOutputStream(file);

            output.writeObject(landList);
            output.writeObject(houseAndLandList);
            output.writeObject(buyerList);
            output.writeObject(sellerList);
            output.writeObject(saleList);

            output.close();
        } catch (Exception e) {
            Utils.Text.showError("Error saving data\n" + e.getMessage());
        }
    }

    // Loads all object arrays from a file via deserialization
    public static void loadData() {
        try {
            FileInputStream file = new FileInputStream("./ObjectData.ser");
            ObjectInputStream input = new ObjectInputStream(file);

            landList = (ArrayList<Land>) input.readObject();
            houseAndLandList = (ArrayList<HouseAndLand>) input.readObject();
            buyerList = (ArrayList<Buyer>) input.readObject();
            sellerList = (ArrayList<Seller>) input.readObject();
            saleList = (ArrayList<Sale>) input.readObject();

            file.close();
            input.close();
        } catch (Exception e) {
            Utils.Text.showError("Error loading data\n" + e.getMessage());
        }
    }

    // Gets an object from an array. Anything that calls this must cast to appropriate object type
    public static Object getObject (int id, List<? extends Identifier> objects) {
        try {
            for (Identifier object : objects) {
                if (object.getId() == id) {
                    return object;
                }
            }
        }catch (Exception e) {
            Utils.Text.showError("Error getting object\n" + e.getMessage());
        }
        return null;
    }

    public static void saleInfo() {
        final int SIZE = saleList.size();
        if (SIZE > 0) {
            final int LATEST_SALE = SIZE - 1;
            Utils.Text.showConfirmation("Latest sale details:\n" + saleList.get(LATEST_SALE).toString());
        }
        else {
            Utils.Text.showConfirmation("No sales have been made");
        }

    }

    // Clears all data
    public static void clearData() {
        landList.clear();
        houseAndLandList .clear();
        buyerList.clear();
        sellerList.clear();
        saleList.clear();

        saveData();
    }
}
