package com.twu.biblioteca;

/**
 * Created by neil on 7/30/17.
 */
public class Movie {
    private String name;
    private int year;
    private String director;
    private int movieRating;

    public Movie(String n, int y, String d, int mr) {
        name = n;
        year = y;
        director = d;
        movieRating = mr;
    }

    public String getName() {
        return name;
    }

    public int getYear() {
        return year;
    }

    public String getDirector() {
        return director;
    }

    public int getMovieRating() {
        return movieRating;
    }
}
