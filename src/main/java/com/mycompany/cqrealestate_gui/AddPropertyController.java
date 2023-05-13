/*
Programmer: Tarique Turnbull
StudentID: 12177936
Course: COIT11134 Object-Oriented Programming
Assessment: Practical Assessment 2

This class controls functionality for adding properties
 */
package com.mycompany.cqrealestate_gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author tqt13
 */
public class AddPropertyController implements Initializable {

    // Text Field arrays
    private TextField[] LAND_FIELDS;
    private TextField[] HOUSE_AND_LAND_FIELDS;

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

    StringBuilder errorMessage;

    // Handles the submit button for land objects
    @FXML private void handleLandSubmitButton () {
        try {
            if (validLandInput()) {
                DataHandler.landList.add(makeLand());
                Utils.Text.showConfirmation("Land added successfully");
                clearFields();
            } else {
                Utils.Text.showError(errorMessage.toString());
                clearError();
            }
        } catch (Exception e) {
            Utils.Text.showError(e.getMessage());
        }
    }

    // Handles the submit button for houseAndLand objects
    @FXML private void handleHouseAndLandSubmitButton () {
        try {
            if (validHouseInput()) {
                DataHandler.houseAndLandList.add(makeHouseAndLand());
                Utils.Text.showConfirmation("House and land added successfully");
                clearFields();
            } else {
                Utils.Text.showError(errorMessage.toString());
                clearError();
            }
        } catch (Exception e) {
            Utils.Text.showError(e.getMessage());
        }
    }

    // Checks if input for land is valid
    private boolean validLandInput() {
        boolean isValid = true;
        if (!Utils.Validator.isNotEmpty(LAND_FIELDS)) {
            errorMessage.append("All fields must be filled\n");
            return false;
        }

        final String ID = txtLandId.getText();
        final String LOT_NUMBER = txtLotNumberLand.getText();
        final String LAND_AREA = txtLandAreaLand.getText();

        if (!Utils.Validator.isInteger(ID)) {
            errorMessage.append("ID must be an integer\n");
            isValid = false;
        } else {
            if (!Utils.Validator.isGreaterThan(ID, 0)) {
                errorMessage.append("ID must be greater than 0\n");
                isValid = false;
            }
            if (!Utils.Validator.idExists(ID, DataHandler.landList)) {
                errorMessage.append("ID already exists\n");
                isValid = false;
            }
        }
        if (!Utils.Validator.isInteger(LOT_NUMBER)) {
            errorMessage.append("Lot number must be an integer\n");
            isValid = false;
        } else {
            if (!Utils.Validator.isGreaterThan(LOT_NUMBER, 0)) {
                errorMessage.append("Lot number must be greater than 0\n");
                isValid = false;
            }
        }
        if (!Utils.Validator.isDouble(LAND_AREA)) {
            errorMessage.append("Land area must be a number\n");
            isValid = false;
        } else {
            if (!Utils.Validator.isGreaterThan(LAND_AREA, 0)) {
                errorMessage.append("Land area must be greater than 0\n");
                isValid = false;
            }
        }
        return isValid;
    }

