/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.cqrealestate_gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author tqt13
 */
public class AddPropertyController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    // Text Fields
    @FXML
    private TextField txtLotNumber;
    @FXML
    private TextField txtAddress;
    @FXML
    private TextField txtLandArea;
    @FXML
    private TextField txtConstructedArea;
    @FXML
    private TextField txtBedrooms;
    @FXML
    private TextField txtBathrooms;
    @FXML
    private Button submitButton;

    @FXML
    private void clearFields() {
        txtLotNumber.clear();
        txtAddress.clear();
        txtLandArea.clear();
    }

    @FXML
    private void handleLandSubmitButton (ActionEvent event) {

        // Check input
        try {
            int lotNumber = Integer.parseInt(txtLotNumber.getText());
            String address = txtAddress.getText();
            double landArea = Double.parseDouble(txtLandArea.getText());

            clearFields();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Land added successfully");
            alert.showAndWait();
        } catch (Exception e) { // Error message if incorrect input
            Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid input");
            alert.showAndWait();
        }
    }

    @FXML
    private void handleHouseAndLandSubmitButton (ActionEvent event) {
        clearFields();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "House and land added successfully");
        alert.showAndWait();
    }

    private void writeToFile() {
        // TODO
    }

    @FXML
    private void switchToMainMenu() throws Exception {
        try {
            App.setRoot("mainMenu");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
