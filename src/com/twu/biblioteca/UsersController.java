package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.List;


public class UsersController {
    private List<User> users = new ArrayList<>();

    public void seedUsers(){
        User user1 = new User("neil halligan", "123-4567");
        User user2 = new User("marco polo", "123-4567");
        User user3 = new User("christopher columbus", "123-4567");
        users.add(user1);
        users.add(user2);
        users.add(user3);
    }

    public List<User> getUsers(){
        return users;
    }

    public boolean login(String username, String password){
        for (User user : users) {
            if (username.equals(user.getUsername()) && password.equals(user.getPassword())) {
                return true;
            }
        }
        return false;
    }
}
