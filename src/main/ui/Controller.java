package ui;

import io.LoadAndSave;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import models.Book;
import models.BookType;
import models.Library;
import network.ReadWebPageEx;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    private static LoadAndSave loadLibrary = new LoadAndSave();
    private static LoadAndSave saveLibrary = new LoadAndSave();

    @FXML private MenuBar menu;
    @FXML private Menu viewLibrary;
    @FXML private MenuItem viewBooks;
    @FXML private MenuItem search;
    @FXML private Menu librarianFeatures;
    @FXML private MenuItem showLibraries;
    @FXML private MenuItem searchTrack;

    @FXML private TableView<Book> tableView;
    @FXML private TableColumn<Book, String> titleColumn;
    @FXML private TableColumn<Book, String> authorColumn;
    @FXML private TableColumn<Book, Integer> yearColumn;
    @FXML private TableColumn<Book, Integer> editionColumn;

    @FXML private AnchorPane bookDetails;
    @FXML private Label label1;
    @FXML private CheckBox rent;

    @FXML private HBox addBook;
    @FXML private Label label2;
    @FXML private Button adder;
    @FXML private TextField authorField;
    @FXML private TextField titleField;
    @FXML private TextField yearField;
    @FXML private TextField editionField;
    @FXML private ComboBox<BookType> genre;
    @FXML private Text details;

    @Override
    /// REQUIRES: genre != null, titleColumn != null, authorColumn != null, yearColumn != null, editionColumn != null,
    // tableView != null
    // EFFECTS: initializes table as well as combo box button for add purposes
    public void initialize(URL location, ResourceBundle resources) {
        genre.getItems().addAll(BookType.FICTION,
                BookType.TEXTBOOK,BookType.COOKING,BookType.NONFICTION, BookType.FAMILY);
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
        yearColumn.setCellValueFactory(new PropertyValueFactory<>("year"));
        editionColumn.setCellValueFactory(new PropertyValueFactory<>("edition"));
        tableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    // REQUIRES: tableView != null
    // EFFECTS: Displays all of books in library
    public void showLibrary(MouseEvent mouseEvent) {
        tableView.getItems().setAll(getBooks());
    }


    // REQUIRES: loadLibrary != null
    // EFFECTS: Gets books from file and converts to displayable format
    public ObservableList<Book> getBooks() {
        ObservableList<Book> books = FXCollections.observableArrayList();
        Library lib = new Library();
        loadLibrary.load(lib, "data/inputfile");
        for (Object b : Library.combineBranches(lib)) {
            Book book = (Book) b;
            books.add(new Book(book.getTitle(),book.getAuthor(),book.getType(),
                    book.getYear(),book.getEdition()));
        }
        return books;
    }

    // REQUIRES: tableView != null, saveLibrary != null
    // MODIFIES: file
    // EFFECTS: Saves library from UI, to file
    public void saveHelper() {
        ObservableList<Book> books = tableView.getItems();
        Library library = new Library();
        for (Book b: books) {
            library.storeBook(b);
        }
        try {
            saveLibrary.save(Library.combineBranches(library), "data/inputfile");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    // EFFECTS: Creates search bar for UI
    public void searchBook(ActionEvent actionEvent) {
        new SearchBar();
    }

    // EFFECTS: Displays Local Libraries from API
    public void findLibraries(ActionEvent actionEvent) {
        ReadWebPageEx pageEx = new ReadWebPageEx();
        String message = pageEx.returnOutput();
        AlertBox.display("Local Libraries", message);
    }

    // EFFECTS: Displays searched list
    public void getSearched(ActionEvent actionEvent) {
        AlertBox.display("Searched Books", "User searched for:");
    }

    // REQUIRES: authorField != null, titleField != null, yearField != null, editionField != null, genre != null,
    // tableView != null
    // MODIFIES: file
    // EFFECTS: adds a book to the table and saves it so that it will be there permanently
    public void addBook(ActionEvent actionEvent) {
        String author = authorField.getText();
        String title = titleField.getText();
        int year = Integer.parseInt(yearField.getText());
        int ed = Integer.parseInt(editionField.getText());
        BookType type = genre.getValue();
        authorField.clear();
        titleField.clear();
        yearField.clear();
        editionField.clear();
        tableView.getItems().add(new Book(title,author,type,year,ed));
        saveHelper();
    }

    // REQUIRES: tableView != null, details != null, rent != null
    // MODIFIES: rent
    // EFFECTS: displays Books info to user as well as rent check
    public void showDetails(MouseEvent mouseEvent) {
        if (!tableView.getSelectionModel().getSelectedItems().isEmpty()) {
            Book book = tableView.getSelectionModel().getSelectedItem();
            details.setText("Title: " + book.getTitle() + "\n"
                    + "Author: " + book.getAuthor() + "\n"
                    + "Page Count: " + book.getYear() + "\n"
                    + "Edition: " + book.getEdition() + "\n"
                    + "Genre: " + book.getType() + "\n");
            rent.setSelected(book.onLoan());
        }
    }

    // REQUIRES: tableView != null
    // EFFECTS: Rents book temporarily
    public void rentBook(ActionEvent actionEvent) {
        if (!tableView.getSelectionModel().getSelectedItems().isEmpty()) {
            if (tableView.getSelectionModel().getSelectedItem().onLoan()) {
                tableView.getSelectionModel().getSelectedItem().setOnLoan();
            } else {
                tableView.getSelectionModel().getSelectedItem().notOnLoan();
            }
        }
    }

    // REQUIRES: tableView != null, loadLibrary != null
    // EFFECTS: Displays fiction books
    public void showFiction(MouseEvent mouseEvent) {
        ObservableList<Book> books = FXCollections.observableArrayList();
        Library lib = new Library();
        loadLibrary.load(lib, "data/inputfile");
        for (Object b : lib.returnFiction()) {
            Book book = (Book) b;
            books.add(new Book(book.getTitle(),book.getAuthor(),book.getType(),
                    book.getYear(),book.getEdition()));
        }
        tableView.getItems().setAll(books);
    }

    // REQUIRES: tableView != null, loadLibrary != null
    // EFFECTS: Displays nonfiction books
    public void showNonFiction(MouseEvent mouseEvent) {
        ObservableList<Book> books = FXCollections.observableArrayList();
        Library lib = new Library();
        loadLibrary.load(lib, "data/inputfile");
        for (Object b : lib.returnNonFiction()) {
            Book book = (Book) b;
            books.add(new Book(book.getTitle(),book.getAuthor(),book.getType(),
                    book.getYear(),book.getEdition()));
        }
        tableView.getItems().setAll(books);
    }

    // REQUIRES: tableView != null, loadLibrary != null
    // EFFECTS: Displays textbooks books
    public void showTextbooks(MouseEvent mouseEvent) {
        ObservableList<Book> books = FXCollections.observableArrayList();
        Library lib = new Library();
        loadLibrary.load(lib, "data/inputfile");
        for (Object b : lib.returnText()) {
            Book book = (Book) b;
            books.add(new Book(book.getTitle(),book.getAuthor(),book.getType(),
                    book.getYear(),book.getEdition()));
        }
        tableView.getItems().setAll(books);
    }

    // REQUIRES: tableView != null, loadLibrary != null
    // EFFECTS: Displays family books
    public void showFamily(MouseEvent mouseEvent) {
        ObservableList<Book> books = FXCollections.observableArrayList();
        Library lib = new Library();
        loadLibrary.load(lib, "data/inputfile");
        for (Object b : lib.returnFamily()) {
            Book book = (Book) b;
            books.add(new Book(book.getTitle(),book.getAuthor(),book.getType(),
                    book.getYear(),book.getEdition()));
        }
        tableView.getItems().setAll(books);
    }

    // REQUIRES: tableView != null, loadLibrary != null
    // EFFECTS: Displays cooking books
    public void showCooking(MouseEvent mouseEvent) {
        ObservableList<Book> books = FXCollections.observableArrayList();
        Library lib = new Library();
        loadLibrary.load(lib, "data/inputfile");
        for (Object b : lib.returnCook()) {
            Book book = (Book) b;
            books.add(new Book(book.getTitle(),book.getAuthor(),book.getType(),
                    book.getYear(),book.getEdition()));
        }
        tableView.getItems().setAll(books);
    }
}

