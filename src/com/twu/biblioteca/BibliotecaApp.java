package com.twu.biblioteca;

import java.io.*;
import java.lang.*;
import java.nio.Buffer;


public class BibliotecaApp {
    private boolean running = true;
    public UsersController usersController = new UsersController();
    public BooksController booksController = new BooksController(usersController);
    public MoviesController moviesController = new MoviesController();

    public static void main(String[] args) {
        BibliotecaApp biblio = new BibliotecaApp();
        biblio.go();
    }

    public void go() {
        seed();
        welcome();
        authenticateUser(getReader());
    }

    public void authenticateUser(BufferedReader br) {
        String username = askUser(br, "username");
        String password = askUser(br, "password");
        if (usersController.login(username, password)) {
            printMainMenu();
            while (running) {
                selectMainMenu(br);
            }
        } else {
            System.out.println("Invalid login!");
        }
    }

    private String askUser(BufferedReader br, String input) {
        System.out.println("Please enter " + input);
        return getInput(br);
    }

    public void welcome() {
        System.out.println("Hello, welcome to Biblioteca!");
    }

    public void printMainMenu(){
        System.out.println(
                "Main Menu\nEnter one of the following options to continue\n-list books\n" +
                "-return books\n-list movies\n-return movies\n-quit");
    }

    public void selectMainMenu(BufferedReader bufferSelector) {
        String menuSelector = getInput(bufferSelector);
        if (menuSelector.equals("list books")) {
            booksController.printAvailableItems();
            booksController.selectItemToCheckout(getInput(bufferSelector));
            printMainMenu();
        } else if (menuSelector.equals("return books")) {
            booksController.printRentedItems();
            booksController.selectItemToReturn(getInput(bufferSelector));
            printMainMenu();
        } else if (menuSelector.equals("list movies")) {
            moviesController.printAvailableItems();
            moviesController.selectItemToCheckout(getInput(bufferSelector));
            printMainMenu();
        } else if (menuSelector.equals("return movies")) {
            moviesController.printRentedItems();
            moviesController.selectItemToReturn(getInput(bufferSelector));
            printMainMenu();
        } else if (menuSelector.equals("quit")){
            quit();
        } else{
            System.out.println("Select a valid menu option!");
        }
    }

    public void seed() {
        usersController.seedUsers();
        booksController.seedRentals();
        moviesController.seedRentals();
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
}
