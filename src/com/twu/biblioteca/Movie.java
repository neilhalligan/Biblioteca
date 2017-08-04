package com.twu.biblioteca;

public class Movie extends Rental {
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

    public boolean equals(Object object) {
        Movie movie = (Movie) object;
        return (name == movie.name &&
                year == movie.year &&
                director == movie.director &&
                movieRating == movie.movieRating);
    }
}
