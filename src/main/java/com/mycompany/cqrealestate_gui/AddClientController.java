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
    @FXML private TextField txtBuyerID;             // POSITION 0 in BUYER_FIELDS
    @FXML private TextField txtBuyerFirstName;      // POSITION 1 in BUYER_FIELDS
    @FXML private TextField txtBuyerLastName;       // POSITION 2 in BUYER_FIELDS
    @FXML private TextField txtBuyerAddress;        // POSITION 3 in BUYER_FIELDS
    @FXML private TextField txtBuyerPhoneNumber;    // POSITION 4 in BUYER_FIELDS

    // Text Fields for Seller objects
    @FXML private TextField txtSellerID;            // POSITION 0 in SELLER_FIELDS
    @FXML private TextField txtSellerFirstName;     // POSITION 1 in SELLER_FIELDS
    @FXML private TextField txtSellerLastName;      // POSITION 2 in SELLER_FIELDS
    @FXML private TextField txtSellerAddress;       // POSITION 3 in SELLER_FIELDS
    @FXML private TextField txtSellerPhoneNumber;   // POSITION 4 in SELLER_FIELDS

    StringBuilder errorMessage; // Holds any error messages

    // Handles submit button for buyer objects
    @FXML private void handleSubmitBuyerButton () {
        try {
            if (isValidInput(BUYER_FIELDS, DataHandler.buyerList)) {
                DataHandler.buyerList.add(makeBuyer());
                Utils.Text.showConfirmation("Buyer added successfully");
                Utils.Text.clearFields(BUYER_FIELDS);
            }else {
                Utils.Text.showError(errorMessage.toString());
                clearError();
            }
        } catch (Exception e) {
            Utils.Text.showError("Error submitting buyer\n" + e.getMessage());
        }
    }

    // Handles submit button for seller objects
    @FXML private void handleSubmitSellerButton () {
        try {
            if (isValidInput(SELLER_FIELDS,DataHandler.sellerList)) {
                DataHandler.sellerList.add(makeSeller());
                Utils.Text.showConfirmation("Seller added successfully");
                Utils.Text.clearFields(SELLER_FIELDS);
            } else {
                Utils.Text.showError(errorMessage.toString());
                clearError();
            }
        } catch (Exception e) {
            Utils.Text.showError("Error submitting seller\n" + e.getMessage());
        }
    }

    // Checks if input is valid
    private boolean isValidInput(TextField[] fields, List <? extends Client> clients) {
        boolean isValid = true;
        if (!Utils.Validator.isNotEmpty(fields)) {
            errorMessage.append("All fields must be filled\n");
            return false;
        }

        // Assign values to check
        final String ID = fields[0].getText();
        final String FIRST_NAME = fields[1].getText();
        final String LAST_NAME = fields[2].getText();
        final String PHONE = fields[4].getText();

        if (!Utils.Validator.isInteger(ID)) {
            errorMessage.append("ID must be an integer\n");
            isValid = false;
        } else {
            if (!Utils.Validator.isGreaterThan(ID,0)) {
                errorMessage.append("ID must be greater than 0\n");
                isValid = false;
            }
            if (!Utils.Validator.idExists(ID, clients)) {
                errorMessage.append("ID already exists\n");
                isValid = false;
            }
        }
        if (!Utils.Validator.isName(FIRST_NAME)) {
            errorMessage.append("First name must be letters only\n");
            isValid = false;
        }
        if (!Utils.Validator.isName(LAST_NAME)) {
            errorMessage.append("Last name must be letters only\n");
            isValid = false;
        }
        if (!Utils.Validator.isPhoneNumber(PHONE)) {
            errorMessage.append("Phone number must be an 8 digit number\n");
            isValid = false;
        }

        return isValid;
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
        Utils.Text.clearFields(BUYER_FIELDS);
        Utils.Text.clearFields(SELLER_FIELDS);
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

