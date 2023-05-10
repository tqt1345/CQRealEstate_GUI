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
public class AddClientController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    // Text Fields for Buyer objects
    // Text Fields for Seller objects

    @FXML private void handleSubmitBuyerButton () {
        // TODO
    }

    @FXML private void handleSubmitSellerButton () {
        // TODO
    }

    private void makeBuyer() {
        // TODO
    }

    private void makeSeller() {
        // TODO
    }

    private boolean isValidInput() {
        // TODO
        return false;
    }

    private void clearFields() {
        // TODO
    }
    public void switchToMainMenu() throws Exception {
        try {
            App.setRoot("mainMenu");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    
}
