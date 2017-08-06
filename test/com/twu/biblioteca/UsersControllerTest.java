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
    public void findUserByName() throws Exception {
        User user = usersController.findUserByName("smarco rolo");
        assertEquals(user, null);
        user = usersController.findUserByName("marco polo");
        assertEquals(user.getUsername(), "marco polo");
    }
}
