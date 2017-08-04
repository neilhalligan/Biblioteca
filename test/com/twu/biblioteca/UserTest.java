package com.twu.biblioteca;

import org.junit.*;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class UserTest {
    private User user1;
    private ArrayList<Book> mockRentedBooks;

    @Before
    public void before() {
        user1 = new User("neil halligan", "123-4567");
        mockRentedBooks = new ArrayList<>();
    }

    @Test
    public void hasUsername() {
        assertEquals(user1.getUsername(), "neil halligan");
    }

    @Test
    public void hasPassword() {
        assertEquals(user1.getPassword(), "123-4567");
    }

    @Test
    public void hasRentedBooks() throws Exception {
        assertEquals(mockRentedBooks, user1.getRentedBooks());
    }

    @Test
    public void addToRentedBooks() throws Exception {
        Book book1 = new Book("The Great Gatsby", "F. Scott Fitzgerald", 1922);
        user1.addToRentedBooks(book1);
        assertEquals(book1, user1.getRentedBooks().get(0));
    }

    @Test
    public void removeFromRentedBooks() throws Exception {
        Book book1 = new Book("The Great Gatsby", "F. Scott Fitzgerald", 1922);
        user1.addToRentedBooks(book1);
        assertTrue(user1.getRentedBooks().contains(book1));
        user1.removeFromRentedBooks(book1);
        assertFalse(user1.getRentedBooks().contains(book1));
    }

    @Test
    public void equals() throws Exception {
        User user2 = new User("Marco Polo", "123-4567");
        User user3 = new User("neil halligan", "123-4567");
        assertNotEquals(user1, user2);
        assertEquals(user1, user3);
    }
}
