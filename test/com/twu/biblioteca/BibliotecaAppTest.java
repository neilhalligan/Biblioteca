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
    private Book book3;
    private String bookMenuMessage;
    private String mainMenuMessage;
    private String returnMenuMessage;

    @Before
    public void setUp() throws Exception {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        biblioteca = new BibliotecaApp();
        biblioteca.populateBookList();
        book1 = new Book("The Great Gatsby", "F. Scott Fitzgerald", 1922);
        book2 = new Book("Moby Dick","Herman Melville", 1851);
        book3 = new Book("Frankenstein","Mary Shelley", 1818);
        bookMenuMessage = "Type book title to checkout book, type \"back\" to go back\n";
        mainMenuMessage = "Main Menu\n1 - List Books\n2 - Return Books\n3 - Quit\n";
        returnMenuMessage = "Type book title to return book, type \"back\" to go back\n";
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
        assertEquals(mockBookList, biblioteca.libraryBooks);
    }

    @Test
    public void populateBookList() throws Exception {
        assertEquals(book1, biblioteca.libraryBooks.get(0));
        assertEquals(book2, biblioteca.libraryBooks.get(1));
    }

    @Test
    public void printMainMenu() throws Exception {
        biblioteca.printMainMenu();
        String expectedOutput  = mainMenuMessage;
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void printBookList() throws Exception {
        String expectedOutput = bookMenuMessage;
        for(Book book : biblioteca.libraryBooks) {
            expectedOutput += book.getTitle() + " | " + book.getAuthor() + " | " + book.getYearPublished() + "\n";
        }

        biblioteca.printLibraryBookList();
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void mainMenuInvalidSelection() throws Exception {
        Reader InputString = new StringReader("invalid\n");
        BufferedReader menuInput = new BufferedReader(InputString);
        String expectedOutput = "Select a valid menu option!\n";
        biblioteca.selectMainMenu(menuInput);
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void printReturnBookList() throws Exception {
        String expectedOutput = returnMenuMessage;
        for(Book book : biblioteca.customerBooks) {
            expectedOutput += book.getTitle() + " | " + book.getAuthor() + " | " + book.getYearPublished() + "\n";
        }
    }

    @Test
    public void mainMenuQuit() throws Exception {
        Reader InputString = new StringReader("3\n1");
        BufferedReader menuInput = new BufferedReader(InputString);
        String expectedOutput = "";
        biblioteca.selectMainMenu(menuInput);
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void hasCustomerBooks() throws Exception {
        List<Book> testCustomerBooks = new ArrayList<Book>();
        testCustomerBooks.add(book3);
        assertEquals(biblioteca.customerBooks, testCustomerBooks);
    }

    @Test
    public void addToCustomerBooks() throws Exception {
        Book customerBook = biblioteca.libraryBooks.get(0);
        biblioteca.addToCustomerBooks(customerBook);
        assertTrue(biblioteca.customerBooks.contains(customerBook));
        assertFalse(biblioteca.libraryBooks.contains(customerBook));
    }

    @Test
    public void returnToLibraryBooks() throws Exception {
        Book returnBook = biblioteca.customerBooks.get(0);
        biblioteca.returnToLibraryBooks(returnBook);
        assertTrue(biblioteca.libraryBooks.contains(returnBook));
        assertFalse(biblioteca.customerBooks.contains(returnBook));
    }

    @Test
    public void checkoutBookAndPrintMainMenu() throws Exception {
        Book bookToCheckout = biblioteca.libraryBooks.get(0);
        Reader menuInputString = new StringReader("1\n" + bookToCheckout.getTitle());
        BufferedReader menuInput = new BufferedReader(menuInputString);
        String expectedOutput = bookMenuMessage;
        for(Book book : biblioteca.libraryBooks) {
            expectedOutput += book.getTitle() + " | " + book.getAuthor() + " | " + book.getYearPublished() + "\n";
        }
        biblioteca.selectMainMenu(menuInput);
        expectedOutput += "Thank you! Enjoy the book\n";
        expectedOutput += mainMenuMessage;
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void checkoutMenuGoBack() throws Exception {
        Reader menuInputString = new StringReader("1\nback\n");
        BufferedReader menuInput = new BufferedReader(menuInputString);
        String expectedOutput = bookMenuMessage;
        for(Book book : biblioteca.libraryBooks) {
            expectedOutput += book.getTitle() + " | " + book.getAuthor() + " | " + book.getYearPublished() + "\n";
        }
        biblioteca.selectMainMenu(menuInput);
        expectedOutput += mainMenuMessage;
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void checkoutMenuInvalidSelection() throws Exception {
        Reader menuInputString = new StringReader("1\ninvalid selection\n");
        BufferedReader menuInput = new BufferedReader(menuInputString);
        String expectedOutput = bookMenuMessage;
        for(Book book : biblioteca.libraryBooks) {
            expectedOutput += book.getTitle() + " | " + book.getAuthor() + " | " + book.getYearPublished() + "\n";
        }
        biblioteca.selectMainMenu(menuInput);
        expectedOutput += "That book is not available.\n";
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void returnBookAndPrintMainMenu() throws Exception {
        Book bookToReturn = biblioteca.customerBooks.get(0);
        Reader menuInputString = new StringReader("2\n" + bookToReturn.getTitle());
        BufferedReader menuInput = new BufferedReader(menuInputString);
        String expectedOutput = returnMenuMessage;
        for(Book book : biblioteca.customerBooks) {
            expectedOutput += book.getTitle() + " | " + book.getAuthor() + " | " + book.getYearPublished() + "\n";
        }
        biblioteca.selectMainMenu(menuInput);
        expectedOutput += "Thank you for returning the book.\n";
        expectedOutput += mainMenuMessage;
        assertEquals(expectedOutput, outContent.toString());
        assertTrue(biblioteca.libraryBooks.contains(bookToReturn));
        assertFalse(biblioteca.customerBooks.contains(bookToReturn));
    }

    @Test
    public void returnMenuGoBack() throws Exception {
        Reader menuInputString = new StringReader("2\nback\n");
        BufferedReader menuInput = new BufferedReader(menuInputString);
        String expectedOutput = returnMenuMessage;
        for(Book book : biblioteca.customerBooks) {
            expectedOutput += book.getTitle() + " | " + book.getAuthor() + " | " + book.getYearPublished() + "\n";
        }
        biblioteca.selectMainMenu(menuInput);
        expectedOutput += mainMenuMessage;
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void returnMenuInvalidSelection() throws Exception {
        Reader menuInputString = new StringReader("2\ninvalid selection\n");
        BufferedReader menuInput = new BufferedReader(menuInputString);
        String expectedOutput = returnMenuMessage;
        for(Book book : biblioteca.customerBooks) {
            expectedOutput += book.getTitle() + " | " + book.getAuthor() + " | " + book.getYearPublished() + "\n";
        }
        biblioteca.selectMainMenu(menuInput);
        expectedOutput += "That is not a valid book to return.\n";
        assertEquals(expectedOutput, outContent.toString());
    }
}
