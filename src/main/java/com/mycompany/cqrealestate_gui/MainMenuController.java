/*
Programmer: Tarique Turnbull
StudentID: 12177936
Course: COIT11134 Object-Oriented Programming
Assessment: Practical Assessment 2

This class controls functionality for the main menu
 */
package com.mycompany.cqrealestate_gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author tqt13
 */
public class MainMenuController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    // Switch to addProperty
    @FXML private void switchToAddProperty() {
        try {
            App.setRoot("addProperty");
        } catch (Exception e) {
            Utils.Text.showError("Error while switching to add property\n" + e.getMessage());
        }
    }

    // Switch to addClient
    @FXML private void switchToAddClient() {
        try {
            App.setRoot("addClient");
        } catch (Exception e) {
            Utils.Text.showError("Error while switching to add client\n" + e.getMessage());
        }
    }

    // Switch to addSale
    @FXML private void switchToAddSale() {
        try {
            App.setRoot("addSale");
        } catch (Exception e) {
            Utils.Text.showError("Error while switching to add sale\n" + e.getMessage());
        }
    }

    // Switch to displayRecords
    @FXML private void switchToDisplayRecords() {
        try {
            App.setRoot("displayRecords");
        } catch (Exception e) {
            Utils.Text.showError("Error while switching to display records\n" + e.getMessage());
        }
    }

    // Switch to searchSale
    @FXML private void switchToSearchSale() {
        try {
            App.setRoot("searchSale");
        } catch (Exception e) {
            Utils.Text.showError("Error while switching to search sale\n" + e.getMessage());
        }
    }

    // Calculates average price of all sales
    @FXML private void handleCalculateAverageButton() {
        try {
            if (DataHandler.saleList.isEmpty()) {
                Utils.Text.showError("Can't calculate average, no sale records");
            } else {

                double sum = 0;
                double average = 0;
                int count = DataHandler.saleList.size();

                for (Sale sale : DataHandler.saleList) {
                    sum += sale.getSoldPrice();
                }

                average = sum / count;
                Utils.Text.showConfirmation("Average price of all sales is: " + average);
            }
        }catch (Exception e) {
            Utils.Text.showError("Error calculating average\n" + e.getMessage());
        }
    }

    // Clears data
    @FXML private void handleClearDataButton(ActionEvent event) {
        try {
            DataHandler.clearData();
            Utils.Text.showConfirmation("Successfully cleared data");
        } catch(Exception e) {
            Utils.Text.showError("Error clearing data\n" + e.getMessage());
        }
    }

    /*
    FOR TESTING
    @FXML private void handleMakeSampleDataButton() {
        try {
            TestData.makeSampleData();
            Utils.Text.showConfirmation("Successfully made sample data");
        } catch (Exception e) {
            Utils.Text.showError(e.getMessage());
        }
    }

     */
    // Exits program
    @FXML private void handleExitButton() {
        App.exit();
    }

}
