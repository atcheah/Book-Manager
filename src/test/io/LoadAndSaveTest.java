package io;

import exceptions.ContainsException;
import exceptions.NegativeNumException;
import models.Book;
import models.BookType;
import models.Library;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static io.LoadAndSave.splitOnComma;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class LoadAndSaveTest {
    private static LoadAndSave loadLibrary = new LoadAndSave();
    private static LoadAndSave saveLibrary = new LoadAndSave();
    private static String file = "LoadAndSaveTest";
    private Book ficBook;
    private Book nonficBook;
    private Book textBook;
    private Book cookBook;
    private Library testLib;

    @BeforeEach
    public void setUp() {
        testLib = new Library();
        ficBook = new Book("Harry Potter and the Deathy Hallows", "J.K. Rowling", BookType.FICTION, 2007, 1);
        testLib.storeBook(ficBook);
        nonficBook = new Book("The Immmortal Life of Henrietta Lacks", "Rebecca Skloot", BookType.NONFICTION, 2008, 1);
        testLib.storeBook(nonficBook);
        textBook = new Book("Introduction to Algorithms", "Thomas H. Cormen", BookType.TEXTBOOK, 1992, 1);
        testLib.storeBook(textBook);
        cookBook = new Book("Mastering the Art of French Cooking", "Julia Child", BookType.COOKING, 1960, 1);
        testLib.storeBook(cookBook);
    }

    @Test
    public void saveTest() throws IOException, NegativeNumException, ContainsException {
        Library x = testLib;
        saveLibrary.save(helper(testLib), file);
        loadLibrary.load(x, file);
        assertEquals(helper(x).get(1), helper(testLib).get(1));
        assertEquals(helper(x).get(2), helper(testLib).get(2));
    }

    @Test
    public void loadTest() throws IOException, NegativeNumException, ContainsException {
        Library x = new Library();
        saveLibrary.save(helper(testLib), file);
        loadLibrary.load(x, file);
        assertEquals(helper(x).get(1), helper(testLib).get(1));
        assertEquals(helper(x).get(2), helper(testLib).get(2));
    }

    @Test
    public void failLoad() throws IOException, NegativeNumException, ContainsException {
        saveLibrary.save(helper(testLib), "error");
    }

    @Test
    public void splitTest() {
        String line = "The Great Gatsby, F. Scott Fitzgerald, FICTION, 1925, 1";
        List<String> testList = new ArrayList<>();
        testList.add("The Great Gatsby");
        testList.add("F. Scott Fitzgerald");
        testList.add("FICTION");
        testList.add("1925");
        testList.add("1");
        testList.equals(splitOnComma(line));
    }

    public List helper(Library library) {
        List<Book> totalCatalogue = new LinkedList<>();
        totalCatalogue.addAll(library.returnCook());
        totalCatalogue.addAll(library.returnFiction());
        totalCatalogue.addAll(library.returnNonFiction());
        totalCatalogue.addAll(library.returnText());
        totalCatalogue.addAll(library.returnFamily());
        return totalCatalogue;
    }

}
