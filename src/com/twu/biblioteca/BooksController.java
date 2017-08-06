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
        Book bookD = new Book("Heart of Darkness","Konrad Singer", 1974);
        availableItems.add(bookA);
        availableItems.add(bookB);
        availableItems.add(bookC);
        availableItems.add(bookD);
        User user1 = usersController.findUserByName("neil halligan");
        rentItem(bookC, user1);
        User user2 = usersController.findUserByName("marco polo");
        rentItem(bookD, user2);
    }

    public void printAvailableItems(){
        System.out.println("Type book title to checkout book, type \"back\" to go back");
        for(Book book : availableItems) {
            System.out.println(book.getTitle() + " | " + book.getAuthor() + " | " + book.getYearPublished());
        }
    }

    public void printRentedItemsAsUser(User user){
        System.out.println("Type book title to return book, type \"back\" to go back");
        for(Book book : rentedItemsByUser(user)) {
            System.out.println(book.getTitle() + " | " + book.getAuthor() + " | " + book.getYearPublished());
        }
    }

    public void printRentedItemsAsLibrarian(){
        System.out.println("Type book title to return book, type \"back\" to go back");
        for(Book book : rentedItems) {
            System.out.println(book.getTitle() + " | " + book.getAuthor() + " | " +
                    book.getYearPublished() + " | " + book.getRenterUsername());
        }
    }

    public void rentItem(Book book, User user) {
        book.setUser(user);
        availableItems.remove(book);
        rentedItems.add(book);
    }

    public void returnItem(Book book) {
        book.setUser(null);
        rentedItems.remove(book);
        availableItems.add(book);
    }

    public void selectItemToCheckout(String selector, User user) {
        Book bookToCheckout = findRentalByTitle(selector, availableItems);
        if (bookToCheckout != null) {
            rentItem(bookToCheckout, user); // , user
            System.out.println("Thank you! Enjoy the book");
        } else if (selector.equals("back")) {
        } else {
            System.out.println("That book is not available.");
        }
    }

    public void selectItemToReturn(String selector, User user) {
        Book bookToReturn = findRentalByTitle(selector, rentedItemsByUser(user));
        if (bookToReturn != null) {
            returnItem(bookToReturn);
            System.out.println("Thank you for returning the book.");
        } else if (selector.equals("back")) {
        } else {
            System.out.println("That is not a valid book to return.");
        }
    }

    public Book findRentalByTitle(String bookTitle, List<Book> books) {
        Book foundBook = null;
        for (Book book : books) {
            if (bookTitle.equals(book.getTitle())) {
                foundBook = book;
            }
        }
        return foundBook;
    }

    public List<Book> rentedItemsByUser(User user) {
        List<Book> rentedItemByUser = new ArrayList<>();
        for(Book book : rentedItems) {
            if (book.getUser() == user) {
                rentedItemByUser.add(book);
            }
        }
        return rentedItemByUser;
    }

    public UsersController getUsersController() {
        return usersController;
    }
}
