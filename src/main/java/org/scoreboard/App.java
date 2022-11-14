package org.scoreboard;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Scoreboard.fxml"));
        primaryStage.setTitle("Team Editor");
        primaryStage.setScene(new Scene(root, 400, 230));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}