/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.cqrealestate_gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

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

    @FXML private void handleSubmitBuyerButton () {
        try {
            if (isValidInput("Buyer")) {
                Buyer buyer = makeBuyer();

                DataHandler.buyerList.add(buyer);

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Buyer added successfully");
                alert.showAndWait();
                clearFields();
            }
        } catch (Exception e) {
            // TODO JOptionPane
            System.out.println(e.getMessage());
        }
    }

    @FXML private void handleSubmitSellerButton () {
        try {
            if (isValidInput("Seller")) {
                Seller seller = makeSeller();

                DataHandler.sellerList.add(seller);

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Seller added successfully");
                alert.showAndWait();
                clearFields();
            }
        } catch (Exception e) {
            // TODO JOptionPane
            System.out.println(e.getMessage());
        }
    }

    private Buyer makeBuyer() {

        int buyerID = Integer.parseInt(txtBuyerID.getText());
        String firstName = txtBuyerFirstName.getText();
        String lastName = txtBuyerLastName.getText();
        String address = txtBuyerAddress.getText();
        String phoneNumber = txtBuyerPhoneNumber.getText();

        return new Buyer(buyerID,firstName, lastName, address, phoneNumber);
    }
    private Seller makeSeller() {
        int sellerID = Integer.parseInt(txtSellerID.getText());
        String firstName = txtSellerFirstName.getText();
        String lastName = txtSellerLastName.getText();
        String address = txtSellerAddress.getText();
        String phoneNumber = txtSellerPhoneNumber.getText();

        return new Seller(sellerID,firstName, lastName, address, phoneNumber);
    }

    private boolean isValidInput(String type) {
        StringBuilder errorMessage = new StringBuilder();
        switch (type) {
            case "Buyer":
                if (txtBuyerFirstName.getText().isEmpty() ||
                        txtBuyerLastName.getText().isEmpty() ||
                        txtBuyerAddress.getText().isEmpty() ||
                        txtBuyerPhoneNumber.getText().isEmpty()) {
                    errorMessage.append("Please fill in all fields\n");
                }
                for (Buyer buyer : DataHandler.buyerList) {
                    if ((buyer.getClientID() == Integer.parseInt(txtBuyerID.getText()))) {
                        errorMessage.append("Client ID already exists\n");
                    }
                }
                if (!Utils.Validator.isName(txtBuyerFirstName.getText())) {
                    errorMessage.append("Invalid name. Letters only\n");
                }
                if (!Utils.Validator.isPhoneNumber(txtBuyerPhoneNumber.getText())) {
                    errorMessage.append("Invalid phone number. 8 digit number only\n");
                }
                if (errorMessage.length() > 0) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, errorMessage.toString());
                    alert.showAndWait();
                    return false;
                }
                break;
            case "Seller":
                if (txtSellerFirstName.getText().isEmpty() ||
                        txtSellerLastName.getText().isEmpty() ||
                        txtSellerAddress.getText().isEmpty() ||
                        txtSellerPhoneNumber.getText().isEmpty()) {
                    errorMessage.append("Please fill in all fields\n");
                }
                for (Seller seller : DataHandler.sellerList) {
                    if ((seller.getClientID() == Integer.parseInt(txtSellerID.getText()))) {
                        errorMessage.append("Seller ID already exists\n");
                    }
                }
                if (!Utils.Validator.isName(txtSellerFirstName.getText())) {
                    errorMessage.append("Invalid name. Letters only\n");
                }
                if (!Utils.Validator.isPhoneNumber(txtSellerPhoneNumber.getText())) {
                    errorMessage.append("Invalid phone number. 8 digit number only\n");
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
        txtBuyerFirstName.clear();
        txtBuyerLastName.clear();
        txtBuyerAddress.clear();
        txtBuyerPhoneNumber.clear();

        txtSellerFirstName.clear();
        txtSellerLastName.clear();
        txtSellerAddress.clear();
        txtSellerPhoneNumber.clear();
    }
    public void switchToMainMenu() throws Exception {
        try {
            App.setRoot("mainMenu");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    
}
