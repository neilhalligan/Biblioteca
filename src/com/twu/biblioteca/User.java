package com.twu.biblioteca;

import java.util.ArrayList;

public class User {
    private String username;
    private String password;
    private ArrayList<Book> rentedBooks = new ArrayList<>();

    public User(String u, String p){
        username = u;
        password = p;
    }

    public boolean equals(Object object) {
        User user = (User) object;
        return (username == user.username &&
                password == user.password);
    }

    public String getUsername() {
        return username;
    }

    public ArrayList<Book> getRentedBooks() {
        return rentedBooks;
    }

    public String getPassword() {
        return password;
    }

    public void addToRentedBooks(Book book){
        rentedBooks.add(book);
    }

    public void removeFromRentedBooks(Book book){
        rentedBooks.remove(book);
    }
}
