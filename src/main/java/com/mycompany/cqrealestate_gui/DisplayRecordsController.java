/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
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

    @FXML private TextArea landRecords;
    @FXML private TextArea houseAndLandRecords;
    @FXML private TextArea buyerRecords;
    @FXML private TextArea sellerRecords;
    @FXML private TextArea saleRecords;


    @FXML
    private void switchToMainMenu() throws Exception {
        try {
            App.setRoot("mainMenu");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void handleDisplayLandRecordsButton() {
        manageRecords(DataHandler.landList, landRecords);
    }
    @FXML
    private void handleDisplayHouseAndLandRecordsButton() {
        manageRecords(DataHandler.houseAndLandList, houseAndLandRecords);
    }

    @FXML
    private void handleDisplayBuyerRecordsButton() {
        manageRecords(DataHandler.buyerList, buyerRecords);
    }

    @FXML
    private void handleDisplaySellerRecordsButton() {
        manageRecords(DataHandler.sellerList, sellerRecords);
    }

    @FXML
    private void handleDisplaySaleRecordsButton() {
        manageRecords(DataHandler.saleList, saleRecords);
    }

    // Generic method appends toString of a specific object list to records TextArea
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
            System.out.println(e.getMessage());
        }
    }
}
