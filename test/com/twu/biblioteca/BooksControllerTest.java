package com.twu.biblioteca;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class BooksControllerTest {
    private ByteArrayOutputStream outContent;
    private Book book1;
    private Book book2;
    private Book book3;
    private Book book4;
    private String bookMenuMessage;
    private String returnMenuMessage;
    private UsersController usersController;
    private BooksController booksController;

    @Before
    public void setUp() throws Exception {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        usersController = new UsersController();
        booksController = new BooksController(usersController);
        booksController.usersController.seedUsers();
        booksController.seedRentals();
        book1 = new Book("The Great Gatsby", "F. Scott Fitzgerald", 1922);
        book2 = new Book("Moby Dick","Herman Melville", 1851);
        book3 = new Book("Frankenstein","Mary Shelley", 1818);
        book4 = new Book("Heart of Darkness","Konrad Singer", 1974);

        bookMenuMessage = "Type book title to checkout book, type \"back\" to go back\n";
        returnMenuMessage = "Type book title to return book, type \"back\" to go back\n";
    }

    @Test
    public void hasBookList() throws Exception {
        List<Book> mockBookList = new ArrayList<>(Arrays.asList(book1, book2));
        assertEquals(mockBookList, booksController.availableItems);
    }

    @Test
    public void hasCustomerBooks() throws Exception {
        List<Book> testCustomerBooks = new ArrayList<>();
        testCustomerBooks.add(book3);
        testCustomerBooks.add(book4);
        assertEquals(booksController.rentedItems, testCustomerBooks);
    }

    @Test
    public void RentedItemsByUser() throws Exception {
        List<Book> testCustomerBooks = new ArrayList<>();
        testCustomerBooks.add(book3);
        User user = usersController.findUserByName("neil halligan");
        assertEquals(booksController.rentedItemsByUser(user), testCustomerBooks);
    }

    @Test
    public void hasUsersController() throws Exception {
        UsersController usersController = booksController.getUsersController();
        assertTrue(usersController instanceof UsersController);
    }

    @Test
    public void printLibraryBooks() throws Exception {
        String expectedOutput = bookMenuMessage;
        for(Book book : booksController.availableItems) {
            expectedOutput += book.getTitle() + " | " + book.getAuthor() + " | " + book.getYearPublished() + "\n";
        }
        booksController.printAvailableItems();
        Assert.assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void seedRentals() throws Exception {
        assertEquals(book1, booksController.availableItems.get(0));
        assertEquals(book2, booksController.availableItems.get(1));
    }

    @Test
    public void rentsItemAndAssignsUser() throws Exception {
        List<User> userList  = booksController.usersController.getUsers();
        User renter = userList.get(1);
        Book rentedBook = booksController.availableItems.get(0);
        booksController.rentItem(rentedBook, renter);
        assertEquals(renter, rentedBook.getUser());
        assertFalse(booksController.availableItems.contains(rentedBook));
        assertTrue(booksController.rentedItems.contains(rentedBook));
    }

    @Test
    public void returnItem() throws Exception {
        User userReturningBook = usersController.findUserByName("neil halligan");
        Book returnBook = booksController.rentedItems.get(0);
        booksController.returnItem(returnBook);
        assertTrue(booksController.availableItems.contains(returnBook));
        assertFalse(booksController.rentedItems.contains(returnBook));
    }
}
