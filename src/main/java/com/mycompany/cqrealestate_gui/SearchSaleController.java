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
        errorMessage = new StringBuilder();
        fields = new TextField[]{txtSaleId};
    }

    TextField[] fields;

    @FXML TextArea txtSaleRecords; // Text area for showing sale info
    @FXML TextField txtSaleId;     // text field for entering sale id


    StringBuilder errorMessage;

    // Handles button for searching sale
    @FXML private void handleSearchButton() {
        try {
            if (isValidInput()) {
                showSaleRecord(txtSaleId.getText());
            } else {
                Utils.Text.showError(errorMessage.toString());
                errorMessage.setLength(0);
            }
        }catch (Exception e) {
            Utils.Text.showError("Error while searching sale\n" + e.getMessage());
        }
    }

    // Checks for valid input
    private boolean isValidInput() {
        boolean isValid = true;
        if (txtSaleId.getText().isEmpty()) {
            errorMessage.append("Sale ID field cannot be empty\n");
            isValid = false;
        }
        final String ID = txtSaleId.getText();

        if (!Utils.Validator.isInteger(ID)) {
            errorMessage.append("Sale ID must be an integer\n");
            isValid = false;
        } else {
            if (Utils.Validator.idExists(ID, DataHandler.saleList)) {
                errorMessage.append("Sale ID does not exist\n");
                isValid = false;
            }
        }
        return isValid;
    }

    private void showSaleRecord(String id) {
        final String SALE_INFO = DataHandler.getObject(Integer.parseInt(id), DataHandler.saleList).toString();
        txtSaleRecords.setText(SALE_INFO);
    }
    /*
    private boolean isValidInputOld () {
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
                    if (sale.getId() == Integer.parseInt(txtSaleId.getText())) {
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
     */


    // Switches to main menu
    public void switchToMainMenu() throws Exception {
        try {
            App.setRoot("mainMenu");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
