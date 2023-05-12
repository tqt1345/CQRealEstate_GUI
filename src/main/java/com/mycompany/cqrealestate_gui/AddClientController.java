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
import java.util.ResourceBundle;

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

    // Handles submit button for buyer objects
    @FXML private void handleSubmitBuyerButton () {
        try {
            if (isValidInput("Buyer")) {
                Buyer buyer = makeBuyer();
                DataHandler.buyerList.add(buyer);
                Utils.Text.showConfirmation("Buyer added successfully");
                clearFields();
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
            }
        } catch (Exception e) {
            Utils.Text.showError("Error submitting seller\n" + e.getMessage());
        }
    }

    // Creates a new buyer object
    private Buyer makeBuyer() {
        int buyerID = Integer.parseInt(txtBuyerID.getText());
        String firstName = txtBuyerFirstName.getText();
        String lastName = txtBuyerLastName.getText();
        String address = txtBuyerAddress.getText();
        String phoneNumber = txtBuyerPhoneNumber.getText();

        return new Buyer(buyerID,firstName, lastName, address, phoneNumber);
    }

    // Creates a new seller object
    private Seller makeSeller() {
        int sellerID = Integer.parseInt(txtSellerID.getText());
        String firstName = txtSellerFirstName.getText();
        String lastName = txtSellerLastName.getText();
        String address = txtSellerAddress.getText();
        String phoneNumber = txtSellerPhoneNumber.getText();

        return new Seller(sellerID,firstName, lastName, address, phoneNumber);
    }

    // Checks if textFields are valid depending on the type of client
    private boolean isValidInput(String type) {
        try {
            StringBuilder errorMessage = new StringBuilder(); // Holds error messages
            switch (type) {
                case "Buyer":
                    if (    // Checks if any fields are empty
                            txtBuyerID.getText().isEmpty() ||
                            txtBuyerFirstName.getText().isEmpty() ||
                            txtBuyerLastName.getText().isEmpty() ||
                            txtBuyerAddress.getText().isEmpty() ||
                            txtBuyerPhoneNumber.getText().isEmpty()) {
                            errorMessage.append("Please fill in all fields\n");
                    }

                    // Checks of inputted buyer id is an integer
                    if (!Utils.Validator.isInteger(txtBuyerID.getText())) {
                        errorMessage.append("Buyer ID must be an integer\n");
                    } else { // if it is an integer, checks if it already exists
                        for (Buyer buyer : DataHandler.buyerList) {
                            if ((buyer.getClientID() == Integer.parseInt(txtBuyerID.getText()))) {
                                errorMessage.append("Buyer ID already exists\n");
                            }
                        }
                    }

                    // Checks if inputted name is correct
                    if (!Utils.Validator.isName(txtBuyerFirstName.getText())) {
                        errorMessage.append("Invalid name. Letters only\n");
                    }

                    // Checks if inputted phone number is correct
                    if (!Utils.Validator.isPhoneNumber(txtBuyerPhoneNumber.getText())) {
                        errorMessage.append("Invalid phone number. 8 digit number only\n");
                    }

                    // If any error messages are stored, display them and return false
                    if (errorMessage.length() > 0) {
                        Utils.Text.showError(errorMessage.toString());
                        return false;
                    }
                    break;
                case "Seller":
                    // Same as Buyer but for seller objects
                    if (    txtSellerID.getText().isEmpty() ||
                            txtSellerFirstName.getText().isEmpty() ||
                            txtSellerLastName.getText().isEmpty() ||
                            txtSellerAddress.getText().isEmpty() ||
                            txtSellerPhoneNumber.getText().isEmpty()) {
                            errorMessage.append("Please fill in all fields\n");
                    }
                    if (!Utils.Validator.isInteger(txtSellerID.getText())) {
                        errorMessage.append("Seller ID must be an integer\n");
                    } else {
                        for (Seller seller : DataHandler.sellerList) {
                            if ((seller.getClientID() == Integer.parseInt(txtSellerID.getText()))) {
                                errorMessage.append("Seller ID already exists\n");
                            }
                        }
                    }
                    if (!Utils.Validator.isName(txtSellerFirstName.getText())) {
                        errorMessage.append("Invalid name. Letters only\n");
                    }
                    if (!Utils.Validator.isPhoneNumber(txtSellerPhoneNumber.getText())) {
                        errorMessage.append("Invalid phone number. 8 digit number only\n");
                    }
                    if (errorMessage.length() > 0) {
                        Utils.Text.showError(errorMessage.toString());
                        return false;
                    }
                    break;
            }
        }catch (Exception e) {
            Utils.Text.showError("Error validating input\n" + e.getMessage());
        }

        return true;
    }

    // Clear all the text fields
    private void clearFields() {

        // Clear Buyer text fields
        txtBuyerID.clear();
        txtBuyerFirstName.clear();
        txtBuyerLastName.clear();
        txtBuyerAddress.clear();
        txtBuyerPhoneNumber.clear();

        // Clear Seller text fields
        txtSellerID.clear();
        txtSellerFirstName.clear();
        txtSellerLastName.clear();
        txtSellerAddress.clear();
        txtSellerPhoneNumber.clear();
    }

    // Switch to main menu
    public void switchToMainMenu() {
        try {
            App.setRoot("mainMenu");
        } catch (Exception e) {
            Utils.Text.showError("Error switching to main menu\n" + e.getMessage());
        }
    }
}
