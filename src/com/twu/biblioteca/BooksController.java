package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.List;

public class BooksController extends RentalsController {
    List<Book> availableItems = new ArrayList<>();
    List<Book> rentedItems = new ArrayList<>();
    public UsersController usersController;

    public BooksController(UsersController u){
        usersController = u;
    }

    public void seedRentals(){
        Book bookA = new Book("The Great Gatsby", "F. Scott Fitzgerald", 1922);
        Book bookB = new Book("Moby Dick","Herman Melville", 1851);
        Book bookC = new Book("Frankenstein","Mary Shelley", 1818);
        availableItems.add(bookA);
        availableItems.add(bookB);
        availableItems.add(bookC);
        User user = usersController.getUsers().get(0);
        rentItem(bookC, user);

    }

    public void printAvailableItems(){
        System.out.println("Type book title to checkout book, type \"back\" to go back");
        for(Book book : availableItems) {
            System.out.println(book.getTitle() + " | " + book.getAuthor() + " | " + book.getYearPublished());
        }
    }

    public void printRentedItems(){
        System.out.println("Type book title to return book, type \"back\" to go back");
        for(Book book : rentedItems) {
            System.out.println(book.getTitle() + " | " + book.getAuthor() + " | " + book.getYearPublished());
        }
    }

    public void rentItem(Book book, User user) {
//        User user = usersController.getUsers().get(0);
        user.addToRentedBooks(book);
        availableItems.remove(book);
        rentedItems.add(book);
    }

    public void returnItem(Book book) {
        rentedItems.remove(book);
        availableItems.add(book);
    }

    public void selectItemToCheckout(String selector) {
        Book bookToCheckout = findRentalByTitle(selector, availableItems);
        User user = usersController.getUsers().get(0);
        if (bookToCheckout != null) {
            rentItem(bookToCheckout, user); // , user
            System.out.println("Thank you! Enjoy the book");
        } else if (selector.equals("back")) {
        } else {
            System.out.println("That book is not available.");
        }
    }

    public void selectItemToReturn(String selector) {
        Book bookToReturn = findRentalByTitle(selector, rentedItems);
        if (bookToReturn != null) {
            returnItem(bookToReturn);
            System.out.println("Thank you for returning the book.");
        } else if (selector.equals("back")) {
        } else {
            System.out.println("That is not a valid book to return.");
        }
    }

    private Book findRentalByTitle(String bookTitle, List<Book> books) {
        Book foundBook = null;
        for (Book book : books) {
            if (bookTitle.equals(book.getTitle())) {
                foundBook = book;
            }
        }
        return foundBook;
    }

    public UsersController getUsersController() {
        return usersController;
    }
}
