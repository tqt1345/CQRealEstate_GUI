/*
Programmer: Tarique Turnbull
StudentID: 12177936
Course: COIT11134 Object-Oriented Programming
Assessment: Practical Assessment 2

This class controls functionality for displaying records
 */
package com.mycompany.cqrealestate_gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;

/**
 * FXML Controller class
 *
 * @author tqt13
 */
public class DisplayRecordsController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    // Text areas for displaying toString info of objects
    @FXML private TextArea landRecords;
    @FXML private TextArea houseAndLandRecords;
    @FXML private TextArea buyerRecords;
    @FXML private TextArea sellerRecords;


    // Handles button for switching to main menu
    @FXML private void switchToMainMenu() throws Exception {
        try {
            App.setRoot("mainMenu");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // Handles button for displaying land records
    @FXML private void handleDisplayLandRecordsButton() {
        manageRecords(DataHandler.landList, landRecords);
    }

    // Handles button for displaying houseAndLand records
    @FXML private void handleDisplayHouseAndLandRecordsButton() {
        manageRecords(DataHandler.houseAndLandList, houseAndLandRecords);
    }

    // Handles button for displaying buyer records
    @FXML private void handleDisplayBuyerRecordsButton() {
        manageRecords(DataHandler.buyerList, buyerRecords);
    }

    // Handles button for displaying seller records
    @FXML private void handleDisplaySellerRecordsButton() {
        manageRecords(DataHandler.sellerList, sellerRecords);
    }

    // Appends toString of a specific object list to records TextArea
    private void manageRecords(ArrayList<?> list, TextArea records) {
        records.clear();
        try {
            if (list.isEmpty()) {
                records.setText("No records found!");
            } else {
                for (Object object : list) {
                    records.appendText(object.toString());
                    records.appendText("\n" + Utils.Text.separator(30) +
                            "\n");
                }
            }
        } catch (Exception e) {
                Utils.Text.showError("Error displaying records\n" + e.getMessage());
        }
    }
}
