/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.cqrealestate_gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import testing.TestData;

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
        return;
    }

    @FXML private void switchToAddProperty() {
        try {
            App.setRoot("addProperty");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML private void switchToAddClient() {
        try {
            App.setRoot("addClient");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML private void switchToAddSale() {
        try {
            App.setRoot("addSale");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML private void switchToDisplayRecords() {
        try {
            App.setRoot("displayRecords");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML private void switchToSearchSale() {
        try {
            App.setRoot("searchSale");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

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

    @FXML private void handleClearDataButton(ActionEvent event) {
        try {
            DataHandler.clearData();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Successfully cleared data");
            alert.showAndWait();
        } catch(Exception e) {
            System.out.println(e);
            //TODO joptionpane
        }
    }

    @FXML private void handleMakeSampleDataButton() {
        try {
            TestData.makeSampleData();
            Utils.Text.showConfirmation("Successfully made sample data");
        } catch (Exception e) {
            Utils.Text.showError(e.getMessage());
        }
    }

    @FXML private void handleExitButton() {
        App.exit();
    }

}
