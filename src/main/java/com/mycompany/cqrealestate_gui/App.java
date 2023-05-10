package com.mycompany.cqrealestate_gui;

import javafx.application.Application;
import javafx.event.ActionEvent;
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
        // Load data
        DataHandler.loadData();

        // Set GUI elements
        scene = new Scene(loadFXML("mainMenu"), 640, 480);
        stage.setScene(scene);
        stage.show();

        // Saves all data when program is closed
        stage.setOnCloseRequest(windowEvent -> {
            DataHandler.saveData();
        });
    }


    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}