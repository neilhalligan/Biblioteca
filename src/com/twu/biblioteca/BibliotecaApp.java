package com.twu.biblioteca;

import java.io.*;
import java.lang.*;


public class BibliotecaApp {
    private boolean running = true;
    public UsersController usersController = new UsersController();
    public SessionsController sessionsController = new SessionsController(usersController);
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
        if (sessionsController.login(username, password)) {
            run(br);
        } else {
            System.out.println("Invalid login!");
        }
    }

    public void welcome() {
        System.out.println("Hello, welcome to Biblioteca!");
    }

    public void printMainMenu(){
        if (sessionsController.userIsLibrarian()) {
            printMainMenuAsLibrarian();
        } else {
            printMainMenuAsUser();
        }
    }

    public void printMainMenuAsLibrarian(){
        System.out.println(
                "Main Menu\n" +
                "Enter one of the following options to continue\n" +
                "-rented books\n" +
                "-rented movies\n" +
                "-quit");
    }

    public void printMainMenuAsUser(){
        System.out.println(
                "Main Menu\nEnter one of the following options to continue\n-available books\n" +
                "-rented books\n-list movies\n-return movies\n-quit");
    }

    public void selectMainMenu(BufferedReader br) {
        if (sessionsController.userIsLibrarian()) {
            selectMainMenuAsLibrarian(br);
        } else {
            selectMainMenuAsUser(br);
        }
    }

    public void selectMainMenuAsLibrarian(BufferedReader bufferSelector) {
        String menuSelector = getInput(bufferSelector);
        if (menuSelector.equals("rented books")) {
            booksController.printRentedItemsAsLibrarian();
            selectGoBack(bufferSelector);
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

    public void selectMainMenuAsUser(BufferedReader bufferSelector) {
        String menuSelector = getInput(bufferSelector);
        User user = sessionsController.getCurrentUser();
        if (menuSelector.equals("available books")) {
            booksController.printAvailableItems();
            booksController.selectItemToCheckout(getInput(bufferSelector), user);
            printMainMenu();
        } else if (menuSelector.equals("rented books")) {
            booksController.printRentedItemsAsUser(user);
            booksController.selectItemToReturn(getInput(bufferSelector), user);
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

    private void selectGoBack(BufferedReader bufferSelector) {
        do {
            System.out.println("invalid option");
        } while (!getInput(bufferSelector.readLine()).equals("back"));
//        if (getInput(bufferSelector).equals("back")) {
//            printMainMenu();
//        } else {
//            System.out.println("invalid option");
//        }
    }

    private void run(BufferedReader br) {
        printMainMenu();
        while (running) {
            selectMainMenu(br);
        }
    }

    private String askUser(BufferedReader br, String input) {
        System.out.println("Please enter " + input);
        return getInput(br);
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
