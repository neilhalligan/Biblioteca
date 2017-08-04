package com.twu.biblioteca;

import org.junit.*;

import java.io.*;

import static org.junit.Assert.*;

public class MoviesIntegrationTest {
    private ByteArrayOutputStream outContent;
    private BibliotecaApp biblioteca;
    private String mainMenuMessage;
    private String movieMenuMessage;
    private String returnMovieMenuMessage;

    @Before
    public void setUp() throws Exception {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        biblioteca = new BibliotecaApp();
//        biblioteca.booksController.seedRentals();
        biblioteca.moviesController.seedRentals();
        movieMenuMessage = "Type movie title to checkout movie, type \"back\" to go back\n";
        mainMenuMessage =
                "Main Menu\nEnter one of the following options to continue\n" +
                        "-list books\n-return books\n-list movies\n-return movies\n-quit\n";
        returnMovieMenuMessage = "Type movie title to return movie, type \"back\" to go back\n";
    }

    @Test
    public void goCheckoutMovieAndPrintMainMenu() throws Exception {
        Movie movieToCheckout = biblioteca.moviesController.availableItems.get(0);
        Reader menuInputString = new StringReader("list movies\n" + movieToCheckout.getName());
        BufferedReader menuInput = new BufferedReader(menuInputString);
        String expectedOutput = movieMenuMessage;
        for(Movie movie : biblioteca.moviesController.availableItems) {
            expectedOutput += movie.getName() + " | " + movie.getYear() + " | " + movie.getDirector() + " | " + movie.getMovieRating() + "\n";
        }
        biblioteca.selectMainMenu(menuInput);
        expectedOutput += "Thank you! Enjoy the movie\n";
        expectedOutput += mainMenuMessage;
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void goCheckoutMovieMenuAndGoBack() throws Exception {
        Reader menuInputString = new StringReader("list movies\nback\n");
        BufferedReader menuInput = new BufferedReader(menuInputString);
        String expectedOutput = movieMenuMessage;
        for(Movie movie : biblioteca.moviesController.availableItems) {
            expectedOutput += movie.getName() + " | " + movie.getYear() + " | " + movie.getDirector() + " | " + movie.getMovieRating() + "\n";
        }
        biblioteca.selectMainMenu(menuInput);
        expectedOutput += mainMenuMessage;
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void goCheckoutMovieMenuAndInvalidSelection() throws Exception {
        Reader menuInputString = new StringReader("list movies\ninvalid movie\n");
        BufferedReader menuInput = new BufferedReader(menuInputString);
        String expectedOutput = movieMenuMessage;
        for(Movie movie : biblioteca.moviesController.availableItems) {
            expectedOutput += movie.getName() + " | " + movie.getYear() + " | " + movie.getDirector() + " | " + movie.getMovieRating() + "\n";
        }
        biblioteca.selectMainMenu(menuInput);
        expectedOutput += "That movie is not available.\n";
        expectedOutput += mainMenuMessage;
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void goReturnMovieAndPrintMainMenu() throws Exception {
        Movie movieToReturn = biblioteca.moviesController.rentedItems.get(0);
        Reader menuInputString = new StringReader("return movies\n" + movieToReturn.getName());
        BufferedReader menuInput = new BufferedReader(menuInputString);
        String expectedOutput = returnMovieMenuMessage;
        for(Movie movie : biblioteca.moviesController.rentedItems) {
            expectedOutput += movie.getName() + " | " + movie.getYear() + " | " + movie.getDirector() + " | " + movie.getMovieRating() + "\n";
        }
        biblioteca.selectMainMenu(menuInput);
        expectedOutput += "Thank you for returning the movie.\n";
        expectedOutput += mainMenuMessage;
        assertEquals(expectedOutput, outContent.toString());
        assertTrue(biblioteca.moviesController.availableItems.contains(movieToReturn));
        assertFalse(biblioteca.moviesController.rentedItems.contains(movieToReturn));
    }

    @Test
    public void goReturnMovieMenuAndGoBack() throws Exception {
        Reader menuInputString = new StringReader("return movies\nback\n");
        BufferedReader menuInput = new BufferedReader(menuInputString);
        String expectedOutput = returnMovieMenuMessage;
        for(Movie movie : biblioteca.moviesController.rentedItems) {
            expectedOutput += movie.getName() + " | " + movie.getYear() + " | " + movie.getDirector() + " | " + movie.getMovieRating() + "\n";
        }
        biblioteca.selectMainMenu(menuInput);
        expectedOutput += mainMenuMessage;
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void goReturnMovieMenuAndInvalidSelection() throws Exception {
        Reader menuInputString = new StringReader("return movies\ninvalid selection\n");
        BufferedReader menuInput = new BufferedReader(menuInputString);
        String expectedOutput = returnMovieMenuMessage;
        for(Movie movie : biblioteca.moviesController.rentedItems) {
            expectedOutput += movie.getName() + " | " + movie.getYear() + " | " + movie.getDirector() + " | " + movie.getMovieRating() + "\n";
        }
        biblioteca.selectMainMenu(menuInput);
        expectedOutput += "That is not a valid movie to return.\n";
        expectedOutput += mainMenuMessage;
        assertEquals(expectedOutput, outContent.toString());
    }
}
