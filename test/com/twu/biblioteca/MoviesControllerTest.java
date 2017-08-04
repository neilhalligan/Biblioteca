package com.twu.biblioteca;

import org.junit.*;
import java.io.*;
import java.util.*;

import static org.junit.Assert.*;

public class MoviesControllerTest {
    private ByteArrayOutputStream outContent;
    private Movie movie1;
    private Movie movie2;
    private Movie movie3;
    private String movieMenuMessage;
    private String returnMenuMessage;

    private MoviesController moviesController;

    @Before
    public void setUp() throws Exception {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        moviesController = new MoviesController();
        moviesController.seedRentals();
        movie1 = new Movie("Chicken Run", 2001, "Francis Ford Coppola", 8);
        movie2 = new Movie("Almost Famous", 2005, "Danny Boyle", 8);
        movie3 = new Movie("Die Hard",1988, "John McTiernan", 9);
        movieMenuMessage = "Type movie title to checkout movie, type \"back\" to go back\n";
        returnMenuMessage = "Type movie title to return movie, type \"back\" to go back\n";
    }

    @Test
    public void hasMoviesList() throws Exception {
        List<Movie> mockMoviesList = new ArrayList<>(Arrays.asList(movie1, movie2));
        assertEquals(mockMoviesList, moviesController.availableItems);
    }

    @Test
    public void hasCustomerMovies() throws Exception {
        List<Movie> testCustomerMovies = new ArrayList<>();
        testCustomerMovies.add(movie3);
        assertEquals(moviesController.rentedItems, testCustomerMovies);
    }

    @Test
    public void printLibraryMovieList() throws Exception {
        String expectedOutput = movieMenuMessage;
        for(Movie movie : moviesController.availableItems) {
            expectedOutput += movie.getName() + " | " + movie.getYear() + " | " + movie.getDirector() + " | " + movie.getMovieRating() + "\n";
        }
        moviesController.printAvailableItems();
        Assert.assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void printReturnMovieList() throws Exception {
        String expectedOutput = returnMenuMessage;
        for(Movie movie : moviesController.rentedItems) {
            expectedOutput += movie.getName() + " | " + movie.getYear() + " | " + movie.getDirector() + " | " + movie.getMovieRating() + "\n";
        }
        moviesController.printRentedItems();
        Assert.assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void populateMoviesList() throws Exception {
        assertEquals(movie1, moviesController.availableItems.get(0));
        assertEquals(movie2, moviesController.availableItems.get(1));
    }

    @Test
    public void addToCustomerMovies() throws Exception {
        Movie customerMovies = moviesController.availableItems.get(0);
        moviesController.rentItem(customerMovies);
        assertTrue(moviesController.rentedItems.contains(customerMovies));
        assertFalse(moviesController.availableItems.contains(customerMovies));
    }

    @Test
    public void returnToLibraryMovies() throws Exception {
        Movie returnMovie = moviesController.rentedItems.get(0);
        moviesController.returnItem(returnMovie);
        assertTrue(moviesController.availableItems.contains(returnMovie));
        assertFalse(moviesController.rentedItems.contains(returnMovie));
    }
}
