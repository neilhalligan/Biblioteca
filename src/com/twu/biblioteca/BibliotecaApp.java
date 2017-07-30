package com.twu.biblioteca;

import java.io.*;
import java.lang.*;
import java.util.*;

public class BibliotecaApp {
    List<Book> libraryBooks = new ArrayList<Book>();
    List<Book> customerBooks = new ArrayList<Book>();
    private boolean running = true;

    public static void main(String[] args) {
        BibliotecaApp biblio = new BibliotecaApp();
        biblio.go();
    }

    public void go() {
        welcome();
        populateBookList();
        printMainMenu();
        while (running) {
            selectMainMenu(getReader());
        }
    }

    public void welcome() {
        System.out.println("Hello, welcome to Biblioteca!");
    }

    public void populateBookList(){
        Book bookA = new Book("The Great Gatsby", "F. Scott Fitzgerald", 1922);
        Book bookB = new Book("Moby Dick","Herman Melville", 1851);
        Book bookC = new Book("Frankenstein","Mary Shelley", 1818);
        libraryBooks.add(bookA);
        libraryBooks.add(bookB);
        libraryBooks.add(bookC);
        addToCustomerBooks(bookC);
    }

    public void printMainMenu(){
        System.out.println("Main Menu\n1 - List Books\n2 - Return Books\n3 - Quit");
    }

    public void selectMainMenu(BufferedReader bufferSelector) {
        String menuSelector = getInput(bufferSelector);
        if (menuSelector.equals("1")) {
            printBookList();
            selectBookToCheckout(getInput(bufferSelector));
        } else if (menuSelector.equals("2")) {
            printReturnBookList();
            selectBookToReturn(getInput(bufferSelector));
        } else if (menuSelector.equals("3")){
            quit();
        } else{
            System.out.println("Select a valid menu option!");
        }
    }

    public void printBookList(){
        System.out.println("Type book title to checkout book, type \"back\" to go back");
        for(Book book : libraryBooks) {
            System.out.println(book.getTitle() + " | " + book.getAuthor() + " | " + book.getYearPublished());
        }
    }

    public void printReturnBookList(){
        System.out.println("Type book title to return book, type \"back\" to go back");
        for(Book book : customerBooks) {
            System.out.println(book.getTitle() + " | " + book.getAuthor() + " | " + book.getYearPublished());
        }
    }

    public void selectBookToCheckout(String selector) {
        Book bookToCheckout = findBookByTitle(selector);
        if (bookToCheckout != null) {
            addToCustomerBooks(bookToCheckout);
            System.out.println("Thank you! Enjoy the book");
            printMainMenu();
        } else if (selector.equals("back")) {
            printMainMenu();
        } else {
            System.out.println("That book is not available.");
        }
    }

    public void selectBookToReturn(String selector) {
        Book bookToCheckout = findBookByTitle(selector);
        if (bookToCheckout != null) {
            returnToLibraryBooks(bookToCheckout);
            System.out.println("Thank you! Enjoy the book");
            printMainMenu();
        } else if (selector.equals("back")) {
            printMainMenu();
        } else {
            System.out.println("That book is not available.");
        }
    }

    public void addToCustomerBooks(Book book) {
        customerBooks.add(book);
        libraryBooks.remove(book);
    }

    public void returnToLibraryBooks(Book book) {
        customerBooks.remove(book);
        libraryBooks.add(book);
    }

    private BufferedReader getReader() {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        return br;
    }

    private String askUserInput(BufferedReader br) throws IOException {
        String text = br.readLine();
        return text;
    }

    private void quit() {
        System.out.println("Bye!");
        running = false;
    }

    private String getInput(BufferedReader br){
        String input = "";
        try {
            input = askUserInput(br);
        } catch(IOException ex) {
            System.out.println(ex.toString());
        }
        return input;
    }

    private Book findBookByTitle(String bookTitle) {
        Book correctBook = null;
        for (Book book : libraryBooks) {
            if (bookTitle.equals(book.getTitle())) {
                correctBook = book;
            }
        }
        return correctBook;
    }
}
