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
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.showAndWait();
                clearFields();
            }
        }catch (Exception e) {
            // TODO JOptionPane
            System.out.println(e);
        }
    }

    @FXML private void handleSubmitHouseAndLandSaleButton () {
        // TODO
    }

    private Sale makeSale(String type){
        // TODO
        switch (type) {
            case "Land":
                int saleId = Integer.parseInt(txtSaleIdLand.getText());
                String date = txtDateLand.getText();
                double soldPrice = Double.parseDouble(txtSoldPriceLand.getText());
                Land land = getLand(txtLandIdLand.getText());
                Seller seller = getSeller(txtSellerIdLand.getText());
                Buyer buyer = getBuyer(txtBuyerIdLand.getText());

                return new Sale(saleId, date, soldPrice, land, seller, buyer);
            case "HouseAndLand":
                // TODO
                break;
        }
        return null;
    }

    private Land getLand(String Id) {
        // TODO
        return null;
    }

    private HouseAndLand getHouseAndLand(String Id) {
        // TODO
        return null;
    }

    private Seller getSeller(String Id) {
        // TODO
        return null;
    }

    private Buyer getBuyer(String Id) {
        // TODO
        return null;
    }

    private boolean isValidInput(String type) {
        StringBuilder errorMessage = new StringBuilder();
        switch (type) {
            case "Land":
                if (txtSaleIdLand.getText().isEmpty() ||
                        txtDateLand.getText().isEmpty() ||
                        txtSoldPriceLand.getText().isEmpty() ||
                        txtLandIdLand.getText().isEmpty() ||
                        txtBuyerIdLand.getText().isEmpty() ||
                        txtSellerIdLand.getText().isEmpty()) {
                    errorMessage.append("All fields must be filled.\n");
                }
                for (Sale sale : DataHandler.saleList) {
                    if (sale.getSaleID() == Integer.parseInt(txtSaleIdLand.getText())) {
                        errorMessage.append("Sale ID already exists\n");
                    }
                }
                if (!Utils.Validator.isDate(txtDateLand.getText())) {
                    errorMessage.append("Invalid date, must be in the format: (dd/mm/yyyy)\n");
                }
                if (Utils.Validator.isDouble(txtSoldPriceLand.getText())) {
                    errorMessage.append("Sold price must be a double\n");
                }
                if (Utils.Validator.isInteger(txtLandIdLand.getText())) {
                    errorMessage.append("Land ID must be an integer\n");
                }
                for (Land land : DataHandler.landList) {
                    if (!(Integer.parseInt(txtLandIdLand.getText()) == land.getPropertyId())) {
                        errorMessage.append("No Land entry with ID: ");
                        errorMessage.append(txtLandIdLand.getText());
                        errorMessage.append("\n");
                    }
                }
                if (Utils.Validator.isInteger(txtBuyerIdLand.getText())) {
                    errorMessage.append("Buyer ID must be an integer\n");
                }
                for (Buyer buyer : DataHandler.buyerList) {
                    if (!(Integer.parseInt(txtBuyerIdLand.getText()) == buyer.getClientID())) {
                        errorMessage.append("No Buyer entry with ID: ");
                        errorMessage.append(txtBuyerIdLand.getText());
                        errorMessage.append("\n");
                    }
                }
                if (Utils.Validator.isInteger(txtSellerIdLand.getText())) {
                    errorMessage.append("Seller ID must be an integer\n");
                }
                for (Seller seller : DataHandler.sellerList) {
                    if (!(Integer.parseInt(txtSellerIdLand.getText()) == seller.getClientID())) {
                        errorMessage.append("No Seller entry with ID: ");
                        errorMessage.append(txtSellerIdLand.getText());
                        errorMessage.append("\n");
                    }
                }
                if (errorMessage.length() > 0) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, errorMessage.toString());
                    alert.showAndWait();
                    return false;
                }
                break;

            case "HouseAndLand":
                if (txtSaleIdHouseAndLand.getText().isEmpty() ||
                        txtDateHouseAndLand.getText().isEmpty() ||
                        txtSoldPriceHouseAndLand.getText().isEmpty() ||
                        txtHouseAndLandIdHouseAndLand.getText().isEmpty() ||
                        txtBuyerIdHouseAndLand.getText().isEmpty() ||
                        txtSellerIdHouseAndLand.getText().isEmpty()) {
                    errorMessage.append("All fields must be filled.\n");
                }
                for (Sale sale : DataHandler.saleList) {
                    if (sale.getSaleID() == Integer.parseInt(txtSaleIdHouseAndLand.getText())) {
                        errorMessage.append("Sale ID already exists\n");
                    }
                }
                if (!Utils.Validator.isDate(txtDateHouseAndLand.getText())) {
                    errorMessage.append("Invalid date, must be in the format: (dd/mm/yyyy)\n");
                }
                if (Utils.Validator.isDouble(txtSoldPriceHouseAndLand.getText())) {
                    errorMessage.append("Sold price must be a double\n");
                }
                if (Utils.Validator.isInteger(txtHouseAndLandIdHouseAndLand.getText())) {
                    errorMessage.append("House and Land ID must be an integer\n");
                }
                for (HouseAndLand houseAndLand : DataHandler.houseAndLandList) {
                    if (!(Integer.parseInt(txtHouseAndLandIdHouseAndLand.getText()) == houseAndLand.getPropertyId())) {
                        errorMessage.append("No House and Land entry with ID: ");
                        errorMessage.append(txtHouseAndLandIdHouseAndLand.getText());
                        errorMessage.append("\n");
                    }
                }
                if (Utils.Validator.isInteger(txtBuyerIdHouseAndLand.getText())) {
                    errorMessage.append("Buyer ID must be an integer\n");
                }
                for (Buyer buyer : DataHandler.buyerList) {
                    if (!(Integer.parseInt(txtBuyerIdHouseAndLand.getText()) == buyer.getClientID())) {
                        errorMessage.append("No Buyer entry with ID: ");
                        errorMessage.append(txtBuyerIdHouseAndLand.getText());
                        errorMessage.append("\n");
                    }
                }
                if (Utils.Validator.isInteger(txtSellerIdHouseAndLand.getText())) {
                    errorMessage.append("Seller ID must be an integer\n");
                }
                if (errorMessage.length() > 0) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, errorMessage.toString());
                    alert.showAndWait();
                    return false;
                }
                break;
        }
        return false;
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
