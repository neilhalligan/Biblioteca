package com.twu.biblioteca;

import java.io.*;
import java.lang.*;
import java.util.*;

public class BibliotecaApp {
    List<Book> bookList = new ArrayList<Book>();
    List<Book> customerBooks = new ArrayList<Book>();
    private boolean running = true;

    public static void main(String[] args) {
        BibliotecaApp biblio = new BibliotecaApp();
        biblio.go();
    }

    public void go() {
        welcome();
        populateBookList();
        printMenu();
        while (running) {
            selectMenu(getInput(getReader()));
        }
    }

    public void welcome() {
        System.out.println("Hello, welcome to Biblioteca!");
    }

    public void populateBookList(){
        Book book1 = new Book("The Great Gatsby", "F. Scott Fitzgerald", 1922);
        Book book2 = new Book("Moby Dick","Herman Melville", 1851);
        bookList.add(book1);
        bookList.add(book2);
    }

    public void printMenu(){
        System.out.println("Main Menu\n1 - List Books\n2 - Quit");
    }

    public String getInput(BufferedReader br){
        String input = "";
        try {
            input = askUserInput(br);
        } catch(IOException ex) {
            System.out.println(ex.toString());
        }
        return input;
    }

    public void selectMenu(String selector) {
        if (selector.equals("1")) {
            printBookList();
            selectBookToCheckout(selector);
        } else if (selector.equals("2")){
            quit();
        } else{
            System.out.println("Select a valid menu option!");
        }
    }

    public void selectBookToCheckout(String selector) {
        Book bookToCheckout = null;

        for (Book book : bookList) {
            if (selector.equals(book.getTitle())) {
                bookToCheckout = book;
            }
        }

        if (bookToCheckout != null) {
            addToCustomerBooks(bookToCheckout);
            System.out.println(bookToCheckout.getTitle() + " successfully checked out!");
        } else if (selector.equals("0\n")) {
            printMenu();
        } else {
            System.out.println("Select a valid book!");
        }
    }

    public void addToCustomerBooks(Book book) {
        customerBooks.add(book);
        bookList.remove(book);
    }

    public void printBookList(){
        System.out.println("Type book number to checkout book, type \"back\" to go back");
        for(Book book : bookList) {
            System.out.println(book.getTitle() + " | " + book.getAuthor() + " | " + book.getYearPublished());
        }
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
}
