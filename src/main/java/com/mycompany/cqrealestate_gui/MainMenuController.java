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
import javafx.scene.control.Alert;

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
        // TODO
        return;
    }    

    public void clearFields() {
     // TODO
    }

    public void switchToAddProperty() {
        try {
            App.setRoot("addProperty");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void switchToAddClient() {
        try {
            App.setRoot("addClient");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void switchToAddSale() {
        try {
            App.setRoot("addSale");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void switchToDisplayRecords() {
        try {
            App.setRoot("displayRecords");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void switchToSearchSale() {
        try {
            App.setRoot("searchSale");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void switchToCalculateAverage() throws Exception {
        try {
            App.setRoot("calculateAverage");
        } catch (Exception e) {
            System.out.println(e.getMessage());
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

}
