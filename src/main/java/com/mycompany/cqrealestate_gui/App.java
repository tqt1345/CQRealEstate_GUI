/*
Programmer: Tarique Turnbull
StudentID: 12177936
Course: COIT11134 Object-Oriented Programming
Assessment: Practical Assessment 2

This class controls functionality for starting and stopping the program
 */

package com.mycompany.cqrealestate_gui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        try {
            // Load data
            DataHandler.loadData();
            DataHandler.saleInfo(); // Shows the latest sale info if any

            // Set GUI elements
            scene = new Scene(loadFXML("mainMenu"));
            stage.setScene(scene);
            stage.show();

            // Saves all data when program is closed
            stage.setOnCloseRequest(windowEvent -> {
                exit();
            });
        } catch (Exception e) {
            Utils.Text.showError("Error while starting program\n" + e.getMessage());
        }
    }


    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    // Exit program
    public static void exit() {
        DataHandler.saveData();
        Platform.exit();
    }
    public static void main(String[] args) {
        launch();
    }

}