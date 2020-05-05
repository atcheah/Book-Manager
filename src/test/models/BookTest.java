package models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BookTest {

    private Book ficBook;
    private Book textBook;
    private Book cookBook;

    @BeforeEach
    public void setUp() {
        ficBook = new Book("Harry Potter and the Deathy Hallows", "J.K. Rowling", BookType.FICTION, 2007, 1);
        textBook = new Book("Introduction to Algorithms", "Thomas H. Cormen", BookType.TEXTBOOK, 1992, 1);
        cookBook = new Book("Mastering the Art of French Cooking", "Julia Child", BookType.COOKING, 1960, 1);
    }

    @Test
    public void testgetters() {
        assertEquals(textBook.getTitle(), "Introduction to Algorithms");
        assertEquals(textBook.getAuthor(), "Thomas H. Cormen");
        assertEquals(cookBook.getType(), BookType.COOKING);
        assertEquals(ficBook.getYear(), 2007);
        assertEquals(cookBook.getEdition(), 1);
    }

    @Test
    public void testloanStatus() {
        assertEquals(textBook.onLoan(), false);
        textBook.setOnLoan();
        assertEquals(textBook.onLoan(), true);
        textBook.notOnLoan();
        assertEquals(textBook.onLoan(), false);
    }

    @Test
    public void testHash() {
        assertEquals("Introduction to AlgorithmsThomas H. Cormen19921".hashCode(), textBook.hashCode());
    }

    @Test
    public void testEquals() {
        Book testBook = textBook;
        assertTrue(textBook.equals(textBook));
        assertFalse(textBook.equals(cookBook));
        assertTrue(textBook.equals(testBook));
        assertFalse(textBook.equals(1));
        testBook = new Book("Introduction to Algorithms", "Thomas H. Cormen", BookType.TEXTBOOK, 1992, 1);
        assertTrue(textBook.equals(testBook));

    }


}
