/*
Programmer: Tarique Turnbull
StudentID: 12177936
Course: COIT11134 Object-Oriented Programming
Assessment: Practical Assessment 2

This class controls functionality for adding sales
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
public class AddSaleController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    // Text Fields for Land Sale objects
    @FXML private TextField txtSaleIdLand;
    @FXML private TextField txtDateLand;
    @FXML private TextField txtSoldPriceLand;
    @FXML private TextField txtLandIdLand;
    @FXML private TextField txtBuyerIdLand;
    @FXML private TextField txtSellerIdLand;

    // Text Fields for HouseAndLandSale Sale objects
    @FXML private TextField txtSaleIdHouseAndLand;
    @FXML private TextField txtDateHouseAndLand;
    @FXML private TextField txtSoldPriceHouseAndLand;
    @FXML private TextField txtHouseAndLandIdHouseAndLand;
    @FXML private TextField txtBuyerIdHouseAndLand;
    @FXML private TextField txtSellerIdHouseAndLand;


    // Handles the submit button for sale objects with Land
    @FXML private void handleSubmitLandSaleButton () {
        try {
            if(isValidInput("Land")) {
                // Make sale object from input
                Sale sale = makeSale("Land");
                DataHandler.saleList.add(sale);

                // Show confirmation and clear fields
                Utils.Text.showConfirmation("Sale added successfully");
                clearFields();
            }
        }catch (Exception e) {
            Utils.Text.showError(e.getMessage());
        }
    }

    // Handles the submit button for sale objects with houseAndLand
    @FXML private void handleSubmitHouseAndLandSaleButton () {
        try {
            if(isValidInput("HouseAndLand")) {
                // Make sale object from input
                Sale sale = makeSale("HouseAndLand");
                DataHandler.saleList.add(sale);

                // Show confirmation and clear fields
                Utils.Text.showConfirmation("Sale added successfully");
                clearFields();
            }
        } catch (Exception e) {
            Utils.Text.showError(e.getMessage());
        }
    }

    // Makes a sale object depending on if it is Land or HouseAndLand
    private Sale makeSale(String type){
        switch (type) {
            case "Land":
                int landSaleId = Integer.parseInt(txtSaleIdLand.getText());
                String landDate = txtDateLand.getText();
                double landSoldPrice = Double.parseDouble(txtSoldPriceLand.getText());
                Land land = getLand(txtLandIdLand.getText());
                Seller landSeller = getSeller(txtSellerIdLand.getText());
                Buyer landBuyer = getBuyer(txtBuyerIdLand.getText());

                return new Sale(landSaleId, landDate, landSoldPrice, land, landSeller, landBuyer);
            case "HouseAndLand":
                int houseAndLandSaleId = Integer.parseInt(txtSaleIdHouseAndLand.getText());
                String houseAndLandDate = txtDateHouseAndLand.getText();
                double houseAndLandSoldPrice = Double.parseDouble(txtSoldPriceHouseAndLand.getText());
                HouseAndLand houseAndLand = getHouseAndLand(txtHouseAndLandIdHouseAndLand.getText());
                Seller houseAndLandSeller = getSeller(txtSellerIdHouseAndLand.getText());
                Buyer houseAndLandBuyer = getBuyer(txtBuyerIdHouseAndLand.getText());

                return new Sale(
                        houseAndLandSaleId,
                        houseAndLandDate,
                        houseAndLandSoldPrice,
                        houseAndLand,
                        houseAndLandSeller,
                        houseAndLandBuyer);
        }
        return null;
    }

    // Gets the land object specified from input in textField
    private Land getLand(String Id) {
        for (Land land : DataHandler.landList) {
            if (land.getPropertyId() == Integer.parseInt(Id)) {
                return land;
            }
        }
        return null;
    }

    // Gets the HouseAndLand object specified from textField
    private HouseAndLand getHouseAndLand(String Id) {
        for (HouseAndLand houseAndLand : DataHandler.houseAndLandList) {
            if (houseAndLand.getPropertyId() == Integer.parseInt(Id)) {
                return houseAndLand;
            }
        }
        return null;
    }

    // Gets Seller object specified from textField
    private Seller getSeller(String Id) {

        for (Seller seller : DataHandler.sellerList) {
            if (seller.getClientID() == Integer.parseInt(Id)) {
                return seller;
            }
        }
        return null;
    }

    // Gets Buyer object specified from textField
    private Buyer getBuyer(String Id) {
        for (Buyer buyer : DataHandler.buyerList) {
            if (buyer.getClientID() == Integer.parseInt(Id)) {
                return buyer;
            }
        }
        return null;
    }


    // Checks if text fields are valid depending on type
    private boolean isValidInput(String type) {
        StringBuilder errorMessage = new StringBuilder();
        switch (type) {
            case "Land":
                if (    // Checks if fields are empty
                        txtSaleIdLand.getText().isEmpty() ||
                        txtDateLand.getText().isEmpty() ||
                        txtSoldPriceLand.getText().isEmpty() ||
                        txtLandIdLand.getText().isEmpty() ||
                        txtBuyerIdLand.getText().isEmpty() ||
                        txtSellerIdLand.getText().isEmpty()) {
                        errorMessage.append("All fields must be filled.\n");
                }


                // Checks if sale id is an int
                if (!Utils.Validator.isInteger(txtSaleIdLand.getText())) {
                    errorMessage.append("Sale ID must be an integer\n");
                } else { // if int, check if it exists already
                    for (Sale sale : DataHandler.saleList) {
                        if (sale.getSaleID() == Integer.parseInt(txtSaleIdLand.getText())) {
                            errorMessage.append("Sale ID already exists\n");
                        }
                    }
                }

                // checks if date is valid
                if (!Utils.Validator.isDate(txtDateLand.getText())) {
                    errorMessage.append("Invalid date, must be in the format: (dd/mm/yyyy)\n");
                }

                // checks if sold price is a double
                if (!Utils.Validator.isDouble(txtSoldPriceLand.getText())) {
                    errorMessage.append("Sold price must be a double\n");
                }

                // checks if land id is an int
                if (!Utils.Validator.isInteger(txtLandIdLand.getText())) {
                    errorMessage.append("Land ID must be an integer\n");
                } else { // if int, checks if there are any land objects with that id
                    boolean landExists = false;
                    for (Land land : DataHandler.landList) {
                        if (land.getPropertyId() == Integer.parseInt(txtLandIdLand.getText())) {
                            landExists = true;
                        }
                    }

                    // No land entries exist with inputted id
                    if (!landExists) {
                        errorMessage.append("No Land entry with ID: ");
                        errorMessage.append(txtLandIdLand.getText());
                        errorMessage.append("\n");
                    }
                }

                // Checks if buyer id is an int
                if (!Utils.Validator.isInteger(txtBuyerIdLand.getText())) {
                    errorMessage.append("Buyer ID must be an integer\n");
                } else { // if int, checks if any buyer object has that id
                    boolean buyerExists = false;
                    for (Buyer buyer : DataHandler.buyerList) {
                        if (buyer.getClientID() == Integer.parseInt(txtBuyerIdLand.getText())) {
                            buyerExists = true;
                        }
                    }

                    // no buyer with inputted id
                    if (!buyerExists) {
                        errorMessage.append("No Buyer entry with ID: ");
                        errorMessage.append(txtBuyerIdLand.getText());
                        errorMessage.append("\n");
                    }
                }

                // Checks if seller id is an int
                if (!Utils.Validator.isInteger(txtSellerIdLand.getText())) {
                    errorMessage.append("Seller ID must be an integer\n");
                } else { // if int, checks if any seller object has that id
                    boolean sellerExists = false;
                    for (Seller seller : DataHandler.sellerList) {
                        if (seller.getClientID() == Integer.parseInt(txtSellerIdLand.getText())) {
                            sellerExists = true;
                        }
                    }

                    // no seller with inputted id
                    if (!sellerExists) {
                        errorMessage.append("No Seller entry with ID: ");
                        errorMessage.append(txtSellerIdLand.getText());
                        errorMessage.append("\n");
                    }
                }

                // If there are any errors, show them
                if (errorMessage.length() > 0) {
                    Utils.Text.showError(errorMessage.toString());
                    return false;
                }
                break;

            case "HouseAndLand": // Similar to previous case
                if (    txtSaleIdHouseAndLand.getText().isEmpty() ||
                        txtDateHouseAndLand.getText().isEmpty() ||
                        txtSoldPriceHouseAndLand.getText().isEmpty() ||
                        txtHouseAndLandIdHouseAndLand.getText().isEmpty() ||
                        txtBuyerIdHouseAndLand.getText().isEmpty() ||
                        txtSellerIdHouseAndLand.getText().isEmpty()) {
                        errorMessage.append("All fields must be filled.\n");
                }
                if (!Utils.Validator.isInteger(txtSaleIdHouseAndLand.getText())) {
                    errorMessage.append("Sale ID must be an integer\n");
                } else {
                    for (Sale sale : DataHandler.saleList) {
                        if (sale.getSaleID() == Integer.parseInt(txtSaleIdHouseAndLand.getText())) {
                            errorMessage.append("Sale ID already exists\n");
                        }
                    }
                }
                if (!Utils.Validator.isDate(txtDateHouseAndLand.getText())) {
                    errorMessage.append("Invalid date, must be in the format: (dd/mm/yyyy)\n");
                }
                if (!Utils.Validator.isDouble(txtSoldPriceHouseAndLand.getText())) {
                    errorMessage.append("Sold price must be a double\n");
                }
                if (!Utils.Validator.isInteger(txtHouseAndLandIdHouseAndLand.getText())) {
                    errorMessage.append("House and Land ID must be an integer\n");
                } else {
                    boolean houseAndLandExists = false;
                    for (HouseAndLand houseAndLand : DataHandler.houseAndLandList) {
                        if (houseAndLand.getPropertyId() == Integer.parseInt(txtHouseAndLandIdHouseAndLand.getText())) {
                            houseAndLandExists = true;
                        }
                    }
                    if (!houseAndLandExists) {
                        errorMessage.append("No House and Land entry with ID: ");
                        errorMessage.append(txtHouseAndLandIdHouseAndLand.getText());
                        errorMessage.append("\n");
                    }
                }
                if (!Utils.Validator.isInteger(txtBuyerIdHouseAndLand.getText())) {
                    errorMessage.append("Buyer ID must be an integer\n");
                } else {
                    boolean buyerExists = false;
                    for (Buyer buyer : DataHandler.buyerList) {
                        if (buyer.getClientID() == Integer.parseInt(txtBuyerIdHouseAndLand.getText())) {
                            buyerExists = true;
                            break;
                        }
                    }
                    if (!buyerExists) {
                        errorMessage.append("No Buyer entry with ID: ");
                        errorMessage.append(txtBuyerIdHouseAndLand.getText());
                        errorMessage.append("\n");
                    }
                }
                if (!Utils.Validator.isInteger(txtSellerIdHouseAndLand.getText())) {
                    errorMessage.append("Seller ID must be an integer\n");
                } else {
                    boolean sellerExists = false;
                    for (Seller seller : DataHandler.sellerList) {
                        if (!(Integer.parseInt(txtSellerIdHouseAndLand.getText()) == seller.getClientID())) {
                            sellerExists = true;
                            break;
                        }
                    }
                    if (!sellerExists) {
                        errorMessage.append("No Seller entry with ID: ");
                        errorMessage.append(txtSellerIdHouseAndLand.getText());
                        errorMessage.append("\n");
                    }
                }
                if (errorMessage.length() > 0) {
                    Utils.Text.showError(errorMessage.toString());
                    return false;
                }
        }
        return true;
    }

    // Clears all fields
    private void clearFields() {

        // Clears fields for land sales
        txtSaleIdLand.clear();
        txtDateLand.clear();
        txtSoldPriceLand.clear();
        txtLandIdLand.clear();
        txtBuyerIdLand.clear();
        txtSellerIdLand.clear();

        // Clears fields for house and land sales
        txtSaleIdHouseAndLand.clear();
        txtDateHouseAndLand.clear();
        txtSoldPriceHouseAndLand.clear();
        txtHouseAndLandIdHouseAndLand.clear();
        txtBuyerIdHouseAndLand.clear();
        txtSellerIdHouseAndLand.clear();
    }

    // Switches to main menu
    @FXML private void switchToMainMenu() {
        try {
            App.setRoot("mainMenu");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
