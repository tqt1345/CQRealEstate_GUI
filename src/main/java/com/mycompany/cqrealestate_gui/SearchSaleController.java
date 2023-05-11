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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author tqt13
 */
public class SearchSaleController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML TextArea txtSaleRecords;
    @FXML TextField txtSaleId;

    @FXML private void handleSearchButton() {
        try {
            if (isValidInput()) {
                searchSale(txtSaleId.getText());
            }
        } catch (Exception e) {
            Utils.Text.showError("Error while searching\n" + e.getMessage());
        }
    }
    private void searchSale(String saleID) {
        txtSaleRecords.clear();

        for (Sale sale : DataHandler.saleList) {
            if (sale.getSaleID() == Integer.parseInt(saleID)) {
                txtSaleRecords.setText(sale.toString());
            }
        }
    }
    private boolean isValidInput () {
        try {
            StringBuilder errorMessage = new StringBuilder();
            if (txtSaleId.getText().isEmpty()) {
                errorMessage.append("Sale ID cannot be empty\n");
            }
            if (!Utils.Validator.isInteger(txtSaleId.getText())) {
                errorMessage.append("Sale ID must be an integer\n");
            } else {
                boolean saleExist = false;
                for (Sale sale : DataHandler.saleList) {
                    if (sale.getSaleID() == Integer.parseInt(txtSaleId.getText())) {
                        saleExist = true;
                    }
                }
                if (!saleExist) {
                    errorMessage.append("Sale ID does not exist\n");
                }
            }

            if (!errorMessage.toString().isEmpty()) {
                Utils.Text.showError(errorMessage.toString());
                return false;
            }
        }catch (Exception e) {
            Utils.Text.showError("Error while validating input\n" + e.getMessage());
        }
        return true;
    }
    public void switchToMainMenu() throws Exception {
        try {
            App.setRoot("mainMenu");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
