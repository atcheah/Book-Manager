package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    // EFFECTS: launches program
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    // EFFECTS: Displays program window
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        primaryStage.setTitle("My program");
        primaryStage.setScene(new Scene(root, 700, 450));
        primaryStage.show();
    }

}

