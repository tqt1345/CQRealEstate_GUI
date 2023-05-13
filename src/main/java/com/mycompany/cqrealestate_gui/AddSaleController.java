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
import java.util.List;
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
    @FXML private TextField txtLandSaleId;              // POS 1 in LAND_SALE_FIELDS
    @FXML private TextField txtLandDate;                // POS 2 in LAND_SALE_FIELDS
    @FXML private TextField txtLandSolePrice;           // POS 3 in LAND_SALE_FIELDS
    @FXML private TextField txtLandId;                  // POS 4 in LAND_SALE_FIELDS
    @FXML private TextField txtLandBuyerId;             // POS 5 in LAND_SALE_FIELDS
    @FXML private TextField txtLandSellerId;            // POS 6 in LAND_SALE_FIELDS

    // Text Fields for HouseAndLandSale Sale objects
    @FXML private TextField txtHouseSaleId;             // POS 1 in HOUSE_SALE_FIELDS
    @FXML private TextField txtHouseDate;               // POS 2 in HOUSE_SALE_FIELDS
    @FXML private TextField txtHouseSoldPrice;          // POS 3 in HOUSE_SALE_FIELDS
    @FXML private TextField txtHouseId;                 // POS 4 in HOUSE_SALE_FIELDS
    @FXML private TextField txtHouseBuyerId;            // POS 5 in HOUSE_SALE_FIELDS
    @FXML private TextField txtHouseSellerId;           // POS 6 in HOUSE_SALE_FIELDS

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

    private boolean isValidInput(TextField[] fields) {
        boolean isValid = true;
        if (!Utils.Validator.isNotEmpty(fields)) {
            errorMessage.append("All fields must be filled.\n");
            return false;
        }

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


    private void clearError() {
        errorMessage.setLength(0);
    }

    private void clearFields() {
        Utils.Text.clearFields(LAND_SALE_FIELDS);
        Utils.Text.clearFields(HOUSE_SALE_FIELDS);
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
/*
Redundant code
    // Gets the land object specified from input in textField
    private Land getLand(String Id) {
        for (Land land : DataHandler.landList) {
            if (land.getId() == Integer.parseInt(Id)) {
                return land;
            }
        }
        return null;
    }

    // Gets the HouseAndLand object specified from textField
    private HouseAndLand getHouseAndLand(String Id) {
        for (HouseAndLand houseAndLand : DataHandler.houseAndLandList) {
            if (houseAndLand.getId() == Integer.parseInt(Id)) {
                return houseAndLand;
            }
        }
        return null;
    }

    // Gets Seller object specified from textField
    private Seller getSeller(String Id) {

        for (Seller seller : DataHandler.sellerList) {
            if (seller.getId() == Integer.parseInt(Id)) {
                return seller;
            }
        }
        return null;
    }

    // Gets Buyer object specified from textField
    private Buyer getBuyer(String Id) {
        for (Buyer buyer : DataHandler.buyerList) {
            if (buyer.getId() == Integer.parseInt(Id)) {
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
                        if (land.getId() == Integer.parseInt(txtLandIdLand.getText())) {
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
                        if (buyer.getId() == Integer.parseInt(txtBuyerIdLand.getText())) {
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
                        if (seller.getId() == Integer.parseInt(txtSellerIdLand.getText())) {
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
                        if (houseAndLand.getId() == Integer.parseInt(txtHouseAndLandIdHouseAndLand.getText())) {
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
                        if (buyer.getId() == Integer.parseInt(txtBuyerIdHouseAndLand.getText())) {
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
                        if (!(Integer.parseInt(txtSellerIdHouseAndLand.getText()) == seller.getId())) {
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

    private boolean validLandSaleInput() {
        boolean isValid = true;
        if (!Utils.Validator.isNotEmpty(LAND_SALE_FIELDS)) {
            errorMessage.append("All fields must be filled.\n");
            return false;
        }

        final String SALE_ID = txtLandSaleId.getText();
        final String DATE = txtLandDate.getText();
        final String SOLD_PRICE = txtLandSolePrice.getText();
        final String LAND_ID = txtLandId.getText();
        final String BUYER_ID = txtLandBuyerId.getText();
        final String SELLER_ID = txtLandSellerId.getText();

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
        }
        if (!Utils.Validator.isInteger(LAND_ID)) {
            errorMessage.append("Land ID must be an integer\n");
            isValid = false;
        } else {
            if (Utils.Validator.idExists(LAND_ID, DataHandler.landList)) {
                errorMessage.append("Land ID does not exist\n");
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
        return true;
    }

    private boolean validHouseSaleInput() {
        boolean isValid = true;
        if (!Utils.Validator.isNotEmpty(HOUSE_SALE_FIELDS)) {
            errorMessage.append("All fields must be filled.\n");
            return false;
        }

        final String SALE_ID = txtHouseSaleId.getText();
        final String DATE = txtHouseDate.getText();
        final String SOLD_PRICE = txtHouseSoldPrice.getText();
        final String HOUSE_ID = txtHouseId.getText();
        final String BUYER_ID = txtHouseBuyerId.getText();
        final String SELLER_ID = txtHouseSellerId.getText();

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
        }
        if (!Utils.Validator.isInteger(HOUSE_ID)) {
            errorMessage.append("Land ID must be an integer\n");
            isValid = false;
        } else {
            if (Utils.Validator.idExists(HOUSE_ID, DataHandler.houseAndLandList)) {
                errorMessage.append("Land ID does not exist\n");
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

     */