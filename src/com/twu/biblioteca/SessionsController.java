package com.twu.biblioteca;

public class SessionsController {
    private UsersController usersController;
    private boolean loggedIn = false;
    private User currentUser = null;

    public SessionsController(UsersController u) {
        usersController = u;
    }

    public boolean login(String username, String password){
        for (User user : usersController.getUsers()) {
            if (username.equals(user.getUsername()) && password.equals(user.getPassword())) {
                loggedIn = true;
                currentUser = user;
                return true;
            }
        }
        return false;
    }

    public boolean isLoggedIn() {
        if (loggedIn == true) {
            return true;
        } else {
            return false;
        }
    }

    public boolean userIsLibrarian() {
        return currentUser.isLibrarian();
    }



    public User getCurrentUser() {
        return currentUser;
    }

}
