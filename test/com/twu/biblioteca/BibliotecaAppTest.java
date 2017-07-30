package com.twu.biblioteca;

import org.junit.*;
import java.io.*;
import java.util.*;
import static org.junit.Assert.*;

public class BibliotecaAppTest {
    private ByteArrayOutputStream outContent;
    private BibliotecaApp biblioteca;
    private Book book1;
    private Book book2;

    @Before
    public void setUp() throws Exception {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        biblioteca = new BibliotecaApp();
        biblioteca.populateBookList();
        book1 = new Book("The Great Gatsby", "F. Scott Fitzgerald", 1922);
        book2 = new Book("Moby Dick","Herman Melville", 1851);
    }

    @Test
    public void printsWelcomeMessage() throws Exception {
        biblioteca.welcome();
        String expectedOutput  = "Hello, welcome to Biblioteca!\n";
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void hasBookList() throws Exception {
        List<Book> mockBookList = new ArrayList<Book>(Arrays.asList(book1, book2));
        assertEquals(mockBookList, biblioteca.bookList);
    }

    @Test
    public void populateBookList() throws Exception {
        assertEquals(book1, biblioteca.bookList.get(0));
        assertEquals(book2, biblioteca.bookList.get(1));
    }

//    @Test
//    public void printBookList() throws Exception {
//        biblioteca.printBookList();
//        String expectedOutput = "Type book title name to checkout book\n";
//        expectedOutput += book1.getTitle() + " | " + book1.getAuthor() + " | " + book1.getYearPublished() + "\n";
//        expectedOutput += book2.getTitle() + " | " + book2.getAuthor() + " | " + book2.getYearPublished() + "\n";
//        assertEquals(expectedOutput, outContent.toString());
//    }

    @Test
    public void printMenu() throws Exception {
        biblioteca.printMenu();
        String expectedOutput  = "Main Menu\n1 - List Books\n2 - Quit\n";
        assertEquals(expectedOutput, outContent.toString());
    }

//    @Test
//    public void mainMenuSelect() throws Exception {
//        // could refactor by adding Stream
//        String expectedOutput = "Select a valid menu option!\n";
//        biblioteca.selectMenu("r", "r");
//
//        // below test is tested in select book to checkout. but must assert that book list is printed correctly!!
//
////        expectedOutput += "Type book number to checkout book, type \"back\" to go back\n";
////        for(Book book : biblioteca.bookList) {
////            expectedOutput += book.getTitle() + " | " + book.getAuthor() + " | " + book.getYearPublished() + "\n";
////        }
////        biblioteca.selectMenu("1", "r");
//
//        expectedOutput += "Bye!\n";
//        biblioteca.selectMenu("2", "r");
//        assertEquals(expectedOutput, outContent.toString());
//    }

    @Test
    public void selectBookToCheckout() throws Exception {
        Book firstBook = biblioteca.bookList.get(0);
        Book secondBook = biblioteca.bookList.get(1);

        biblioteca.selectBookToCheckout(firstBook.getTitle());
        biblioteca.selectBookToCheckout(secondBook.getTitle());
//        Book firstBook = biblioteca.bookList.get(0);
//        Book firstBook = biblioteca.bookList.get(Integer.parseInt("1") - 1);
        String expectedOutput  = firstBook.getTitle() + " successfully checked out!\n";

//        biblioteca.selectBookToCheckout("1");
//        Book secondBook = biblioteca.bookList.get(0);
        expectedOutput  += secondBook.getTitle() + " successfully checked out!\n";

//        assertEquals(biblioteca.customerBooks.get(0), firstBook);
//        assertEquals(biblioteca.customerBooks.get(1), secondBook);
//        assertFalse(biblioteca.bookList.contains(firstBook));
//        assertFalse(biblioteca.bookList.contains(secondBook));
        assertEquals(expectedOutput, outContent.toString());
    }


//    @Test
//    public void name() throws Exception {
//        Reader menuInputString = new StringReader("1\n");
//        BufferedReader menuInput = new BufferedReader(menuInputString);
//
//        Reader bookInputString = new StringReader("The Great Gatsby\n");
//        BufferedReader bookInput = new BufferedReader(bookInputString);
//
//        String expectedOutput = "Type book number to checkout book, type \"back\" to go back\n";
//        for(Book book : biblioteca.bookList) {
//            expectedOutput += book.getTitle() + " | " + book.getAuthor() + " | " + book.getYearPublished() + "\n";
//        }
//
//        biblioteca.selectMenu(biblioteca.getInput(menuInput), biblioteca.getInput(bookInput));
//        expectedOutput += "The Great Gatsby successfully checked out!\n";
//        assertEquals(expectedOutput, outContent.toString());
//    }

    @Test
    public void hasCustomerBooks() throws Exception {
        List<Book> testCustomerBooks = new ArrayList<Book>();
        assertEquals(biblioteca.customerBooks, testCustomerBooks);
    }

    @Test
    public void addToCustomerBooks() throws Exception {
        Book book = biblioteca.bookList.get(0);
        biblioteca.addToCustomerBooks(book);
        assertEquals(biblioteca.customerBooks.get(0), book1);
        assertFalse(biblioteca.bookList.contains(book1));
    }

    @Test
    public void selectBookToCheckoutByTitle() throws Exception {
        biblioteca.selectBookToCheckout("The Great Gatsby");
        String expectedOutput = "The Great Gatsby successfully checked out!\n";
        assertEquals(expectedOutput, outContent.toString());
    }
}
