package org.dimigo.gui.javaproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ExpresswayMain extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Expressway.fxml"));

        stage.setScene(new Scene(root));
        stage.setTitle("실시간 고속도로");

        stage.centerOnScreen();
        stage.show();
    }
}