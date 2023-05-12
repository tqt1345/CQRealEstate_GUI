/*
Programmer: Tarique Turnbull
StudentID: 12177936
Course: COIT11134 Object-Oriented Programming
Assessment: Practical Assessment 2

This class controls functionality for adding clients
 */
package com.mycompany.cqrealestate_gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author tqt13
 */
public class AddClientController implements Initializable {

    // Text Field arrays
    private TextField[] BUYER_FIELDS;
    private TextField[] SELLER_FIELDS;

    // Text Fields for Buyer objects
    @FXML private TextField txtBuyerID;
    @FXML private TextField txtBuyerFirstName;
    @FXML private TextField txtBuyerLastName;
    @FXML private TextField txtBuyerAddress;
    @FXML private TextField txtBuyerPhoneNumber;

    // Text Fields for Seller objects
    @FXML private TextField txtSellerID;
    @FXML private TextField txtSellerFirstName;
    @FXML private TextField txtSellerLastName;
    @FXML private TextField txtSellerAddress;
    @FXML private TextField txtSellerPhoneNumber;

    StringBuilder errorMessage; // Holds any error messages

    // Handles submit button for buyer objects
    @FXML private void handleSubmitBuyerButton () {
        try {
            if (validBuyerInput()) {
                DataHandler.buyerList.add(makeBuyer());
                Utils.Text.showConfirmation("Buyer added successfully");
                clearFields();
            }else {
                showError();
                clearError();
            }
        } catch (Exception e) {
            Utils.Text.showError("Error submitting buyer\n" + e.getMessage());
        }
    }

    // Handles submit button for seller objects
    @FXML private void handleSubmitSellerButton () {
        try {
            if (validSellerInput()) {
                DataHandler.sellerList.add(makeSeller());
                Utils.Text.showConfirmation("Seller added successfully");
                clearFields();
            } else {
                showError();
                clearError();
            }
        } catch (Exception e) {
            Utils.Text.showError("Error submitting seller\n" + e.getMessage());
        }
    }

    private boolean validBuyerInput() {
        boolean isValid = true;
        if (!isNotEmpty(BUYER_FIELDS)) {
            return false;
        }

        final String ID = txtBuyerID.getText();
        final String FIRST_NAME = txtBuyerFirstName.getText();
        final String LAST_NAME = txtBuyerLastName.getText();
        final String PHONE = txtBuyerPhoneNumber.getText();

        if (!isValidID(ID, DataHandler.buyerList)) {
            isValid = false;
        }
        if (!isValidName(FIRST_NAME)) {
            isValid = false;
        }
        if (!isValidName(LAST_NAME)) {
            isValid = false;
        }
        if (!isValidPhone(PHONE)) {
            isValid = false;
        }

        return isValid;
    }

    private boolean validSellerInput() {
        boolean isValid = true;
        if (!isNotEmpty(SELLER_FIELDS)) {
            return false;
        }

        final String ID = txtSellerID.getText();
        final String FIRST_NAME = txtSellerFirstName.getText();
        final String LAST_NAME = txtSellerLastName.getText();
        final String PHONE = txtSellerPhoneNumber.getText();

        if (!isValidID(ID, DataHandler.sellerList)) {
            isValid = false;
        }
        if (!isValidName(FIRST_NAME)) {
            isValid = false;
        }
        if (!isValidName(LAST_NAME)) {
            isValid = false;
        }
        if (!isValidPhone(PHONE)) {
            isValid = false;
        }
        return isValid;
    }

    private boolean isNotEmpty (TextField[] fields) {
        for (TextField field : fields) {
            final String INPUT_FIELD = field.getText();
            if (INPUT_FIELD.isEmpty()) {
                errorMessage.append("All fields must be filled\n");
                return false;
            }
        }
        return true;
    }

    private boolean isValidID(String id, List<? extends Client> clients) {
        if (!Utils.Validator.isInteger(id)) {
            errorMessage.append("ID must be an integer\n");
            return false;
        }

        final int ID_INT = Integer.parseInt(id);
        if (ID_INT <= 0) {
            errorMessage.append("ID must be a positive integer\n");
            return false;
        }

        for (Client client : clients) {
            final int CLIENT_ID = client.getClientID();
            if (CLIENT_ID == Integer.parseInt(id)) {
                errorMessage.append("ID already exists\n");
                return false;
            }
        }
        return true;
    }

    private boolean isValidName(String name) {
        if (!Utils.Validator.isName(name)) {
            errorMessage.append("Name must have letters only\n");
            return false;
        }
        return true;
    }

    private boolean isValidPhone(String phone) {
        if (!Utils.Validator.isPhoneNumber(phone)) {
            errorMessage.append("Phone number must be an 8 digit number\n");
            return false;
        }
        return true;
    }

    // Creates a new buyer object
    private Buyer makeBuyer() {
        final int BUYER_ID = Integer.parseInt(txtBuyerID.getText());
        final String BUYER_FIRST_NAME = txtBuyerFirstName.getText();
        final String BUYER_LAST_NAME = txtBuyerLastName.getText();
        final String BUYER_ADDRESS = txtBuyerAddress.getText();
        final String BUYER_PHONE_NUMBER = txtBuyerPhoneNumber.getText();

        return new Buyer(BUYER_ID, BUYER_FIRST_NAME, BUYER_LAST_NAME, BUYER_ADDRESS, BUYER_PHONE_NUMBER);
    }

    // Creates a new seller object
    private Seller makeSeller() {
        final int SELLER_ID = Integer.parseInt(txtSellerID.getText());
        final String SELLER_FIRST_NAME = txtSellerFirstName.getText();
        final String SELLER_LAST_NAME = txtSellerLastName.getText();
        final String SELLER_ADDRESS = txtSellerAddress.getText();
        final String SELLER_PHONE_NUMBER = txtSellerPhoneNumber.getText();

        return new Seller(SELLER_ID,SELLER_FIRST_NAME, SELLER_LAST_NAME, SELLER_ADDRESS, SELLER_PHONE_NUMBER);
    }

    // Clear all the text fields
    private void clearFields() {
        clearBuyerFields();
        clearSellerFields();
    }

    private void clearBuyerFields() {
        for (TextField field : BUYER_FIELDS) {
            field.clear();
        }
    }

    private void clearSellerFields() {
        for (TextField field : SELLER_FIELDS) {
            field.clear();
        }
    }

    // Clear the error message
    private void clearError() {
        errorMessage.setLength(0);
    }

    private void showError() {
        Utils.Text.showError(errorMessage.toString());
    }
    // Switch to main menu
    public void switchToMainMenu() {
        try {
            App.setRoot("mainMenu");
        } catch (Exception e) {
            Utils.Text.showError("Error switching to main menu\n" + e.getMessage());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        BUYER_FIELDS = new TextField[]{txtBuyerID, txtBuyerFirstName, txtBuyerLastName, txtBuyerAddress, txtBuyerPhoneNumber};
        SELLER_FIELDS = new TextField[]{txtSellerID, txtSellerFirstName, txtSellerLastName, txtSellerAddress, txtSellerPhoneNumber};
        errorMessage = new StringBuilder();
    }

}
