package models;

import observer.Subject;

import java.util.LinkedList;
import java.util.List;


public class Library extends Subject {

    private List<Book> familyBooks;
    private List<Book> fictionBooks;
    private List<Book> nonfictionBooks;
    private List<Book> textBooks;
    private List<Book> cookBooks;

    public Library() {
        familyBooks = new LinkedList<>();
        fictionBooks = new LinkedList<>();
        nonfictionBooks = new LinkedList<>();
        textBooks = new LinkedList<>();
        cookBooks = new LinkedList<>();
    }

    // REQUIRES: book != null
    // MODIFIES: this
    // EFFECTS: stores the given Book (book) into the appropriate container within this class
    public void storeBook(Book book) {
        BookType genre = book.getType();
        switch (genre) {
            case FAMILY:
                this.familyBooks.add(book);
                break;
            case NONFICTION:
                this.nonfictionBooks.add(book);
                break;
            case FICTION:
                this.fictionBooks.add(book);
                break;
            case TEXTBOOK:
                this.textBooks.add(book);
                break;
            default:
                this.cookBooks.add(book);
                break;
        }
    }

    // REQUIRES: book != null
    // EFFECTS: return true if the given book is in the catalogue, regardless
    //          of its loan status, else return false
    public boolean contains(Book book) {
        BookType searchType = book.getType();
        switch (searchType) {
            case FAMILY:
                return familyBooks.contains(book);
            case NONFICTION:
                return nonfictionBooks.contains(book);
            case FICTION:
                return fictionBooks.contains(book);
            case TEXTBOOK:
                return textBooks.contains(book);
            default:
                return cookBooks.contains(book);
        }
    }

    // REQUIRES: book != null
    // EFFECTS: return true if the given book is available to loan.
    public boolean canLoan(Book book) {
        addObserver(book);
        notifyObservers(book);
        return !book.onLoan() && contains(book);
    }


    // REQUIRES: book != null
    // MODIFIES: this
    // EFFECTS: set book as being checked out from this library if it is currently not borrowed. Return true if
    //          this is successful, else, return false
    public boolean checkOutBook(Book book) {
        if (this.canLoan(book)) {
            book.setOnLoan();
            return true;
        } else {
            return false;
        }
    }

    // REQUIRES: book != null
    // MODIFIES: this
    // EFFECTS: set book as being back in the library if it has been borrowed previously,
    //          return true if successful. Otherwise, the method should return false
    public boolean returnBook(Book book) {
        if (book.onLoan()) {
            book.notOnLoan();
            return true;
        } else {
            return false;
        }
    }


    // EFFECTS: Creates string for displaying contents
    public String displayContents() {
        String string = "";
        List<Book> totalCatalogue = Library.combineBranches(this);
        for (Book b : totalCatalogue) {
            string = string + b.getTitle() + " by " + b.getAuthor() + "\n";
        }
        return string;
    }

    public List<Book> returnFiction() {
        return this.fictionBooks;
    }

    public List<Book> returnCook() {
        return this.cookBooks;
    }

    public List<Book> returnNonFiction() {
        return this.nonfictionBooks;
    }

    public List<Book> returnText() {
        return this.textBooks;
    }

    public List<Book> returnFamily() {
        return this.familyBooks;
    }

    // REQUIRES: library != null
    // EFFECTS: Combines branches of library to singular list of books
    public static List combineBranches(Library library) {
        List<Book> totalCatalogue = new LinkedList<>();
        totalCatalogue.addAll(library.returnCook());
        totalCatalogue.addAll(library.returnFiction());
        totalCatalogue.addAll(library.returnNonFiction());
        totalCatalogue.addAll(library.returnText());
        totalCatalogue.addAll(library.returnFamily());
        return totalCatalogue;
    }

}



