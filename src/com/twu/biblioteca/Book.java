package com.twu.biblioteca;

public class Book {
    private String title;
    private String author;
    private int yearPublished;
    private User user = null;

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

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public String getRenterUsername(){
        return this.getUser().getUsername();
    }

    public boolean equals(Object object) {
        Book book = (Book) object;
        return (title == book.title &&
                author == book.author &&
                yearPublished == book.yearPublished);
    }
}
