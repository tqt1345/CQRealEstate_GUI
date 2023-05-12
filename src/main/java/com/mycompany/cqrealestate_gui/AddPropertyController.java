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
import java.util.List;
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
                showError();
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
                showError();
                clearError();
            }
        } catch (Exception e) {
            Utils.Text.showError(e.getMessage());
        }
    }

    private boolean validLandInput() {
        boolean isValid = true;
        if (!isNotEmpty(LAND_FIELDS)) {
            return false;
        }

        final String ID = txtLandId.getText();
        final String LOT_NUMBER = txtLotNumberLand.getText();
        final String LAND_AREA = txtLandAreaLand.getText();

        if (!isValidId(ID, DataHandler.landList)) {
            isValid = false;
        }
        if (!isValidLotNumber(LOT_NUMBER)) {
            isValid = false;
        }
        if (!isValidLandArea(LAND_AREA)) {
            isValid = false;
        }
        return isValid;
    }

    private boolean validHouseInput () {
        boolean isValid = true;
        if (!isNotEmpty(HOUSE_AND_LAND_FIELDS)) {
            return false;
        }
        final String ID = txtHouseAndLandId.getText();
        final String LOT_NUMBER = txtLotNumberHouse.getText();
        final String LAND_AREA = txtLandAreaHouse.getText();
        final String CONSTRUCTED_AREA = txtConstructedAreaHouse.getText();
        final String BEDROOMS = txtBedroomsHouse.getText();
        final String TOILETS = txtToiletsHouse.getText();

        if (!isValidId(ID, DataHandler.houseAndLandList)) {
            isValid = false;
        }
        if (!isValidLotNumber(LOT_NUMBER)) {
            isValid = false;
        }
        if (!isValidLandArea(LAND_AREA)) {
            isValid = false;
        }
        if (!isValidConstructedArea(LAND_AREA, CONSTRUCTED_AREA)) {
            isValid = false;
        }
        if (!isValidBedrooms(BEDROOMS)) {
            isValid = false;
        }
        if (!isValidToilets(TOILETS)) {
            isValid = false;
        }
        return isValid;
    }
    private boolean isNotEmpty (TextField [] fields) {
        for (TextField field : fields) {
            final String INPUT_FIELD = field.getText();
            if (INPUT_FIELD.isEmpty()) {
                errorMessage.append("All fields must be filled\n");
                return false;
            }
        }
        return true;
    }

    private boolean isValidId(String id, List<? extends Land> properties) {

        if (!Utils.Validator.isInteger(id)) {
            errorMessage.append("ID must be an integer\n");
            return false;
        }
        final int ID_INT = Integer.parseInt(id);

        if (ID_INT <= 0) {
            errorMessage.append("ID must be greater than 0\n");
            return false;
        }

        for (Land property : properties) {
            final int ID_IN_LIST = property.getPropertyId();
            if (ID_INT == ID_IN_LIST) {
                errorMessage.append("ID already exists\n");
                return false;
            }
        }
        return true;
    }

    private boolean isValidLotNumber(String lotNumber) {
        if (!Utils.Validator.isInteger(lotNumber)) {
            errorMessage.append("Lot number must be an integer\n");
            return false;
        }
        if (Integer.parseInt(lotNumber) <= 0) {
            errorMessage.append("Lot number must be greater than 0\n");
            return false;
        }
        return true;
    }

    private boolean isValidLandArea(String landArea) {
        if (!Utils.Validator.isDouble(landArea)) {
            errorMessage.append("Land area must be a double\n");
            return false;
        }
        if (Double.parseDouble(landArea) <= 0) {
            errorMessage.append("Land area must be greater than 0\n");
            return false;
        }
        return true;
    }

    private boolean isValidConstructedArea(String landArea, String constructedArea) {
        if (!Utils.Validator.isDouble(constructedArea)) {
            errorMessage.append("Constructed area must be a double\n");
            return false;
        }
        final double CONSTRUCTED_AREA_DOUBLE = Double.parseDouble(constructedArea);
        if (CONSTRUCTED_AREA_DOUBLE <= 0) {
            errorMessage.append("Constructed area must be greater than 0\n");
            return false;
        }
        final int LAND_AREA = Integer.parseInt(landArea);
        if (CONSTRUCTED_AREA_DOUBLE >= LAND_AREA) {
            errorMessage.append("Constructed area must be less than land area\n");
            return false;
        }
        return true;
    }

    private boolean isValidBedrooms(String bedrooms) {
        if (!Utils.Validator.isInteger(bedrooms)) {
            errorMessage.append("Bedrooms must be an integer\n");
            return false;
        }
        if (Integer.parseInt(bedrooms) <= 0) {
            errorMessage.append("Bedrooms must be greater than 0\n");
            return false;
        }
        return true;
    }

    private boolean isValidToilets(String toilets) {
        if (!Utils.Validator.isInteger(toilets)) {
            errorMessage.append("Toilets must be an integer\n");
            return false;
        }
        if (Integer.parseInt(toilets) <= 0) {
            errorMessage.append("Toilets must be greater than 0\n");
            return false;
        }
        return true;
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
        clearLandFields();
        clearHouseFields();
    }

    private void clearLandFields() {
        for (TextField field : LAND_FIELDS) {
            field.clear();
        }
    }

    private void clearHouseFields() {
        for (TextField field : HOUSE_AND_LAND_FIELDS) {
            field.clear();
        }
    }
    // Switches to the main menu
    @FXML private void switchToMainMenu() throws Exception {
        try {
            App.setRoot("mainMenu");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void showError() {
        Utils.Text.showError(errorMessage.toString());
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
