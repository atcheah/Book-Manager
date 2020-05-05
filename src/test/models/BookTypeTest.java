package models;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BookTypeTest {

    @Test
    public void getNameTest() {
        assertEquals("FAMILY",BookType.FAMILY.getName());
        assertEquals("FICTION",BookType.FICTION.getName());
        assertEquals("NONFICTION",BookType.NONFICTION.getName());
        assertEquals("TEXTBOOK",BookType.TEXTBOOK.getName());
        assertEquals("COOKING",BookType.COOKING.getName());
    }

    @Test
    public void getByNameTest() {
        assertEquals(BookType.getByName("FAMILY"),BookType.FAMILY);
        assertEquals(BookType.getByName("FICTION"),BookType.FICTION);
        assertEquals(BookType.getByName("NONFICTION"),BookType.NONFICTION);
        assertEquals(BookType.getByName("TEXTBOOK"),BookType.TEXTBOOK);
        assertEquals(BookType.getByName("COOKING"),BookType.COOKING);
    }

    @Test
    public void valueAtTest() {
        assertEquals(BookType.valueAt("FAMILY"),BookType.FAMILY);
        assertEquals(BookType.valueAt("FICTION"),BookType.FICTION);
        assertEquals(BookType.valueAt("NONFICTION"),BookType.NONFICTION);
        assertEquals(BookType.valueAt("TEXTBOOK"),BookType.TEXTBOOK);
        assertEquals(BookType.valueAt("COOKING"),BookType.COOKING);
    }
}
