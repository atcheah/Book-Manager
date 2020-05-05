package models;

public enum BookType {
    FAMILY("FAMILY"),
    FICTION("FICTION"),
    NONFICTION("NONFICTION"),
    TEXTBOOK("TEXTBOOK"),
    COOKING("COOKING"),
    ;
    private String name;

    private BookType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    // REQUIRES: non empty string
    // EFFECTS: Converts string into book type
    public static BookType getByName(String n) {
        return BookType.valueAt(n);
    }

    // EFFECTS: Converts string into book type
    public static BookType valueAt(String name) {
        if (name.equals("FAMILY")) {
            return BookType.FAMILY;
        } else if (name.equals("FICTION")) {
            return BookType.FICTION;
        } else if (name.equals("NONFICTION")) {
            return BookType.NONFICTION;
        } else if (name.equals("TEXTBOOK")) {
            return BookType.TEXTBOOK;
        } else {
            return BookType.COOKING;
        }
    }
}
