package ui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.Book;

import java.util.LinkedList;

public class AlertBox {

    private static LinkedList<Book> searched = new LinkedList<>();

    // EFFECTS: creates an alert box with the right message based on purpose
    public static void display(String title, String message) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(300);

        Label label = new Label();
        if (title == "Searched Books") {
            for (Book b: searched) {
                message += "\n" + b.getTitle() + " by " + b.getAuthor();
            }
        }
        label.setText(message);
        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> window.close());

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, closeButton);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }

    // MODIFIES: searched
    // EFFECTS: adds users searches to list of searches
    public static void update(LinkedList<Book> recieved) {
        searched.addAll(recieved);
    }
}
