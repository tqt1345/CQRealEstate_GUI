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

    // Text Fields for Land objects
    @FXML
    private TextField txtLotNumberLand ;
    @FXML
    private TextField txtAddressLand;
    @FXML
    private TextField txtLandAreaLand;

    // Text fields for HouseAndLand objects
    @FXML
    private TextField txtLotNumberHouse;
    @FXML
    private TextField txtAddressHouse;
    @FXML
    private TextField txtLandAreaHouse;
    @FXML
    private TextField txtConstructedAreaHouse;
    @FXML
    private TextField txtBedroomsHouse;
    @FXML
    private TextField txtToiletsHouse;

    // Buttons
    @FXML
    private Button submitButton;

    @FXML
    private void handleLandSubmitButton (ActionEvent event) {
        try {
            if (isValidInput("land")) { // If all input is valid, will run.
                // Make land object from input
                Land land = makeLand();

                // Write the land object to file
                //Utils.FileHandler.writeLandToFile(land);

                // Show confirmation and clear fields
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Land added successfully");
                alert.showAndWait();
                clearFields();

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void handleHouseAndLandSubmitButton (ActionEvent event) {
        try {
            if (isValidInput("houseAndLand")) {
                // Make house and land object from input
                HouseAndLand houseAndLand = makeHouseAndLand();

                // Write the house and land object to file
                //Utils.FileHandler.writeHouseAndLandToFile(houseAndLand);

                // Show confirmation and clear fields
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "House and land added successfully");
                alert.showAndWait();
                clearFields();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private Land makeLand() {
        int lotNumber = Integer.parseInt(txtLotNumberLand.getText());
        String address = txtAddressLand.getText();
        double landArea = Double.parseDouble(txtLandAreaLand.getText());
        return new Land(lotNumber, address, landArea);
    }

    private HouseAndLand makeHouseAndLand() {
        int lotNumber = Integer.parseInt(txtLotNumberHouse.getText());
        String address = txtAddressHouse.getText();
        double landArea = Double.parseDouble(txtLandAreaHouse.getText());
        double constructedArea = Double.parseDouble(txtConstructedAreaHouse.getText());
        int bedrooms = Integer.parseInt(txtBedroomsHouse.getText());
        int toilets = Integer.parseInt(txtToiletsHouse.getText());
        return new HouseAndLand(lotNumber, address, landArea, constructedArea, bedrooms, toilets);
    }

    private boolean isValidInput (String type) {
        String errorMessage = "";
        switch (type) {
            case "land":
                if (txtLotNumberLand.getText().isEmpty() ||
                        txtAddressLand.getText().isEmpty() ||
                        txtLandAreaLand.getText().isEmpty()) {
                    errorMessage += "All fields must be filled\n";
                }
                if (!Utils.Validator.isInteger(txtLotNumberLand.getText())) {
                    errorMessage += "Lot number must be an integer\n";
                }
                if (!Utils.Validator.isDouble(txtLandAreaLand.getText())) {
                    errorMessage += "Land area must be a double\n";
                }
                if (!errorMessage.isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, errorMessage);
                    alert.showAndWait();
                    return false;
                }
                break;
            case "houseAndLand":
                if (txtLotNumberHouse.getText().isEmpty() ||
                        txtAddressHouse.getText().isEmpty() ||
                        txtLandAreaHouse.getText().isEmpty() ||
                        txtConstructedAreaHouse.getText().isEmpty() ||
                        txtBedroomsHouse.getText().isEmpty() ||
                        txtToiletsHouse.getText().isEmpty()) {
                    errorMessage += "All fields must be filled\n";
                }
                if (!Utils.Validator.isInteger(txtLotNumberHouse.getText())) {
                    errorMessage += "Lot number must be an integer\n";
                }
                if (!Utils.Validator.isDouble(txtLandAreaHouse.getText())) {
                    errorMessage += "Land area must be a double\n";
                }
                if (!Utils.Validator.isDouble(txtConstructedAreaHouse.getText())) {
                    errorMessage += "Constructed area must be a double\n";
                }
                if (!Utils.Validator.isInteger(txtBedroomsHouse.getText())) {
                    errorMessage += "Bedrooms must be an integer\n";
                }
                if (!Utils.Validator.isInteger(txtToiletsHouse.getText())) {
                    errorMessage += "Bathrooms must be an integer\n";
                }
                if (!errorMessage.isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, errorMessage);
                    alert.showAndWait();
                    return false;
                }
                break;
        }
        return true;
    }
    @FXML
    private void clearFields() {
        // Clearing Land fields
        txtLotNumberLand.clear();
        txtAddressLand.clear();
        txtLandAreaLand.clear();

        // Clearing House and land fields
        txtLotNumberHouse.clear();
        txtAddressHouse.clear();
        txtLandAreaHouse.clear();
        txtConstructedAreaHouse.clear();
        txtBedroomsHouse.clear();
        txtToiletsHouse.clear();
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
