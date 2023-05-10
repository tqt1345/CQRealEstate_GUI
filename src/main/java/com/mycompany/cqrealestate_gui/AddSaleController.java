/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.cqrealestate_gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author tqt13
 */
public class AddSaleController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    // Text Fields for Land Sale objects
    // Text Fields for HouseAndLandSale Sale objects

    @FXML private void handleSubmitLandSaleButton () {
        // TODO
    }

    @FXML private void handleSubmitHouseAndLandSaleButton () {
        // TODO
    }

    private void makeSale(String type) {
        // TODO
        switch (type) {
            case "Land":
                return;
            case "HouseAndLand":
                return;
        }
    }

    private boolean isValidInput() {
        // TODO
        return false;
    }

    private void clearFields() {
        // TODO
    }

    @FXML private void switchToMainMenu() {
        try {
            App.setRoot("mainMenu");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
