/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.cqrealestate_gui;

import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
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

    private Land getLand(String Id) {
        for (Land land : DataHandler.landList) {
            if (land.getPropertyId() == Integer.parseInt(Id)) {
                return land;
            }
        }
        return null;
    }

    private HouseAndLand getHouseAndLand(String Id) {
        for (HouseAndLand houseAndLand : DataHandler.houseAndLandList) {
            if (houseAndLand.getPropertyId() == Integer.parseInt(Id)) {
                return houseAndLand;
            }
        }
        return null;
    }

    private Seller getSeller(String Id) {

        for (Seller seller : DataHandler.sellerList) {
            if (seller.getClientID() == Integer.parseInt(Id)) {
                return seller;
            }
        }
        return null;
    }

    private Buyer getBuyer(String Id) {
        for (Buyer buyer : DataHandler.buyerList) {
            if (buyer.getClientID() == Integer.parseInt(Id)) {
                return buyer;
            }
        }
        return null;
    }


    private boolean isValidInput(String type) {
        StringBuilder errorMessage = new StringBuilder();
        switch (type) {
            case "Land":
                if (    txtSaleIdLand.getText().isEmpty() ||
                        txtDateLand.getText().isEmpty() ||
                        txtSoldPriceLand.getText().isEmpty() ||
                        txtLandIdLand.getText().isEmpty() ||
                        txtBuyerIdLand.getText().isEmpty() ||
                        txtSellerIdLand.getText().isEmpty()) {
                        errorMessage.append("All fields must be filled.\n");
                }
                if (!Utils.Validator.isInteger(txtSaleIdLand.getText())) {
                    errorMessage.append("Sale ID must be an integer\n");
                } else {
                    for (Sale sale : DataHandler.saleList) {
                        if (sale.getSaleID() == Integer.parseInt(txtSaleIdLand.getText())) {
                            errorMessage.append("Sale ID already exists\n");
                        }
                    }
                }
                if (!Utils.Validator.isDate(txtDateLand.getText())) {
                    errorMessage.append("Invalid date, must be in the format: (dd/mm/yyyy)\n");
                }
                if (!Utils.Validator.isDouble(txtSoldPriceLand.getText())) {
                    errorMessage.append("Sold price must be a double\n");
                }
                if (!Utils.Validator.isInteger(txtLandIdLand.getText())) {
                    errorMessage.append("Land ID must be an integer\n");
                } else {
                    boolean landExists = false;
                    for (Land land : DataHandler.landList) {
                        if (land.getPropertyId() == Integer.parseInt(txtLandIdLand.getText())) {
                            landExists = true;
                        }
                    }
                    if (!landExists) {
                        errorMessage.append("No Land entry with ID: ");
                        errorMessage.append(txtLandIdLand.getText());
                        errorMessage.append("\n");
                    }
                }

                if (!Utils.Validator.isInteger(txtBuyerIdLand.getText())) {
                    errorMessage.append("Buyer ID must be an integer\n");
                } else {
                    boolean buyerExists = false;
                    for (Buyer buyer : DataHandler.buyerList) {
                        if (buyer.getClientID() == Integer.parseInt(txtBuyerIdLand.getText())) {
                            buyerExists = true;
                        }
                    }
                    if (!buyerExists) {
                        errorMessage.append("No Buyer entry with ID: ");
                        errorMessage.append(txtBuyerIdLand.getText());
                        errorMessage.append("\n");
                    }
                }

                if (!Utils.Validator.isInteger(txtSellerIdLand.getText())) {
                    errorMessage.append("Seller ID must be an integer\n");
                } else {
                    boolean sellerExists = false;
                    for (Seller seller : DataHandler.sellerList) {
                        if (seller.getClientID() == Integer.parseInt(txtSellerIdLand.getText())) {
                            sellerExists = true;
                        }
                    }
                    if (!sellerExists) {
                        errorMessage.append("No Seller entry with ID: ");
                        errorMessage.append(txtSellerIdLand.getText());
                        errorMessage.append("\n");
                    }
                }

                if (errorMessage.length() > 0) {
                    Utils.Text.showError(errorMessage.toString());
                    return false;
                }
                break;

            case "HouseAndLand":
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

    private void clearFields() {

        txtSaleIdLand.clear();
        txtDateLand.clear();
        txtSoldPriceLand.clear();
        txtLandIdLand.clear();
        txtBuyerIdLand.clear();
        txtSellerIdLand.clear();

        txtSaleIdHouseAndLand.clear();
        txtDateHouseAndLand.clear();
        txtSoldPriceHouseAndLand.clear();
        txtHouseAndLandIdHouseAndLand.clear();
        txtBuyerIdHouseAndLand.clear();
        txtSellerIdHouseAndLand.clear();
    }

    @FXML private void switchToMainMenu() {
        try {
            App.setRoot("mainMenu");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
