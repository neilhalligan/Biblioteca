package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.List;


public class UsersController {
    private List<User> users = new ArrayList<>();

    public void seedUsers(){
        User user1 = new User("neil halligan", "123-4567");
        User user2 = new User("marco polo", "123-4567");
        User librarian = new User("christopher columbus", "123-4567");
        librarian.setLibrarian(true);
        users.add(user1);
        users.add(user2);
        users.add(librarian);
    }

    public List<User> getUsers(){
        return users;
    }

    public User findUserByName(String username) {
        User foundUser = null;
        for (User user : users) {
            if (username.equals(user.getUsername())) {
                foundUser = user;
            }
        }
        return foundUser;
    }

}
