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

    private boolean isValidInput (String type) {
        String errorMessage = "";
        switch (type) {
            case "land":
                if (txtLotNumber.getText().isEmpty() || txtAddress.getText().isEmpty() || txtLandArea.getText().isEmpty()) {
                    errorMessage += "All fields must be filled\n";
                }
                if (!Utils.Validator.isInteger(txtLotNumber.getText())) {
                    errorMessage += "Lot number must be an integer\n";
                }
                if (!Utils.Validator.isDouble(txtLandArea.getText())) {
                    errorMessage += "Land area must be a double\n";
                }
                if (!errorMessage.isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, errorMessage);
                    alert.showAndWait();
                    return false;
                }
            case "houseAndLand":
                if (txtLotNumber.getText().isEmpty() || txtAddress.getText().isEmpty() || txtLandArea.getText().isEmpty() || txtConstructedArea.getText().isEmpty() || txtBedrooms.getText().isEmpty() || txtBathrooms.getText().isEmpty()) {
                    errorMessage += "All fields must be filled\n";
                }
                if (!Utils.Validator.isInteger(txtLotNumber.getText())) {
                    errorMessage += "Lot number must be an integer\n";
                }
                if (!Utils.Validator.isDouble(txtLandArea.getText())) {
                    errorMessage += "Land area must be a double\n";
                }
                if (!Utils.Validator.isDouble(txtConstructedArea.getText())) {
                    errorMessage += "Constructed area must be a double\n";
                }
                if (!Utils.Validator.isInteger(txtBedrooms.getText())) {
                    errorMessage += "Bedrooms must be an integer\n";
                }
                if (!Utils.Validator.isInteger(txtBathrooms.getText())) {
                    errorMessage += "Bathrooms must be an integer\n";
                }
                if (!errorMessage.isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, errorMessage);
                    alert.showAndWait();
                    return false;
                }
        }
        return true;
    }

    @FXML
    private void handleLandSubmitButton (ActionEvent event) {

        if (isValidInput("land")) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Land added successfully");
            alert.showAndWait();
        }
    }

    @FXML
    private void handleHouseAndLandSubmitButton (ActionEvent event) {
        if (isValidInput("houseAndLand")) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "House and land added successfully");
            alert.showAndWait();
        }
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
