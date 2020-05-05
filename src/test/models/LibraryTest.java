package models;

import exceptions.ContainsException;
import exceptions.NegativeNumException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class LibraryTest {

    private Book ficBook;
    private Book nonficBook;
    private Book textBook;
    private Book cookBook;
    private Book familyBook;
    private Book loneBook;
    private Library testLib;

    @BeforeEach
    public void setUp() {
        testLib = new Library();
        ficBook = new Book("Harry Potter and the Deathly Hallows", "J.K. Rowling", BookType.FICTION, 2007, 1);
        testLib.storeBook(ficBook);
        nonficBook = new Book("The Immmortal Life of Henrietta Lacks", "Rebecca Skloot", BookType.NONFICTION, 2008, 1);
        testLib.storeBook(nonficBook);
        textBook = new Book("Introduction to Algorithms", "Thomas H. Cormen", BookType.TEXTBOOK, 1992, 1);
        testLib.storeBook(textBook);
        cookBook = new Book("Mastering the Art of French Cooking", "Julia Child", BookType.COOKING, 1960, 1);
        testLib.storeBook(cookBook);
        familyBook = new Book("Caterpillar", "Eric Carle", BookType.FAMILY, 1969,1);
        testLib.storeBook(familyBook);
        loneBook = new Book("Lord Of The Rings", "JRR. Tolkien", BookType.FICTION, 1954, 1);
    }

    @Test
    public void testinCatalogue() {
        assertEquals(testLib.contains(cookBook), true);
        assertEquals(testLib.contains(ficBook), true);
        assertEquals(testLib.contains(textBook), true);
        assertEquals(testLib.contains(familyBook), true);
        assertEquals(testLib.contains(nonficBook), true);
        assertFalse(testLib.contains(loneBook));
    }

    @Test
    public void testcanLoan() {
        assertEquals(testLib.canLoan(ficBook), true);
        testLib.checkOutBook(ficBook);
        assertEquals(testLib.canLoan(ficBook), false);

        assertEquals(testLib.canLoan(cookBook), true);
        testLib.checkOutBook(cookBook);
        assertEquals(testLib.canLoan(cookBook), false);
    }

    @Test
    public void testreturnBook() {
        assertEquals(testLib.canLoan(textBook), true);
        assertEquals(testLib.returnBook(textBook), false);
        assertEquals(testLib.checkOutBook(textBook), true);
        assertEquals(testLib.canLoan(textBook), false);
        assertEquals(testLib.checkOutBook(textBook), false);
        assertEquals(testLib.returnBook(textBook), true);
        assertEquals(testLib.canLoan(textBook), true);
    }

    @Test
    public void displayTest() {
        assertEquals("Mastering the Art of French Cooking by Julia Child\n"
                + "Harry Potter and the Deathly Hallows by J.K. Rowling\n"
                + "The Immmortal Life of Henrietta Lacks by Rebecca Skloot\n"
                + "Introduction to Algorithms by Thomas H. Cormen\n"
                + "Caterpillar by Eric Carle\n", testLib.displayContents());
    }

}
