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

    // Text Field arrays
    private TextField[] LAND_SALE_FIELDS;
    private TextField[] HOUSE_SALE_FIELDS;

    // Text Fields for Land Sale objects
    @FXML private TextField txtLandSaleId;              // POSITION 0 in LAND_SALE_FIELDS
    @FXML private TextField txtLandDate;                // POSITION 1 in LAND_SALE_FIELDS
    @FXML private TextField txtLandSolePrice;           // POSITION 2 in LAND_SALE_FIELDS
    @FXML private TextField txtLandId;                  // POSITION 3 in LAND_SALE_FIELDS
    @FXML private TextField txtLandBuyerId;             // POSITION 4 in LAND_SALE_FIELDS
    @FXML private TextField txtLandSellerId;            // POSITION 5 in LAND_SALE_FIELDS

    // Text Fields for HouseAndLandSale Sale objects
    @FXML private TextField txtHouseSaleId;             // POSITION 0 in HOUSE_SALE_FIELDS
    @FXML private TextField txtHouseDate;               // POSITION 1 in HOUSE_SALE_FIELDS
    @FXML private TextField txtHouseSoldPrice;          // POSITION 2 in HOUSE_SALE_FIELDS
    @FXML private TextField txtHouseId;                 // POSITION 3 in HOUSE_SALE_FIELDS
    @FXML private TextField txtHouseBuyerId;            // POSITION 4 in HOUSE_SALE_FIELDS
    @FXML private TextField txtHouseSellerId;           // POSITION 5 in HOUSE_SALE_FIELDS

    StringBuilder errorMessage; // Holds any error messages

    // Handles the submit button for sale objects with Land
    @FXML private void handleSubmitLandSaleButton () {
        try {
            if(isValidInput(LAND_SALE_FIELDS)) {
                DataHandler.saleList.add(makeLandSale());
                Utils.Text.showConfirmation("Sale added successfully");
                Utils.Text.clearFields(LAND_SALE_FIELDS);
            } else {
                Utils.Text.showError(errorMessage.toString());
                clearError();
            }
        }catch (Exception e) {
            Utils.Text.showError("Error submitting sale\n" + e.getMessage());
        }
    }

    // Handles the submit button for sale objects with houseAndLand
    @FXML private void handleSubmitHouseAndLandSaleButton () {
        try {
            if(isValidInput(HOUSE_SALE_FIELDS)) {
                DataHandler.saleList.add(makeHouseSale());
                Utils.Text.showConfirmation("Sale added successfully");
                Utils.Text.clearFields(HOUSE_SALE_FIELDS);
            } else {
                Utils.Text.showError(errorMessage.toString());
                clearError();
            }
        } catch (Exception e) {
            Utils.Text.showError("Error submitting sale\n" + e.getMessage());
        }
    }

    // Checks if input fields are valid
    private boolean isValidInput(TextField[] fields) {
        boolean isValid = true;
        if (!Utils.Validator.isNotEmpty(fields)) {
            errorMessage.append("All fields must be filled.\n");
            return false;
        }

        // Assign values to check
        final String SALE_ID = fields[0].getText();
        final String DATE = fields[1].getText();
        final String SOLD_PRICE = fields[2].getText();
        final String PROPERTY_ID = fields[3].getText();
        final String BUYER_ID = fields[4].getText();
        final String SELLER_ID = fields[5].getText();

        if (!Utils.Validator.isInteger(SALE_ID)) {
            errorMessage.append("Sale ID must be an integer\n");
            isValid = false;
        } else {
            if (!Utils.Validator.idExists(SALE_ID, DataHandler.saleList)) {
                errorMessage.append("Sale ID already exists\n");
                isValid = false;
            }
        }
        if (!Utils.Validator.isDate(DATE)) {
            errorMessage.append("Date must be in the format dd/mm/yyyy\n");
            isValid = false;
        }
        if (!Utils.Validator.isDouble(SOLD_PRICE)) {
            errorMessage.append("Sold price must be a number\n");
            isValid = false;
        } else {
            if (Double.parseDouble(SOLD_PRICE) <= 0) {
                errorMessage.append("Sold price must be greater than 0\n");
                isValid = false;
            }
        }
        if (!Utils.Validator.isInteger(PROPERTY_ID)) {
            errorMessage.append("Property ID must be an integer\n");
            isValid = false;
        } else {
            if (Utils.Validator.idExists(PROPERTY_ID, DataHandler.landList)) {
                errorMessage.append("Property ID does not exist\n");
                isValid = false;
            }
        }
        if (!Utils.Validator.isInteger(BUYER_ID)) {
            errorMessage.append("Buyer ID must be an integer\n");
            isValid = false;
        } else {
            if (Utils.Validator.idExists(BUYER_ID, DataHandler.buyerList)) {
                errorMessage.append("Buyer ID does not exist\n");
                isValid = false;
            }
        }
        if (!Utils.Validator.isInteger(SELLER_ID)) {
            errorMessage.append("Seller ID must be an integer\n");
            isValid = false;
        } else {
            if (Utils.Validator.idExists(SELLER_ID, DataHandler.sellerList)) {
                errorMessage.append("Seller ID does not exist\n");
                isValid = false;
            }
        }

        return isValid;
    }


    // Make sale objects
    private Sale makeLandSale() {
        final int SALE_ID = Integer.parseInt(txtLandSaleId.getText());
        final String DATE = txtLandDate.getText();
        final double SOLD_PRICE = Double.parseDouble(txtLandSolePrice.getText());
        final Land LAND = (Land) DataHandler.getObject(Integer.parseInt(txtLandId.getText()), DataHandler.landList);
        final Seller SELLER = (Seller) DataHandler.getObject(Integer.parseInt(txtLandSellerId.getText()), DataHandler.sellerList);
        final Buyer BUYER = (Buyer) DataHandler.getObject(Integer.parseInt(txtLandBuyerId.getText()), DataHandler.buyerList);

        return new Sale (SALE_ID, DATE, SOLD_PRICE, LAND, SELLER, BUYER);
    }

    private Sale makeHouseSale() {
        final int SALE_ID = Integer.parseInt(txtHouseSaleId.getText());
        final String DATE = txtHouseDate.getText();
        final double SOLD_PRICE = Double.parseDouble(txtHouseSoldPrice.getText());
        final HouseAndLand HOUSE = (HouseAndLand) DataHandler.getObject(Integer.parseInt(txtHouseId.getText()), DataHandler.houseAndLandList);
        final Seller SELLER = (Seller) DataHandler.getObject(Integer.parseInt(txtHouseSellerId.getText()), DataHandler.sellerList);
        final Buyer BUYER = (Buyer) DataHandler.getObject(Integer.parseInt(txtHouseBuyerId.getText()), DataHandler.buyerList);

        return new Sale (SALE_ID, DATE, SOLD_PRICE, HOUSE, SELLER, BUYER);
    }


    // Clears error message
    private void clearError() {
        errorMessage.setLength(0);
    }

    // Switches to main menu
    @FXML private void switchToMainMenu() {
        try {
            App.setRoot("mainMenu");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        LAND_SALE_FIELDS = new TextField[]{txtLandSaleId, txtLandDate, txtLandSolePrice, txtLandId, txtLandBuyerId, txtLandSellerId};
        HOUSE_SALE_FIELDS = new TextField[]{txtHouseSaleId, txtHouseDate, txtHouseSoldPrice, txtHouseId, txtHouseBuyerId, txtHouseSellerId};
        errorMessage = new StringBuilder();
    }
}
