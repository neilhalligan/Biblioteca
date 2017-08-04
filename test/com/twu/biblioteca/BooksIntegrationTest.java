package com.twu.biblioteca;

import org.junit.*;

import java.io.*;

import static org.junit.Assert.*;

public class BooksIntegrationTest {
    private ByteArrayOutputStream outContent;
    private BibliotecaApp biblioteca;
    private String bookMenuMessage;
    private String mainMenuMessage;
    private String returnBookMenuMessage;

    @Before
    public void setUp() throws Exception {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        biblioteca = new BibliotecaApp();
        biblioteca.seed();
//        biblioteca.booksController.seedRentals();
//        biblioteca.moviesController.seedRentals();
        bookMenuMessage = "Type book title to checkout book, type \"back\" to go back\n";
        mainMenuMessage =
                "Main Menu\nEnter one of the following options to continue\n" +
                "-list books\n-return books\n-list movies\n-return movies\n-quit\n";
        returnBookMenuMessage = "Type book title to return book, type \"back\" to go back\n";
    }

    @Test
    public void checkoutBookAndPrintMainMenu() throws Exception {
        Book bookToCheckout = biblioteca.booksController.availableItems.get(0);
        Reader menuInputString = new StringReader("list books\n" + bookToCheckout.getTitle());
        BufferedReader menuInput = new BufferedReader(menuInputString);
        String expectedOutput = bookMenuMessage;
        for(Book book : biblioteca.booksController.availableItems) {
            expectedOutput += book.getTitle() + " | " + book.getAuthor() + " | " + book.getYearPublished() + "\n";
        }
        biblioteca.selectMainMenu(menuInput);
        expectedOutput += "Thank you! Enjoy the book\n";
        expectedOutput += mainMenuMessage;
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void checkoutMenuGoBack() throws Exception {
        Reader menuInputString = new StringReader("list books\nback\n");
        BufferedReader menuInput = new BufferedReader(menuInputString);
        String expectedOutput = bookMenuMessage;
        for(Book book : biblioteca.booksController.availableItems) {
            expectedOutput += book.getTitle() + " | " + book.getAuthor() + " | " + book.getYearPublished() + "\n";
        }
        biblioteca.selectMainMenu(menuInput);
        expectedOutput += mainMenuMessage;
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void checkoutMenuInvalidSelection() throws Exception {
        Reader menuInputString = new StringReader("list books\ninvalid book\n");
        BufferedReader menuInput = new BufferedReader(menuInputString);
        String expectedOutput = bookMenuMessage;
        for(Book book : biblioteca.booksController.availableItems) {
            expectedOutput += book.getTitle() + " | " + book.getAuthor() + " | " + book.getYearPublished() + "\n";
        }
        biblioteca.selectMainMenu(menuInput);
        expectedOutput += "That book is not available.\n";
        expectedOutput += mainMenuMessage;
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void returnBookAndPrintMainMenu() throws Exception {
        Book bookToReturn = biblioteca.booksController.rentedItems.get(0);
        Reader menuInputString = new StringReader("return books\n" + bookToReturn.getTitle());
        BufferedReader menuInput = new BufferedReader(menuInputString);
        String expectedOutput = returnBookMenuMessage;
        for(Book book : biblioteca.booksController.rentedItems) {
            expectedOutput += book.getTitle() + " | " + book.getAuthor() + " | " + book.getYearPublished() + "\n";
        }
        biblioteca.selectMainMenu(menuInput);
        expectedOutput += "Thank you for returning the book.\n";
        expectedOutput += mainMenuMessage;
        assertEquals(expectedOutput, outContent.toString());
        assertTrue(biblioteca.booksController.availableItems.contains(bookToReturn));
        assertFalse(biblioteca.booksController.rentedItems.contains(bookToReturn));
    }

    @Test
    public void goReturnBookMenuGoBack() throws Exception {
        Reader menuInputString = new StringReader("return books\nback\n");
        BufferedReader menuInput = new BufferedReader(menuInputString);
        String expectedOutput = returnBookMenuMessage;
        for(Book book : biblioteca.booksController.rentedItems) {
            expectedOutput += book.getTitle() + " | " + book.getAuthor() + " | " + book.getYearPublished() + "\n";
        }
        biblioteca.selectMainMenu(menuInput);
        expectedOutput += mainMenuMessage;
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void goReturnMenuInvalidSelection() throws Exception {
        Reader menuInputString = new StringReader("return books\ninvalid selection\n");
        BufferedReader menuInput = new BufferedReader(menuInputString);
        String expectedOutput = returnBookMenuMessage;
        for(Book book : biblioteca.booksController.rentedItems) {
            expectedOutput += book.getTitle() + " | " + book.getAuthor() + " | " + book.getYearPublished() + "\n";
        }
        biblioteca.selectMainMenu(menuInput);
        expectedOutput += "That is not a valid book to return.\n";
        expectedOutput += mainMenuMessage;
        assertEquals(expectedOutput, outContent.toString());
    }
}
