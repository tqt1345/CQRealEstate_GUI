/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.cqrealestate_gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import java.net.URL;
import java.util.ArrayList;
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

    StringBuilder errorMessage;

    /**
     * Initializes the controller class.
     */


    // Handles submit button for buyer objects
    @FXML private void handleSubmitBuyerButton () {
        try {
            if (isValidInput("Buyer")) {
                Buyer buyer = makeBuyer();
                DataHandler.buyerList.add(buyer);
                Utils.Text.showConfirmation("Buyer added successfully");
                clearFields();
            }else {
                showError();
            }
        } catch (Exception e) {
            Utils.Text.showError("Error submitting buyer\n" + e.getMessage());
        }
    }

    // Handles submit button for seller objects
    @FXML private void handleSubmitSellerButton () {
        try {
            if (isValidInput("Seller")) {
                Seller seller = makeSeller();
                DataHandler.sellerList.add(seller);
                Utils.Text.showConfirmation("Seller added successfully");
                clearFields();
            } else {
                showError();
            }
        } catch (Exception e) {
            Utils.Text.showError("Error submitting seller\n" + e.getMessage());
        }
    }

    private boolean isValidInput(String type) {
        boolean isValid = true;
        switch (type) {
            case "Buyer":
                if(!buyerIsNotEmpty()) {
                    isValid = false;
                }
                if(!isValidBuyerId()) {
                    isValid = false;
                }
                if(!isValidBuyerName()) {
                    isValid = false;
                }
                if(!isValidBuyerPhone()) {
                    isValid = false;
                }
                break;
            case "Seller":
                if(!sellerIsNotEmpty()) {
                    isValid = false;
                }
                if(!isValidSellerId()) {
                    isValid = false;
                }
                if(!isValidSellerName()) {
                    isValid = false;
                }
                if(!isValidSellerPhone()) {
                    isValid = false;
                }
                break;
        }
        return isValid;
    }

    private boolean buyerIsNotEmpty() {
        for (TextField field : BUYER_FIELDS) {
            final String INPUT_FIELD = field.getText();
            if (INPUT_FIELD.isEmpty()) {
                errorMessage.append("All fields must be filled\n");
                return false;
            }
        }
        return true;
    }

    private boolean sellerIsNotEmpty () {
        for (TextField field : SELLER_FIELDS) {
            final String INPUT_FIELD = field.getText();
            if (INPUT_FIELD.isEmpty()) {
                errorMessage.append("All fields must be filled\n");
                return false;
            }
        }
        return true;
    }

    private boolean isValidBuyerId() {

        final String BUYER_ID = txtBuyerID.getText();
        if (!Utils.Validator.isInteger(BUYER_ID)) {
            errorMessage.append("Buyer ID must be an integer\n");
            return false;
        }

        final int BUYER_ID_INT = Integer.parseInt(BUYER_ID);
        for (Buyer buyer : DataHandler.buyerList) {
            final int BUYER_ID_IN_LIST = buyer.getClientID();
            if (BUYER_ID_IN_LIST == BUYER_ID_INT) {
                errorMessage.append("Buyer ID already exists\n");
                return false;
            }
        }
        return true;
    }

    private boolean isValidSellerId() {

        final String SELLER_ID = txtSellerID.getText();
        if (!Utils.Validator.isInteger(SELLER_ID)) {
            errorMessage.append("Seller ID must be an integer\n");
            return false;
        }

        final int SELLER_ID_INT = Integer.parseInt(SELLER_ID);
        for (Seller seller : DataHandler.sellerList) {
            final int SELLER_ID_IN_LIST = seller.getClientID();
            if (SELLER_ID_IN_LIST == SELLER_ID_INT) {
                errorMessage.append("Seller ID already exists\n");
                return false;
            }
        }
        return true;
    }

    private boolean isValidBuyerName() {
        final String BUYER_FIRST_NAME = txtBuyerFirstName.getText();
        if (!Utils.Validator.isName(BUYER_FIRST_NAME)) {
            errorMessage.append("Name must have letters only\n");
            return false;
        }
        return true;
    }

    private boolean isValidSellerName() {
        final String SELLER_FIRST_NAME = txtSellerFirstName.getText();
        if (!Utils.Validator.isName(SELLER_FIRST_NAME)) {
            errorMessage.append("Name must have letters only\n");
            return false;
        }
        return true;
    }

    private boolean isValidBuyerPhone() {
        final String BUYER_PHONE_NUMBER = txtBuyerPhoneNumber.getText();
        if (!Utils.Validator.isPhoneNumber(BUYER_PHONE_NUMBER)) {
            errorMessage.append("Phone number must be an 8 digit number\n");
            return false;
        }
        return true;
    }

    private boolean isValidSellerPhone() {
        final String SELLER_PHONE_NUMBER = txtSellerPhoneNumber.getText();
        if (!Utils.Validator.isPhoneNumber(SELLER_PHONE_NUMBER)) {
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
        txtBuyerID.clear();
        txtBuyerFirstName.clear();
        txtBuyerLastName.clear();
        txtBuyerAddress.clear();
        txtBuyerPhoneNumber.clear();
    }

    private void clearSellerFields() {
        txtSellerID.clear();
        txtSellerFirstName.clear();
        txtSellerLastName.clear();
        txtSellerAddress.clear();
        txtSellerPhoneNumber.clear();
    }

    // Clear the error message
    private void clearError() {
        errorMessage = new StringBuilder();
    }

    private void showError() {
        Utils.Text.showError(errorMessage.toString());
        clearError();
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
