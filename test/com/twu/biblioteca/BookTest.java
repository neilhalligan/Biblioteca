package com.twu.biblioteca;

import org.junit.*;

import static org.junit.Assert.*;

public class BookTest {
    private Book book;

    @Before
    public void before() {
        book = new Book("On the Road", "Jack Kerouac", 1957);
    }

    @Test
    public void hasTitle() {
        assertEquals(book.getTitle(), "On the Road");
    }

    @Test
    public void hasAuthor() {
        assertEquals(book.getAuthor(), "Jack Kerouac");
    }

    @Test
    public void hasYearPublished() {
        assertEquals(book.getYearPublished(), 1957);
    }

    @Test
    public void equals() throws Exception {
        Book testBook1 = new Book("The Great Gatsby", "F. Scott Fitzgerald", 1922);
        Book testBook2 = new Book("Moby Dick","Herman Melville", 1851);
        Book testBook3 = new Book("The Great Gatsby", "F. Scott Fitzgerald", 1922);
        assertEquals(testBook1, testBook3);
        assertNotEquals(testBook1, testBook2);
    }
}