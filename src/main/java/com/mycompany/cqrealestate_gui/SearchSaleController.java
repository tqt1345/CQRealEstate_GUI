/*
Programmer: Tarique Turnbull
StudentID: 12177936
Course: COIT11134 Object-Oriented Programming
Assessment: Practical Assessment 2

This class controls functionality for searching through sale records
 */
package com.mycompany.cqrealestate_gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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

    @FXML TextArea txtSaleRecords; // Text area for showing sale info
    @FXML TextField txtSaleId;     // text field for entering sale id

    // Handles button for searching sale
    @FXML private void handleSearchButton() {
        try {
            if (isValidInput()) {
                searchSale(txtSaleId.getText());
            }
        } catch (Exception e) {
            Utils.Text.showError("Error while searching\n" + e.getMessage());
        }
    }

    // Searches for sale based on id
    private void searchSale(String saleID) {
        txtSaleRecords.clear();

        for (Sale sale : DataHandler.saleList) {
            if (sale.getSaleID() == Integer.parseInt(saleID)) {
                txtSaleRecords.setText(sale.toString());
            }
        }
    }

    // Validates input
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

    // Switches to main menu
    public void switchToMainMenu() throws Exception {
        try {
            App.setRoot("mainMenu");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
