package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SessionsControllerTest {
    private User user;
    private User librarian;
    private UsersController usersController;
    private SessionsController sessionsController;

    @Before
    public void before(){
        usersController = new UsersController();
        usersController.seedUsers();
        sessionsController =  new SessionsController(usersController);
        user = usersController.findUserByName("neil halligan");
        librarian = usersController.findUserByName("christopher columbus");
    }

    @Test
    public void correctlyLogsInUser() throws Exception {
        sessionsController.login("neil halligan", "999-9999");
        assertFalse(sessionsController.isLoggedIn());
        sessionsController.login("shneil halligan", "999-9999");
        assertFalse(sessionsController.isLoggedIn());
        sessionsController.login("neil halligan", "123-4567");
        assertTrue(sessionsController.isLoggedIn());
    }

    @Test
    public void currentUser() throws Exception {
        sessionsController.login("neil halligan", "123-4567");
        assertEquals(sessionsController.getCurrentUser(), user);
    }

    @Test
    public void isUserLibrarian() throws Exception {
        sessionsController.login(user.getUsername(), "123-4567");
        assertFalse(sessionsController.userIsLibrarian());
        sessionsController.login(librarian.getUsername(), "123-4567");
        assertTrue(sessionsController.userIsLibrarian());
    }
}
