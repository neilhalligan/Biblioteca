package com.twu.biblioteca;

/**
 * Created by neil on 7/27/17.
 */
public class Book {
    private String title;
    private String author;
    private int yearPublished;

    public int getYearPublished() {
        return yearPublished;
    }

    public Book(String t, String a, int y) {
        title = t;
        author = a;
        yearPublished = y;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {

        return title;
    }

    public boolean equals(Object object) {
        Book book = (Book) object;
        return (title == book.title &&
                author == book.author &&
                yearPublished == book.yearPublished);
    }
}