    // Checks if input for houseAndLand is valid
    private boolean validHouseInput () {
        boolean isValid = true;
        if (!Utils.Validator.isNotEmpty(HOUSE_AND_LAND_FIELDS)) {
            errorMessage.append("All fields must be filled\n");
            return false;
        }
        final String ID = txtHouseAndLandId.getText();
        final String LOT_NUMBER = txtLotNumberHouse.getText();
        final String LAND_AREA = txtLandAreaHouse.getText();
        final String CONSTRUCTED_AREA = txtConstructedAreaHouse.getText();
        final String BEDROOMS = txtBedroomsHouse.getText();
        final String TOILETS = txtToiletsHouse.getText();

        if (!Utils.Validator.isInteger(ID)) {
            errorMessage.append("ID must be an integer\n");
            isValid = false;
        } else {
            if (!Utils.Validator.isGreaterThan(ID, 0)) {
                errorMessage.append("ID must be greater than 0\n");
                isValid = false;
            }
            if (!Utils.Validator.idExists(ID, DataHandler.houseAndLandList)) {
                errorMessage.append("ID already exists\n");
                isValid = false;
            }
        }
        if (!Utils.Validator.isInteger(LOT_NUMBER)) {
            errorMessage.append("Lot number must be an integer\n");
            isValid = false;
        } else {
            if (!Utils.Validator.isGreaterThan(LOT_NUMBER, 0)) {
                errorMessage.append("Lot number must be greater than 0\n");
                isValid = false;
            }
        }
        if (!Utils.Validator.isDouble(LAND_AREA)) {
            errorMessage.append("Land area must be a number\n");
            isValid = false;
        } else {
            if (!Utils.Validator.isGreaterThan(LAND_AREA, 0)) {
                errorMessage.append("Land area must be greater than 0\n");
                isValid = false;
            }
        }
        if (!Utils.Validator.isDouble(CONSTRUCTED_AREA)) {
            errorMessage.append("Constructed area must be a number\n");
            isValid = false;
        } else {
            if (!Utils.Validator.isGreaterThan(CONSTRUCTED_AREA, 0)) {
                errorMessage.append("Constructed area must be greater than 0\n");
                isValid = false;
            } else {
                if (Double.parseDouble(CONSTRUCTED_AREA) >= Double.parseDouble(LAND_AREA)) {
                    errorMessage.append("Constructed area must be less than land area\n");
                    isValid = false;
                }
            }
        }
        if (!Utils.Validator.isInteger(BEDROOMS)) {
            errorMessage.append("Bedrooms must be an integer\n");
            isValid = false;
        }
        if (!Utils.Validator.isInteger(TOILETS)) {
            errorMessage.append("Toilets must be an integer\n");
            isValid = false;
        }
        return isValid;
    }

    // Makes land objects
    private Land makeLand() {
        final int ID = Integer.parseInt(txtLandId.getText());
        final int LOT_NUMBER = Integer.parseInt(txtLotNumberLand.getText());
        final String ADDRESS = txtAddressLand.getText();
        final double LAND_AREA = Double.parseDouble(txtLandAreaLand.getText());

        return new Land(ID, LOT_NUMBER, ADDRESS, LAND_AREA);
    }

    // Makes houseAndLand objects
    private HouseAndLand makeHouseAndLand() {
        final int ID = Integer.parseInt(txtHouseAndLandId.getText());
        final int LOT_NUMBER = Integer.parseInt(txtLotNumberHouse.getText());
        final String ADDRESS = txtAddressHouse.getText();
        final double LAND_AREA = Double.parseDouble(txtLandAreaHouse.getText());
        final double CONSTRUCTED_AREA = Double.parseDouble(txtConstructedAreaHouse.getText());
        final int BEDROOMS = Integer.parseInt(txtBedroomsHouse.getText());
        final int TOILETS = Integer.parseInt(txtToiletsHouse.getText());

        return new HouseAndLand(ID, LOT_NUMBER, ADDRESS, LAND_AREA, CONSTRUCTED_AREA, BEDROOMS, TOILETS);
    }

    // Clears all text fields
    private void clearFields() {
        Utils.Text.clearFields(LAND_FIELDS);
        Utils.Text.clearFields(HOUSE_AND_LAND_FIELDS);
    }

    // Switches to the main menu
    @FXML private void switchToMainMenu() throws Exception {
        try {
            App.setRoot("mainMenu");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void clearError() {
        errorMessage.setLength(0);
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        LAND_FIELDS = new TextField[]{txtLandId, txtLotNumberLand, txtAddressLand, txtLandAreaLand};
        HOUSE_AND_LAND_FIELDS = new TextField[]{txtHouseAndLandId, txtLotNumberHouse, txtAddressHouse, txtLandAreaHouse, txtConstructedAreaHouse, txtBedroomsHouse, txtToiletsHouse};
        errorMessage = new StringBuilder();
    }
}
