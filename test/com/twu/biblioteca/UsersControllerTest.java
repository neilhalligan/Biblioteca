package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import static org.junit.Assert.assertEquals;

public class UsersControllerTest {
    private User user1;
    private User user2;
    private User user3;
    private UsersController usersController;

    @Before
    public void before(){
        usersController = new UsersController();
        usersController.seedUsers();
        user1 = new User("neil halligan", "123-4567");
        user2 = new User("marco polo", "123-4567");
        user3 = new User("christopher columbus", "123-4567");
    }

    @Test
    public void seedUsers() throws Exception {
        assertEquals(user1, usersController.getUsers().get(0));
        assertEquals(user2, usersController.getUsers().get(1));
        assertEquals(user3, usersController.getUsers().get(2));
    }

    @Test
    public void login() throws Exception {
        assertFalse(usersController.login("neil halligan", "999-9999"));
        System.out.println(usersController.login("neil halligan", "123-4567"));
    }
}
