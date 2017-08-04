package com.twu.biblioteca;

import org.junit.*;

import static org.junit.Assert.*;

public class MovieTest {

    private Movie movie1;

    @Before
    public void before() {
        movie1 = new Movie("Almost Famous", 2005, "Danny Boyle", 8);
    }

    @Test
    public void hasName() {
        assertEquals(movie1.getName(), "Almost Famous");
    }

    @Test
    public void hasYear() {
        assertEquals(movie1.getYear(), 2005);
    }

    @Test
    public void hasDirector() {
        assertEquals(movie1.getDirector(), "Danny Boyle");
    }

    @Test
    public void hasMovieRating() {
        assertEquals(movie1.getMovieRating(), 8);
    }

    @Test
    public void equals() throws Exception {
        Movie movie2 = new Movie("Chicken Run", 2001, "Francis Ford Coppola", 8);
        Movie movie3 = new Movie("Almost Famous", 2005, "Danny Boyle", 8);
        assertEquals(movie1, movie3);
        assertNotEquals(movie1, movie2);
    }
}