package com.mycompany.cqrealestate_gui;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

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
            // TODO
        } catch (Exception e) {
            // TODO JOptionPane error message
            System.out.println(e.getMessage());
        }
    }

    // Loads all object arrays from a file via deserialization
    public static void loadData() {
        try {
            // TODO
        } catch (Exception e) {
            // TODO JOptionPane error message
            System.out.println(e.getMessage());
        }
    }

}
