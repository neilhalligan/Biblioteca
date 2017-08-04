package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.List;

public class MoviesController extends RentalsController {
    List<Movie> availableItems = new ArrayList<>();
    List<Movie> rentedItems = new ArrayList<>();


    public void seedRentals(){
        Movie movieA = new Movie("Chicken Run", 2001, "Francis Ford Coppola", 8);
        Movie movieB = new Movie("Almost Famous", 2005, "Danny Boyle", 8);
        Movie movieC = new Movie("Die Hard",1988, "John McTiernan", 9);
        availableItems.add(movieA);
        availableItems.add(movieB);
        availableItems.add(movieC);
        rentItem(movieC);
    }

    public void printAvailableItems(){
        System.out.println("Type movie title to checkout movie, type \"back\" to go back");
        for(Movie movie : availableItems) {
            System.out.println(movie.getName() + " | " + movie.getYear() + " | " + movie.getDirector() + " | " + movie.getMovieRating());
        }
    }

    public void printRentedItems(){
        System.out.println("Type movie title to return movie, type \"back\" to go back");
        for(Movie movie : rentedItems) {
            System.out.println(movie.getName() + " | " + movie.getYear() + " | " + movie.getDirector() + " | " + movie.getMovieRating());
        }
    }

    public void rentItem(Movie movie) {
        rentedItems.add(movie);
        availableItems.remove(movie);
    }

    public void returnItem(Movie movie) {
        rentedItems.remove(movie);
        availableItems.add(movie);
    }

    public void selectItemToCheckout(String selector) {
        Movie movieToRent = findRentalByName(selector, availableItems);
        if (movieToRent != null) {
            rentItem(movieToRent);
            System.out.println("Thank you! Enjoy the movie");
        } else if (selector.equals("back")) {
        } else {
            System.out.println("That movie is not available.");
        }
    }

    public void selectItemToReturn(String selector) {
        Movie itemToReturn = findRentalByName(selector, rentedItems);
        if (itemToReturn != null) {
            returnItem(itemToReturn);
            System.out.println("Thank you for returning the movie.");
        } else if (selector.equals("back")) {
        } else {
            System.out.println("That is not a valid movie to return.");
        }
    }

    private Movie findRentalByName(String movieTitle, List<Movie> movies) {
        Movie foundMovie = null;
        for (Movie movie : movies) {
            if (movieTitle.equals(movie.getName())) {
                foundMovie = movie;
            }
        }
        return foundMovie;
    }
}
