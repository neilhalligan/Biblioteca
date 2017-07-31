package com.twu.biblioteca;

/**
 * Created by neil on 7/30/17.
 */

import org.junit.*;
import java.io.*;
import java.util.*;
import static org.junit.Assert.*;

public class BookIntegrationTest {
    private ByteArrayOutputStream outContent;
    private BibliotecaApp biblioteca;
    private Book book1;
    private Book book2;
    private Book book3;
    private String bookMenuMessage;
    private String mainMenuMessage;
    private String returnMenuMessage;

    @Before
    public void setUp() throws Exception {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        biblioteca = new BibliotecaApp();
        biblioteca.populateBookList();
        book1 = new Book("The Great Gatsby", "F. Scott Fitzgerald", 1922);
        book2 = new Book("Moby Dick","Herman Melville", 1851);
        book3 = new Book("Frankenstein","Mary Shelley", 1818);
        bookMenuMessage = "Type book title to checkout book, type \"back\" to go back\n";
        mainMenuMessage = "Main Menu\n1 - List Books\n2 - Return Books\n3 - Quit\n";
        returnMenuMessage = "Type book title to return book, type \"back\" to go back\n";
    }
}
