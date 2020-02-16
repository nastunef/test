package app.controllers;

import database.DatabaseHandler;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import patterns.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SignUpControllerTest {

    private  static DatabaseHandler databaseHandler;

    @BeforeClass
    public static void createOperations() {
        databaseHandler = new DatabaseHandler();
    }

    @Test
    public void initialize() throws SQLException {
        String name = "Василий";
        String lastname = "Васильев";
        String password = "vasyan777";
        String login = "vas_777_vas";
        User user = new User(name,lastname,password,login);
        databaseHandler.signUpUser(user);
        ResultSet expected = databaseHandler.getUser(user);
        Assert.assertTrue(expected.next());

    }
}