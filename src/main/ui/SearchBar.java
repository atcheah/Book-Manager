package ui;

import io.LoadAndSave;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.Book;
import models.BookType;
import models.Library;

import java.util.LinkedList;

public class SearchBar {

    private static LoadAndSave loadLibrary = new LoadAndSave();
    private LinkedList<Book> searched = new LinkedList<>();

    // MODIFIES: searched
    // EFFECTS: creates and displays search bar
    public SearchBar() {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Search");
        window.setMinWidth(300);
        TextField author = new TextField("Author");
        TextField title = new TextField("Title");
        TextField year = new TextField("Year");
        TextField edition = new TextField("Edition");
        ComboBox<BookType> type = new ComboBox<>();
        type.getItems().addAll(BookType.FICTION,
                BookType.TEXTBOOK,BookType.COOKING,BookType.NONFICTION, BookType.FAMILY);
        Button searchButton = new Button("Search");
        searchButton.setOnAction(e -> check(author,title,year,edition,type.getValue()));
        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> window.close());

        VBox layout = new VBox(10);
        layout.getChildren().addAll(title, author, year, edition, type, searchButton, closeButton);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
        AlertBox.update(searched);
    }

    // REQUIRES: loadLibrary != null
    // MODIFIES: searched
    // EFFECTS: Takes UI and sees if book is in library
    private void check(TextField author, TextField title, TextField year, TextField edition, BookType value) {
        Library lib = new Library();
        loadLibrary.load(lib, "data/inputfile");
        String a = author.getText();
        String t = title.getText();
        int y = Integer.parseInt(year.getText());
        int ed = Integer.parseInt(edition.getText());
        String message = "";
        searched.add(new Book(t,a,value,y,ed));
        if (lib.canLoan(new Book(t,a,value,y,ed))) {
            message = "Book contained in library";
        } else {
            message = "Not in library";
        }
        AlertBox.display("Results", message);
    }

}
