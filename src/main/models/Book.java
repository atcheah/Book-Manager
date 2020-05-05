package models;

import observer.LibrarianObserver;

public class Book implements LibrarianObserver {

    private String title;
    private String author;
    private BookType type;
    private int year;
    private int edition;
    private boolean isOnLoan;

    public Book(String title, String author, BookType type, int year, int ed) {
        this.title = title;
        this.author = author;
        this.type = type;
        this.year = year;
        edition = ed;
        isOnLoan = false;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public BookType getType() {
        return type;
    }

    public int getYear() {
        return year;
    }

    public int getEdition() {
        return edition;
    }

    public boolean onLoan() {
        return isOnLoan;
    }

    // MODIFIES: this
    // EFFECTS: sets a field in this book such that it is on loan
    public void setOnLoan() {
        isOnLoan = true;
    }

    // MODIFIES: this
    // EFFECTS: set a field in this book such that it is no longer on loan
    public void notOnLoan() {
        isOnLoan = false;
    }

    @Override
    public void update(Book book) {
        System.out.println("Somebody looked for " + book.getTitle());
    }

    @Override
    // EFFECTS: creates personal hash code for object
    public int hashCode() {
        String title = this.title + author + year + edition;
        return title.hashCode();
    }

    @Override
    // REQUIRES: obj != null
    // EFFECTS: determines whether an object, is equal to this
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (getClass() != obj.getClass()) {
            return false;
        } else {
            Book other = (Book) obj;
            return this.title.equals(other.title) && this.author.equals(other.author) && this.year == other.year
                    && this.edition == other.edition;
        }

    }
}
