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
    @FXML private TextField txtLandId;
    @FXML private TextField txtLotNumberLand ;
    @FXML private TextField txtAddressLand;
    @FXML private TextField txtLandAreaLand;

    // Text fields for HouseAndLand objects
    @FXML private TextField txtHouseAndLandId;
    @FXML private TextField txtLotNumberHouse;
    @FXML private TextField txtAddressHouse;
    @FXML private TextField txtLandAreaHouse;
    @FXML private TextField txtConstructedAreaHouse;
    @FXML private TextField txtBedroomsHouse;
    @FXML private TextField txtToiletsHouse;

    // Buttons
    @FXML private Button submitButton;

    @FXML
    private void handleLandSubmitButton (ActionEvent event) {
        try {
            if (isValidInput("land")) { // If all input is valid, will run.
                // Make land object from input
                Land land = makeLand();

                // Add land object to land list
                DataHandler.landList.add(land);

                // Show confirmation and clear fields
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Land added successfully");
                alert.showAndWait();
                clearFields();

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML private void handleHouseAndLandSubmitButton (ActionEvent event) {
        try {
            if (isValidInput("houseAndLand")) {
                // Make house and land object from input
                HouseAndLand houseAndLand = makeHouseAndLand();

                // Add house and land object to house and land list
                DataHandler.houseAndLandList.add(houseAndLand);

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
        int landId = Integer.parseInt(txtLandId.getText());
        int lotNumber = Integer.parseInt(txtLotNumberLand.getText());
        String address = txtAddressLand.getText();
        double landArea = Double.parseDouble(txtLandAreaLand.getText());
        return new Land(landId, lotNumber, address, landArea);
    }

    private HouseAndLand makeHouseAndLand() {
        int houseAndLandId = Integer.parseInt(txtHouseAndLandId.getText());
        int lotNumber = Integer.parseInt(txtLotNumberHouse.getText());
        String address = txtAddressHouse.getText();
        double landArea = Double.parseDouble(txtLandAreaHouse.getText());
        double constructedArea = Double.parseDouble(txtConstructedAreaHouse.getText());
        int bedrooms = Integer.parseInt(txtBedroomsHouse.getText());
        int toilets = Integer.parseInt(txtToiletsHouse.getText());
        return new HouseAndLand(houseAndLandId, lotNumber, address, landArea, constructedArea, bedrooms, toilets);
    }

    private boolean isValidInput (String type) {
        StringBuilder errorMessage = new StringBuilder();
        switch (type) {
            case "land":
                if (    txtLandId.getText().isEmpty() ||
                        txtLotNumberLand.getText().isEmpty() ||
                        txtAddressLand.getText().isEmpty() ||
                        txtLandAreaLand.getText().isEmpty()) {

                        errorMessage.append("All fields must be filled\n");
                }
                
                for (Land land : DataHandler.landList) {
                    if (land.getPropertyId() == Integer.parseInt(txtLandId.getText())) {
                        errorMessage.append("Land ID already exists\n");
                    }
                }
                if (!Utils.Validator.isInteger(txtLotNumberLand.getText())) {
                    errorMessage.append("Lot number must be an integer\n");
                }
                if (!Utils.Validator.isDouble(txtLandAreaLand.getText())) {
                    errorMessage.append("Land area must be a double\n");
                }
                if (errorMessage.length() > 0) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, errorMessage.toString());
                    alert.showAndWait();
                    return false;
                }
                break;
            case "houseAndLand":
                if (    txtHouseAndLandId.getText().isEmpty() ||
                        txtLotNumberHouse.getText().isEmpty() ||
                        txtAddressHouse.getText().isEmpty() ||
                        txtLandAreaHouse.getText().isEmpty() ||
                        txtConstructedAreaHouse.getText().isEmpty() ||
                        txtBedroomsHouse.getText().isEmpty() ||
                        txtToiletsHouse.getText().isEmpty()) {

                        errorMessage.append("All fields must be filled\n");
                }
                for (HouseAndLand houseAndLand : DataHandler.houseAndLandList) {
                    if (houseAndLand.getPropertyId() == Integer.parseInt(txtHouseAndLandId.getText())) {
                        errorMessage.append("HouseAndLand ID already exists\n");
                    }
                }
                if (!Utils.Validator.isInteger(txtLotNumberHouse.getText())) {
                    errorMessage.append("Lot number must be an integer\n");
                }
                if (!Utils.Validator.isDouble(txtLandAreaHouse.getText())) {
                    errorMessage.append("Land area must be a double\n");
                }
                if (!Utils.Validator.isDouble(txtConstructedAreaHouse.getText())) {
                    errorMessage.append("Constructed area must be a double\n");
                }
                if (!Utils.Validator.isInteger(txtBedroomsHouse.getText())) {
                    errorMessage.append("Bedrooms must be an integer\n");
                }
                if (!Utils.Validator.isInteger(txtToiletsHouse.getText())) {
                    errorMessage.append("Bathrooms must be an integer\n");
                }
                if (errorMessage.length() > 0) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, errorMessage.toString());
                    alert.showAndWait();
                    return false;
                }
                break;
        }
        return true;
    }
    private void clearFields() {
        // Clearing Land fields
        txtLandId.clear();
        txtLotNumberLand.clear();
        txtAddressLand.clear();
        txtLandAreaLand.clear();

        // Clearing House and land fields
        txtHouseAndLandId.clear();
        txtLotNumberHouse.clear();
        txtAddressHouse.clear();
        txtLandAreaHouse.clear();
        txtConstructedAreaHouse.clear();
        txtBedroomsHouse.clear();
        txtToiletsHouse.clear();
    }
    @FXML private void switchToMainMenu() throws Exception {
        try {
            App.setRoot("mainMenu");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
