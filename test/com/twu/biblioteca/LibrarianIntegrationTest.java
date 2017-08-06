package com.twu.biblioteca;

import org.junit.*;

import java.io.*;

import static org.junit.Assert.*;

public class LibrarianIntegrationTest {
    private ByteArrayOutputStream outContent;
    private BibliotecaApp biblioteca;
    private String loginMenu;
    private String librarianMainMenu;
    private String returnBookMenuMessage;

    @Before
    public void before() throws Exception {
        biblioteca = new BibliotecaApp();
        biblioteca.seed();
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        loginMenu = "Please enter username\n" +
                "Please enter password\n";
        librarianMainMenu = "Main Menu\n" +
                "Enter one of the following options to continue\n" +
                "-rented books\n" +
                "-rented movies\n" +
                "-quit\n";
        returnBookMenuMessage = "Type \"back\" to go back\n";
    }

    @Test
    public void loginToLibrarianMainMenu() {
        Reader menuInputString = new StringReader("christopher columbus\n123-4567\nquit");
        BufferedReader menuInput = new BufferedReader(menuInputString);
        String expectedOutput = loginMenu;
        expectedOutput += librarianMainMenu;
        biblioteca.authenticateUser(menuInput);
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void loginAsLibrarianAndViewRentedBooks() {
        Reader menuInputString = new StringReader("christopher columbus\n123-4567\nrented books\nback\nquit");
        BufferedReader menuInput = new BufferedReader(menuInputString);
        String expectedOutput = loginMenu;
        expectedOutput += librarianMainMenu;
        expectedOutput += returnBookMenuMessage;
        for(Book book : biblioteca.booksController.rentedItems) {
            expectedOutput += book.getTitle() + " | " + book.getAuthor() + " | " + book.getYearPublished() + " | " + book.getRenterUsername() + "\n";
        }
        expectedOutput += librarianMainMenu;
        biblioteca.authenticateUser(menuInput);
        assertEquals(expectedOutput, outContent.toString());
    }
}
