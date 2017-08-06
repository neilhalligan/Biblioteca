package com.twu.biblioteca;

import org.junit.*;
import java.io.*;
import static org.junit.Assert.*;

public class BibliotecaAppTest {
    private ByteArrayOutputStream outContent;
    private BibliotecaApp biblioteca;
    private String bookMenuMessage;
    private String mainMenuMessage;
    private String returnBookMenuMessage;
    private String movieMenuMessage;
    private String returnMovieMenuMessage;

    @Before
    public void setUp() throws Exception {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        biblioteca = new BibliotecaApp();
        biblioteca.seed();
        bookMenuMessage = "Type book title to checkout book, type \"back\" to go back\n";
        movieMenuMessage = "Type movie title to checkout movie, type \"back\" to go back\n";
        mainMenuMessage =
            "Main Menu\nEnter one of the following options to continue\n" +
            "-available books\n-return books\n-list movies\n-return movies\n-quit\n";
        returnBookMenuMessage = "Type book title to return book, type \"back\" to go back\n";
        returnMovieMenuMessage = "Type movie title to return movie, type \"back\" to go back\n";
    }

    @Test
    public void printsWelcomeMessage() throws Exception {
        biblioteca.welcome();
        String expectedOutput  = "Hello, welcome to Biblioteca!\n";
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void mainMenuInvalidSelection() throws Exception {
        Reader InputString = new StringReader("invalid\n");
        BufferedReader menuInput = new BufferedReader(InputString);
        String expectedOutput = "Select a valid menu option!\n";
        biblioteca.selectMainMenuAsUser(menuInput);
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void mainMenuQuit() throws Exception {
        Reader InputString = new StringReader("quit\navailable books\n");
        BufferedReader menuInput = new BufferedReader(InputString);
        String expectedOutput = "";
        biblioteca.selectMainMenuAsUser(menuInput);
        assertEquals(expectedOutput, outContent.toString());
    }
}
